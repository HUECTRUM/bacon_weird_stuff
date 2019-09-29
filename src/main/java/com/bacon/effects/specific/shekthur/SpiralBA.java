package com.bacon.effects.specific.shekthur;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.utils.calculation.MovementCalculator;
import com.bacon.utils.helper.MovementHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.attacks.AttackPairBonus.of;
import static com.bacon.attacks.AttackPairBonusType.POWER;
import static com.bacon.utils.FieldUtils.playerDist;
import static com.bacon.utils.StreamUtils.filterList;
import static java.util.Arrays.asList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class SpiralBA implements CardEffect {
    @Autowired
    private MovementHelper movementHelper;
    @Autowired
    private MovementCalculator movementCalculator;

    private List<Integer> advanceValues = asList(0, 1, 2, 3);

    @Override
    public String effectName() {
        return "Spiral BA advance";
    }

    @Override
    public List<Integer> choices(Player player, GameInfoHolder gameInfoHolder) {
        int advanceMax = movementCalculator.maxAvailableAdvance(gameInfoHolder, player);
        return filterList(advanceValues, value -> value <= advanceMax);
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int beatNum = gameInfoHolder.infoHelper.currentBeatNumber(gameInfoHolder);
        int spaces = choices(player, gameInfoHolder).get(choiceIndex);

        if (spaces == 0) {
            return;
        }

        movementHelper.move(
                gameInfoHolder,
                player,
                movementCalculator.advanceDirection(gameInfoHolder, player),
                spaces >= playerDist(gameInfoHolder) ? spaces + 1 : spaces
        );

        player.attachBonus(beatNum, of(POWER, -spaces));
    }
}
