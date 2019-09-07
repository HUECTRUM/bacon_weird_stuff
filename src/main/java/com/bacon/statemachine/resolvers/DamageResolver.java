package com.bacon.statemachine.resolvers;

import com.bacon.attacks.AttackPair;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.bacon.attacks.AttackPairBonusType.*;
import static com.bacon.statemachine.conditions.AttackCheckTransitionConditions.NO_DAMAGE;
import static com.bacon.statemachine.conditions.AttackCheckTransitionConditions.PLAYER_DEAD;
import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.lang.Math.max;

@Component
@Slf4j
public class DamageResolver {
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
}
