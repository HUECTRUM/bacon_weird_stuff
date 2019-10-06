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

import static com.bacon.utils.StreamUtils.filterList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Retreat implements CardEffect {
    @Autowired
    private MovementHelper movementHelper;
    @Autowired
    private MovementCalculator movementCalculator;

    public final List<Integer> choices;

    @Override
    public String effectName() {
        return String.format("Retreat %s", choices);
    }

    @Override
    public List<Integer> choices(Player player, GameInfoHolder gameInfoHolder) {
        int retreatMax = movementCalculator.maxAvailableRetreat(gameInfoHolder, player);
        return filterList(choices, choice -> choice <= retreatMax);
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int spaces = choices(player, gameInfoHolder).get(choiceIndex);
        movementHelper.move(
                gameInfoHolder,
                player,
                movementCalculator.retreatDirection(gameInfoHolder, player),
                spaces
        );
    }
}
