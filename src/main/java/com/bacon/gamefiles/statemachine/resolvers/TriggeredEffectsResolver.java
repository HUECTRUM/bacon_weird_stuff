package com.bacon.gamefiles.statemachine.resolvers;

import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.gameobjects.triggers.EffectTrigger;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.ioc.selector.SelectorHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.choices.ChoiceSelector;
import com.bacon.gamefiles.selectors.effectorder.EffectOrderSelector;
import com.bacon.gamefiles.statemachine.conditions.StateTransitionCondition;
import com.bacon.gamefiles.statemachine.resolvers.internal.helper.EffectResolveMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.holders.BeatTriggerKey.trigger;
import static com.bacon.gamefiles.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static com.bacon.gamefiles.utils.StreamUtils.concatLists;
import static java.util.Collections.emptyList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Slf4j
@Scope(value = SCOPE_PROTOTYPE)
public class TriggeredEffectsResolver {
    public SelectorHolder<ChoiceSelector> choiceSelectors = new SelectorHolder<>();
    public SelectorHolder<EffectOrderSelector> effectOrderSelectorSelectors = new SelectorHolder<>();

    public StateTransitionCondition resolveEffects(GameInfoHolder holder, EffectTrigger trigger, EffectResolveMode mode) {
        switch (mode) {
            case BOTH:
                return resolveBoth(holder, trigger);
            case ACTIVE:
                return resolveSingle(holder, trigger, true);
            case REACTIVE:
                return resolveSingle(holder, trigger, false);
            case ACTIVE_FIRST:
                //todo: return value considering active?
                resolveSingle(holder, trigger, true);
                return resolveSingle(holder, trigger, false);
            case REACTIVE_FIRST:
                //todo: return value considering reactive?
                resolveSingle(holder, trigger, false);
                return resolveSingle(holder, trigger, true);
            default:
                return EMPTY;
        }
    }

    private StateTransitionCondition resolveSingle(GameInfoHolder holder, EffectTrigger trigger, boolean active) {
        int beatNum = holder.infoHelper.currentBeatNumber(holder);

        Player player = active ? holder.beatInfoHolder.activePlayer : holder.beatInfoHolder.reactivePlayer;
        List<CardEffect> effects = active
                ? holder.beatInfoHolder.activePlayerPair.triggeredEffects(trigger)
                : holder.beatInfoHolder.reactivePlayerPair.triggeredEffects(trigger);
        effects.addAll(holder.additionalEffects.getOrDefault(trigger(beatNum, trigger, player), emptyList()));

        if (effects.size() > 1) {
            effectOrderSelectorSelectors.get(player, holder)
                    .effectOrder(player, holder, effects)
                    .forEach(ind -> resolveEffect(holder, player, effects.get(ind)));
        } else {
            effects.forEach(effect -> resolveEffect(holder, player, effect));
        }

        return EMPTY;
    }

    //used when there is no active/reactive players set yet
    private StateTransitionCondition resolveBoth(GameInfoHolder holder, EffectTrigger trigger) {
        int beatNum = holder.infoHelper.currentBeatNumber(holder);

        List<CardEffect> firstPlayerEffects = concatLists(
                holder.playerOne.beatHolder.currentBeatPair.triggeredEffects(trigger),
                holder.additionalEffects.getOrDefault(trigger(beatNum, trigger, holder.playerOne), emptyList())
        );
        List<CardEffect> secondPlayerEffects = concatLists(
                holder.playerTwo.beatHolder.currentBeatPair.triggeredEffects(trigger),
                holder.additionalEffects.getOrDefault(trigger(beatNum, trigger, holder.playerTwo), emptyList())
        );

        firstPlayerEffects.forEach(effect -> resolveEffect(holder, holder.playerOne, effect));
        secondPlayerEffects.forEach(effect -> resolveEffect(holder, holder.playerTwo, effect));
        return EMPTY;
    }

    private void resolveEffect(GameInfoHolder holder, Player player, CardEffect effect) {
        List<?> choices = effect.choices(player, holder);
        log.info("Effect choices available for effect {}: {}", effect, choices);

        if (!choices.isEmpty()) {
            //do not use selectors in case there are no choices
            int index = choices.size() == 1
                    ? 0 : choiceSelectors.get(player, holder).choose(holder, player, effect, choices);
            effect.apply(player, holder, index);
        }
    }
}
