package com.bacon.effects.specific.shekthur;

import com.bacon.characters.specific.shekthur.ShekthurUa;
import com.bacon.events.EventEmitter;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

import static com.bacon.events.EventType.P1_UA_CHANGE;
import static com.bacon.events.EventType.P2_UA_CHANGE;
import static com.bacon.events.GameEvent.event;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.List.of;
import static java.util.stream.Collectors.toList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class BrandAA implements CardEffect {
    @Autowired
    private EventEmitter emitter;

    @Override
    public String effectName() {
        return "Brand AA lifesteal";
    }

    @Override
    public List<Integer> choices(Player player, GameInfoHolder gameInfoHolder) {
        ShekthurUa ua = (ShekthurUa) player.character.ua();
        return IntStream
                .range(0, ua.tokens / 2 + 1)
                .map(i -> i * 2)
                .boxed()
                .collect(toList());
    }

    @Override
    public void apply(Player player, GameInfoHolder gameInfoHolder, int choiceIndex) {
        Player opponent = gameInfoHolder.infoHelper.opponent(gameInfoHolder, player);
        ShekthurUa ua = (ShekthurUa) player.character.ua();

        int tokensSpent = choices(player, gameInfoHolder).get(choiceIndex);

        ua.tokens -= tokensSpent;
        player.health = min(player.health + tokensSpent, 20);
        opponent.health = max(opponent.health - tokensSpent, 1);

        emitter.emit(event(
                player.equals(gameInfoHolder.playerOne) ? P1_UA_CHANGE : P2_UA_CHANGE,
                of(player.character.ua().description())
        ));
    }
}
