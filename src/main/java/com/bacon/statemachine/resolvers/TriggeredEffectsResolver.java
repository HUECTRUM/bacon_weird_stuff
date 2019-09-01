package com.bacon.statemachine.resolvers;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.selectors.choices.ChoiceSelector;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import com.bacon.statemachine.resolvers.internal.helper.EffectResolveMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.holders.BeatTriggerKey.trigger;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static com.bacon.utils.StreamUtils.concatLists;
import static java.util.Collections.emptyList;

@Component
@Slf4j
public class TriggeredEffectsResolver {
    @Autowired
    private ChoiceSelector choiceSelector;

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

        effects.forEach(effect -> resolveEffect(holder, player, effect));
        return EMPTY;
    }

    //used when there is no active/reactive players set yet
    private StateTransitionCondition resolveBoth(GameInfoHolder holder, EffectTrigger trigger) {
        int beatNum = holder.infoHelper.currentBeatNumber(holder);

        List<CardEffect> firstPlayerEffects = concatLists(
                holder.beatInfoHolder.firstPlayerPair.triggeredEffects(trigger),
                holder.additionalEffects.getOrDefault(trigger(beatNum, trigger, holder.playerOne), emptyList())
        );
        List<CardEffect> secondPlayerEffects = concatLists(
                holder.beatInfoHolder.secondPlayerPair.triggeredEffects(trigger),
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
            int index = choiceSelector.choose(holder, player, effect, choices);
            effect.apply(player, holder, index);
        }
    }
}
