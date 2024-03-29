package com.bacon.gamefiles.effects.movement.common;

import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.utils.calculation.MovementCalculator;
import com.bacon.gamefiles.utils.helper.MovementHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.utils.FieldUtils.playerDist;
import static com.bacon.gamefiles.utils.StreamUtils.concatLists;
import static com.bacon.gamefiles.utils.StreamUtils.filterList;
import static java.lang.Math.abs;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Component
@Scope(value = SCOPE_PROTOTYPE)
public class AdvanceDodge implements CardEffect {
    @Autowired
    private MovementHelper movementHelper;
    @Autowired
    private MovementCalculator movementCalculator;

    public final List<Integer> choices;

    @Override
    public String effectName() {
        return "Dodge";
    }

    @Override
    public Object[] constructorParams() {
        return new Object[] {choices};
    }

    @Override
    public List<Integer> choices(Player player, GameInfoHolder gameInfoHolder) {
        int advanceMax = movementCalculator.maxAvailableAdvance(gameInfoHolder, player);
        List<Integer> advances = filterList(choices, choice -> choice > 0 && abs(choice) <= advanceMax);

        int retreatMax = movementCalculator.maxAvailableRetreat(gameInfoHolder, player);
        List<Integer> retreats = filterList(choices, choice -> choice < 0 && abs(choice) <= retreatMax);

        return concatLists(advances, retreats);
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int moveValue = choices(player, gameInfoHolder).get(choiceIndex);
        int spaces = abs(moveValue);

        Direction advanceDirection = movementCalculator.advanceDirection(gameInfoHolder, player);
        Direction direction = moveValue > 0
                ? advanceDirection : movementCalculator.retreatDirection(gameInfoHolder, player);

        int totalSpaces = direction == advanceDirection && spaces >= playerDist(gameInfoHolder)
                ? spaces + 1 : spaces;

        movementHelper.move(gameInfoHolder, player, direction, totalSpaces);
        if (advanceDirection != movementCalculator.advanceDirection(gameInfoHolder, player)) {
            dodgeProc(player, gameInfoHolder);
        }
    }

    private void dodgeProc(Player player, GameInfoHolder gameInfoHolder) {
        gameInfoHolder.infoHelper.opponent(gameInfoHolder, player).beatHolder.attackHit = false;
    }
}