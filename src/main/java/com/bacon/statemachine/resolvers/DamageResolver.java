package com.bacon.statemachine.resolvers;

import com.bacon.attacks.AttackPair;
import com.bacon.attacks.AttackPairStatsCalculator;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.lang.Math.max;

@Component
@Slf4j
public class DamageResolver {
    @Autowired
    private AttackPairStatsCalculator pairStatsCalculator;

    public StateTransitionCondition resolveDamage(GameInfoHolder holder, boolean active) {
        boolean attackHit = active ? holder.beatInfoHolder.activePlayerHit : holder.beatInfoHolder.reactivePlayerHit;
        if (!attackHit) {
            log.info("This attack has missed, oops!");
            return EMPTY;
        }

        AttackPair attackPair = active
                ? holder.beatInfoHolder.activePlayerPair : holder.beatInfoHolder.reactivePlayerPair;
        AttackPair defendintPair = active
                ? holder.beatInfoHolder.reactivePlayerPair : holder.beatInfoHolder.activePlayerPair;
        Player damageTaking = damageTakingPlayer(holder, active);

        damageTaking.health -= max(attackPair.power() - defendintPair.soak(), 0);
        log.info("Attack hit. New health for damage taking player {} is {}",
                damageTaking.playerId, damageTaking.health);
        return EMPTY;
    }

    private Player damageTakingPlayer(GameInfoHolder holder, boolean active) {
        boolean firstPlayerActive = holder.beatInfoHolder.activePlayer == holder.playerOne;
        return active
                ? (firstPlayerActive ? holder.playerTwo : holder.playerOne)
                : (firstPlayerActive ? holder.playerOne : holder.playerTwo);
    }
}
