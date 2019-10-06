package com.bacon.messaging.player.selectors;

import com.bacon.events.EventEmitter;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.messaging.player.state.AwaitChoiceMessagingState;
import com.bacon.player.Player;
import com.bacon.selectors.choices.ChoiceSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.events.EventType.EFFECT_CHOICE;
import static com.bacon.events.GameEvent.event;
import static java.util.List.of;

@Component
public class MessagingChoiceSelector implements ChoiceSelector {
    @Autowired
    private PlayerMessaging messaging;
    @Autowired
    private AwaitChoiceMessagingState state;
    @Autowired
    private EventEmitter eventEmitter;

    @Override
    public int choose(GameInfoHolder holder, Player player, CardEffect effect, List<?> choices) {
        eventEmitter.emit(event(EFFECT_CHOICE, of(effect.effectName(), choices), holder.gameId));
        return messaging.await(state, holder.gameId);
    }
}
