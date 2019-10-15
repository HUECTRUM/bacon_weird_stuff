package com.bacon.gamefiles.characters.specific.shekthur;

import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.events.EventType.P1_UA_CHANGE;
import static com.bacon.gamefiles.events.EventType.P2_UA_CHANGE;
import static com.bacon.gamefiles.events.GameEvent.event;
import static com.bacon.gamefiles.utils.ChoiceUtils.NO_CHOICES;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class ShekthurUaRegain implements CardEffect {
    @Autowired
    private EventEmitter emitter;

    @Override
    public String effectName() {
        return "Shekthur UA token gain";
    }

    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        return NO_CHOICES;
    }

    @Override
    public void apply(Player player, GameInfoHolder holder, int choiceIndex) {
        ShekthurUa ua = (ShekthurUa) player.character.ua();
        ua.gainTokens(player.beatHolder.damageDealt);

        emitter.emit(event(
                player.equals(holder.playerOne) ? P1_UA_CHANGE : P2_UA_CHANGE,
                of(player.character.ua().description()),
                holder.gameId
        ));
    }
}
