package com.bacon.statemachine.resolvers;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import com.bacon.statemachine.resolvers.internal.helper.EffectResolveMode;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;

@Component
public class TriggeredEffectsResolver {
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
        Player player = active ? holder.beatInfoHolder.activePlayer : holder.beatInfoHolder.reactivePlayer;
        List<CardEffect> effects = active
                ? holder.beatInfoHolder.activePlayerPair.triggeredEffects(trigger)
                : holder.beatInfoHolder.reactivePlayerPair.triggeredEffects(trigger);

        effects.forEach(effect -> effect.apply(player, holder));
        return EMPTY;
    }

    //used when there is no active/reactive players set yet
    private StateTransitionCondition resolveBoth(GameInfoHolder holder, EffectTrigger trigger) {
        List<CardEffect> firstPlayerEffects = holder.beatInfoHolder.firstPlayerPair.triggeredEffects(trigger);
        List<CardEffect> secondPlayerEffects = holder.beatInfoHolder.secondPlayerPair.triggeredEffects(trigger);

        firstPlayerEffects.forEach(effect -> effect.apply(holder.playerOne, holder));
        secondPlayerEffects.forEach(effect -> effect.apply(holder.playerTwo, holder));
        return EMPTY;
    }
}