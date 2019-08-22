package com.bacon.statemachine.resolvers;

import com.bacon.attacks.AttackPairStatsCalculator;
import com.bacon.gameobjects.cards.Card;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DamageResolver {
    @Autowired
    private AttackPairStatsCalculator pairStatsCalculator;

    public void resolveDamage(GameInfoHolder holder, boolean active) {
        boolean hit = active ? holder.beatInfoHolder.activePlayerHit : holder.beatInfoHolder.reactivePlayerHit;
        if (!hit) {
            log.info("This attack has missed, oops!");
            return;
        }

        List<Card> attackPair = active
                ? holder.beatInfoHolder.activePlayerPair : holder.beatInfoHolder.reactivePlayerPair;
        Player damageTaking = damageTakingPlayer(holder, active);

        damageTaking.health -= pairStatsCalculator.power(attackPair);
        log.info("Attack hit. New health for damage taking player {} is {}",
                damageTaking.playerId, damageTaking.health);
    }

    private Player damageTakingPlayer(GameInfoHolder holder, boolean active) {
        boolean firstPlayerActive = holder.beatInfoHolder.activePlayer == holder.playerOne;
        return active
                ? (firstPlayerActive ? holder.playerTwo : holder.playerOne)
                : (firstPlayerActive ? holder.playerOne : holder.playerTwo);
    }
}
