package com.bacon.effects.movement.common;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import com.bacon.utils.calculation.MovementCalculator;
import com.bacon.utils.helper.MovementHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.utils.FieldUtils.playerDist;
import static com.bacon.utils.StreamUtils.filterList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Advance implements CardEffect {
    @Autowired
    private MovementHelper movementHelper;
    @Autowired
    private MovementCalculator movementCalculator;

    public final List<Integer> choices;

    @Override
    public String effectName() {
        return String.format("Advance %s", choices);
    }

    @Override
    public List<Integer> choices(Player player, GameInfoHolder gameInfoHolder) {
        int advanceMax = movementCalculator.maxAvailableAdvance(gameInfoHolder, player);
        return filterList(choices, choice -> choice <= advanceMax);
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int spaces = choices(player, gameInfoHolder).get(choiceIndex);
        movementHelper.move(
                gameInfoHolder,
                player,
                movementCalculator.advanceDirection(gameInfoHolder, player),
                spaces >= playerDist(gameInfoHolder) ? spaces + 1 : spaces
        );
    }
}