package com.bacon.effects.specific.shekthur;

import com.bacon.characters.specific.shekthur.ShekthurUa;
import com.bacon.events.EventEmitter;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.attacks.AttackPairBonus.of;
import static com.bacon.attacks.AttackPairBonusType.POWER;
import static com.bacon.events.EventType.P1_UA_CHANGE;
import static com.bacon.events.EventType.P2_UA_CHANGE;
import static com.bacon.events.GameEvent.event;
import static com.bacon.utils.ChoiceUtils.NO_CHOICES;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class UnleashedEob implements CardEffect {
    @Override
    public String effectName() {
        return "Unleashed EOB";
    }

    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        ShekthurUa ua = (ShekthurUa) player.character.ua();
        int beatNum = gameInfoHolder.infoHelper.currentBeatNumber(gameInfoHolder);

        ua.gainTokens(2);
        player.attachBonus(beatNum + 1, of(POWER, 1));

        EventEmitter.INSTANCE.emit(event(
                player.equals(gameInfoHolder.playerOne) ? P1_UA_CHANGE : P2_UA_CHANGE,
                List.of(player.character.ua().description())
        ));
    }
}
