package com.bacon.messaging.player.selectors;

import com.bacon.events.EventEmitter;
import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.messaging.player.state.AwaitAnteMessagingState;
import com.bacon.player.Player;
import com.bacon.selectors.ante.AnteSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.events.EventType.ANTE_SELECT;
import static com.bacon.events.GameEvent.event;

@Component
public class MessagingAnteSelector implements AnteSelector {
    @Autowired
    private PlayerMessaging messaging;
    @Autowired
    private AwaitAnteMessagingState state;
    @Autowired
    private EventEmitter eventEmitter;

    @Override
    public int anteChoice(GameInfoHolder holder, Player player, List<?> choices) {
        eventEmitter.emit(event(ANTE_SELECT, choices));
        return messaging.await(state);
    }
}
