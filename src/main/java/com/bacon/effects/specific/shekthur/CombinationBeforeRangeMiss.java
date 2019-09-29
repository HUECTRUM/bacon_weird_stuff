package com.bacon.effects.specific.shekthur;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.utils.ChoiceUtils.NO_CHOICES;
import static com.bacon.utils.FieldUtils.playerDist;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class CombinationBeforeRangeMiss implements CardEffect {
    @Override
    public String effectName() {
        return "Combination 3+ range miss";
    }

    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        if (playerDist(gameInfoHolder) >= 3) {
            player.beatHolder.attackHit = false;
        }
    }
}
