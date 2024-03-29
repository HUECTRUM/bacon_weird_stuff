package com.bacon.gamefiles.statemachine.resolvers;

import com.bacon.gamefiles.attacks.AttackPair;
import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.events.EventType;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.attacks.AttackPairBonusType.*;
import static com.bacon.gamefiles.events.GameEvent.event;
import static com.bacon.gamefiles.gameobjects.triggers.EffectTrigger.ON_DAMAGE_DEALT;
import static com.bacon.gamefiles.gameobjects.triggers.EffectTrigger.ON_DAMAGE_TAKEN;
import static com.bacon.gamefiles.statemachine.conditions.AttackCheckTransitionConditions.NO_DAMAGE;
import static com.bacon.gamefiles.statemachine.conditions.AttackCheckTransitionConditions.PLAYER_DEAD;
import static com.bacon.gamefiles.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static com.bacon.gamefiles.statemachine.resolvers.internal.helper.EffectResolveMode.ACTIVE;
import static com.bacon.gamefiles.statemachine.resolvers.internal.helper.EffectResolveMode.REACTIVE;
import static java.lang.Math.max;

@Component
@Slf4j
public class DamageResolver {
    @Autowired
    private EventEmitter emitter;

    public StateTransitionCondition resolveDamage(GameInfoHolder holder, boolean active) {
        int beatNum = holder.infoHelper.currentBeatNumber(holder);

        AttackPair attackPair = active
                ? holder.beatInfoHolder.activePlayerPair : holder.beatInfoHolder.reactivePlayerPair;
        AttackPair defendingPair = active
                ? holder.beatInfoHolder.reactivePlayerPair : holder.beatInfoHolder.activePlayerPair;
        Player damageTaking = damageTakingPlayer(holder, active);
        Player damageDealing = damageDealingPlayer(holder, active);

        int effectiveSoak = damageDealing.hasBonus(beatNum, ISOAK) ? 0 : defendingPair.soak(damageTaking, beatNum);
        int damageDealt = max(attackPair.power(damageDealing, beatNum) - effectiveSoak, 0);
        damageTaking.health -= damageDealt;
        damageDealing.beatHolder.damageDealt = damageTaking.beatHolder.damageTaken = damageDealt;

        emitter.emit(event(
                damageTaking == holder.playerOne ? EventType.P1_DAMAGE : EventType.P2_DAMAGE,
                List.of(damageDealt, damageTaking.health),
                holder.gameId
        ));

        resolveEffects(damageDealing, damageTaking, damageDealt, holder);
        setStunConditions(damageDealt, defendingPair, damageDealing, damageTaking, beatNum);
        log.info("Attack hit. New health for damage taking player {} is {}",
                damageTaking.playerId, damageTaking.health);

        return damageTaking.health <= 0
                ? PLAYER_DEAD
                : damageDealt > 0 ? EMPTY : NO_DAMAGE;
    }

    private Player damageTakingPlayer(GameInfoHolder holder, boolean active) {
        boolean firstPlayerActive = holder.beatInfoHolder.activePlayer == holder.playerOne;
        return active
                ? (firstPlayerActive ? holder.playerTwo : holder.playerOne)
                : (firstPlayerActive ? holder.playerOne : holder.playerTwo);
    }

    private Player damageDealingPlayer(GameInfoHolder holder, boolean active) {
        boolean firstPlayerActive = holder.beatInfoHolder.activePlayer == holder.playerOne;
        return active
                ? (firstPlayerActive ? holder.playerOne : holder.playerTwo)
                : (firstPlayerActive ? holder.playerTwo : holder.playerOne);
    }

    private void setStunConditions(int damageDealt, AttackPair defendingPair, Player attackingPlayer, Player defendingPlayer, int beatNum) {
        if (defendingPlayer.hasBonus(beatNum, SI)) {
            return;
        }

        int effectiveSG = attackingPlayer.hasBonus(beatNum, ISG) ? 0 : defendingPair.stunGuard(defendingPlayer, beatNum);
        defendingPlayer.beatHolder.stunned |= damageDealt > effectiveSG;
    }

    //TODO: Separate game states
    private void resolveEffects(Player damageDealing, Player damageTaking, int damage, GameInfoHolder holder) {
        if (damage <= 0) {
            return;
        }

        TriggeredEffectsResolver resolver = holder.resolversContainer.effectsResolver;
        resolver.resolveEffects(
                holder,
                ON_DAMAGE_DEALT,
                damageDealing.equals(holder.beatInfoHolder.activePlayer) ? ACTIVE : REACTIVE
        );
        resolver.resolveEffects(
                holder,
                ON_DAMAGE_TAKEN,
                damageTaking.equals(holder.beatInfoHolder.activePlayer) ? ACTIVE : REACTIVE
        );
    }
}
