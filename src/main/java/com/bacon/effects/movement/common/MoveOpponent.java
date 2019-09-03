package com.bacon.effects.movement.common;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import static com.bacon.utils.FieldUtils.playerDist;
import static com.bacon.utils.StreamUtils.concatLists;
import static com.bacon.utils.StreamUtils.filterList;
import static com.bacon.utils.calculation.MovementCalculator.*;
import static com.bacon.utils.helper.MovementHelper.move;
import static java.lang.Math.abs;

@Data
@AllArgsConstructor
public class MoveOpponent implements CardEffect {
    public List<Integer> choices;

    @Override
    public List<Integer> choices(Player player, GameInfoHolder gameInfoHolder) {
        int pushMax = maxAvailablePush(gameInfoHolder, player);
        List<Integer> pushes = filterList(choices, choice -> choice > 0 && abs(choice) <= pushMax);

        int pullMax = maxAvailablePull(gameInfoHolder, player);
        List<Integer> pulls = filterList(choices, choice -> choice < 0 && abs(choice) <= pullMax);

        return concatLists(pushes, pulls);
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int moveValue = choices(player, gameInfoHolder).get(choiceIndex);
        int spaces = abs(moveValue);

        Direction pullDirection = pullDirection(gameInfoHolder, player);
        Direction direction = moveValue > 0
                ? pushDirection(gameInfoHolder, player) : pullDirection;

        int totalSpaces = direction == pullDirection && spaces >= playerDist(gameInfoHolder)
                ? spaces + 1 : spaces;

        Player movePlayer = gameInfoHolder.infoHelper.opponent(gameInfoHolder, player);
        move(gameInfoHolder, movePlayer, direction, totalSpaces);
    }
}
