package com.bacon.messaging.player.selectors;

import com.bacon.events.EventEmitter;
import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.player.Player;
import com.bacon.selectors.ante.AnteSelector;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.bacon.events.EventType.ANTE_SELECT;
import static com.bacon.events.GameEvent.event;
import static com.bacon.messaging.player.MessagingState.AWAIT_ANTE;

@AllArgsConstructor
public class MessagingAnteSelector implements AnteSelector {
    private PlayerMessaging messaging;

    @Override
    public int anteChoice(GameInfoHolder holder, Player player, List<?> choices) {
        EventEmitter.INSTANCE.emit(event(ANTE_SELECT, choices));
        return messaging.await(AWAIT_ANTE);
    }
}
