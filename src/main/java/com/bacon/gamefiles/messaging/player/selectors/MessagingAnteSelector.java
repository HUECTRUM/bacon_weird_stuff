package com.bacon.gamefiles.messaging.player.selectors;

import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.messaging.player.PlayerMessaging;
import com.bacon.gamefiles.messaging.player.state.AwaitAnteMessagingState;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.ante.AnteSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.events.EventType.ANTE_SELECT;
import static com.bacon.gamefiles.events.GameEvent.event;

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
        eventEmitter.emit(event(ANTE_SELECT, choices, holder.gameId));
        return messaging.await(state, holder.gameId);
    }
}
