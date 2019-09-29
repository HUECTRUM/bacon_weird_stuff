package com.bacon.effects.specific.shekthur;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static com.bacon.attacks.AttackPairBonus.of;
import static com.bacon.attacks.AttackPairBonusType.ISOAK;
import static com.bacon.utils.ChoiceUtils.NO_CHOICES;
import static java.math.BigDecimal.valueOf;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class CominationISoak implements CardEffect {
    @Override
    public String effectName() {
        return "Combination ISoak";
    }

    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int beatNum = gameInfoHolder.infoHelper.currentBeatNumber(gameInfoHolder);
        BigDecimal prio = player.beatHolder.currentBeatPair.totalPriority(player, beatNum);

        if (prio.compareTo(valueOf(7)) >= 0) {
            player.attachBonus(beatNum, of(ISOAK, 0));
        }
    }
}
