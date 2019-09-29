package com.bacon.effects.specific.shekthur;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.attacks.AttackPairBonus.of;
import static com.bacon.attacks.AttackPairBonusType.POWER;
import static com.bacon.utils.ChoiceUtils.NO_CHOICES;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class CombinationOH implements CardEffect {
    @Override
    public String effectName() {
        return "Combination OH power bonus";
    }

    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int beatNum = gameInfoHolder.infoHelper.currentBeatNumber(gameInfoHolder);
        if (beatNum - 1 == 0) {
            return;
        }

        if (player.prevBeats.get(beatNum - 1).attackHit) {
            player.attachBonus(beatNum, of(POWER, 2));
        }
    }
}
