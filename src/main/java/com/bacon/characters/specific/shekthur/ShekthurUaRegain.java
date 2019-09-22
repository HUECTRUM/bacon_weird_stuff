package com.bacon.characters.specific.shekthur;

import com.bacon.events.EventEmitter;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

import static com.bacon.events.EventType.P1_UA_CHANGE;
import static com.bacon.events.EventType.P2_UA_CHANGE;
import static com.bacon.events.GameEvent.event;
import static com.bacon.utils.ChoiceUtils.NO_CHOICES;
import static java.util.List.of;

public enum ShekthurUaRegain implements CardEffect {
    EFFECT;

    @Override
    public String effectName() {
        return "Shekthur UA token gain";
    }

    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        ShekthurUa ua = (ShekthurUa) player.character.ua();
        ua.gainTokens(player.beatHolder.damageDealt);

        EventEmitter.INSTANCE.emit(event(
                player.equals(gameInfoHolder.playerOne) ? P1_UA_CHANGE : P2_UA_CHANGE,
                of(player.character.ua().description())
        ));
    }
}
