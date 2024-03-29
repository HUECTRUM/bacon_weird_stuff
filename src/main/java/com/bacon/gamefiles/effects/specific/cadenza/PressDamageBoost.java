package com.bacon.gamefiles.effects.specific.cadenza;

import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.attacks.AttackPairBonus.of;
import static com.bacon.gamefiles.attacks.AttackPairBonusType.POWER;
import static com.bacon.gamefiles.utils.ChoiceUtils.NO_CHOICES;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class PressDamageBoost implements CardEffect {
    @Override
    public String effectName() {
        return "Press damage boost";
    }

    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        int beatNum = gameInfoHolder.infoHelper.currentBeatNumber(gameInfoHolder);
        player.attachBonus(beatNum, of(POWER, player.beatHolder.damageTaken));
    }
}
