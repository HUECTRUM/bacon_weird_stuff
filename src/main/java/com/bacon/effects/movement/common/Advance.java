package com.bacon.effects.movement.common;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.bacon.utils.FieldUtils.playerDist;
import static com.bacon.utils.StreamUtils.filterList;
import static com.bacon.utils.calculation.MovementCalculator.advanceDirection;
import static com.bacon.utils.calculation.MovementCalculator.maxAvailableAdvance;
import static com.bacon.utils.helper.MovementHelper.move;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Advance implements CardEffect {
    public List<Integer> choices;

    @Override
    public String effectName() {
        return String.format("Advance %s", choices);
    }

    @Override
    public List<Integer> choices(Player player, GameInfoHolder gameInfoHolder) {
        int advanceMax = maxAvailableAdvance(gameInfoHolder, player);
        return filterList(choices, choice -> choice <= advanceMax);
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int spaces = choices(player, gameInfoHolder).get(choiceIndex);
        move(
                gameInfoHolder,
                player,
                advanceDirection(gameInfoHolder, player),
                spaces >= playerDist(gameInfoHolder) ? spaces + 1 : spaces
        );
    }
}