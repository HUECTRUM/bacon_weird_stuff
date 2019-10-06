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

import static com.bacon.utils.ChoiceUtils.NO_CHOICES;
import static java.lang.Math.min;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class ReaverODPush implements CardEffect {
    @Autowired
    private MovementHelper movementHelper;
    @Autowired
    private MovementCalculator movementCalculator;

    @Override
    public String effectName() {
        return "Reaver OD push";
    }

    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int spaces = min(player.beatHolder.damageDealt, movementCalculator.maxAvailablePush(gameInfoHolder, player));
        Player movePlayer = gameInfoHolder.infoHelper.opponent(gameInfoHolder, player);

        movementHelper.move(gameInfoHolder, movePlayer, movementCalculator.pushDirection(gameInfoHolder, player), spaces);
    }
}
