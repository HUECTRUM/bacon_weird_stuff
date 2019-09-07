package com.bacon.effects.specific.shekthur;

import com.bacon.characters.specific.shekthur.ShekthurUa;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.stream.Collectors.toList;

public class BrandAA implements CardEffect {
    @Override
    public List<Integer> choices(Player player, GameInfoHolder gameInfoHolder) {
        ShekthurUa ua = (ShekthurUa) player.character.ua();
        return IntStream
                .range(0, ua.tokens / 2 + 1)
                .map(i -> i * 2)
                .boxed()
                .collect(toList());
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        Player opponent = gameInfoHolder.infoHelper.opponent(gameInfoHolder, player);
        ShekthurUa ua = (ShekthurUa) player.character.ua();

        int tokensSpent = choices(player, gameInfoHolder).get(choiceIndex);

        ua.tokens -= tokensSpent;
        player.health = min(player.health + tokensSpent, 20);
        opponent.health = max(opponent.health - tokensSpent, 1);
    }
}
