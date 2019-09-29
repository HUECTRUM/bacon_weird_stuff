package com.bacon.effects.movement.common;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.utils.calculation.MovementCalculator;
import com.bacon.utils.helper.MovementHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.utils.FieldUtils.playerDist;
import static com.bacon.utils.StreamUtils.concatLists;
import static com.bacon.utils.StreamUtils.filterList;
import static java.lang.Math.abs;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Data
@AllArgsConstructor
@Component
@Scope(value = SCOPE_PROTOTYPE)
public class MoveOpponent implements CardEffect {
    @Autowired
    private MovementHelper movementHelper;
    @Autowired
    private MovementCalculator movementCalculator;

    public List<Integer> choices;

    @Override
    public String effectName() {
        return String.format("Move opponent %s", choices);
    }

    @Override
    public List<Integer> choices(Player player, GameInfoHolder gameInfoHolder) {
        int pushMax = movementCalculator.maxAvailablePush(gameInfoHolder, player);
        List<Integer> pushes = filterList(choices, choice -> choice >= 0 && abs(choice) <= pushMax);

        int pullMax = movementCalculator.maxAvailablePull(gameInfoHolder, player);
        List<Integer> pulls = filterList(choices, choice -> choice < 0 && abs(choice) <= pullMax);

        return concatLists(pushes, pulls);
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int moveValue = choices(player, gameInfoHolder).get(choiceIndex);
        int spaces = abs(moveValue);

        Direction pullDirection = movementCalculator.pullDirection(gameInfoHolder, player);
        Direction direction = moveValue > 0
                ? movementCalculator.pushDirection(gameInfoHolder, player) : pullDirection;

        int totalSpaces = direction == pullDirection && spaces >= playerDist(gameInfoHolder)
                ? spaces + 1 : spaces;

        Player movePlayer = gameInfoHolder.infoHelper.opponent(gameInfoHolder, player);
        movementHelper.move(gameInfoHolder, movePlayer, direction, totalSpaces);
    }
}
