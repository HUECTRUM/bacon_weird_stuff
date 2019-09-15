package com.bacon.messaging.player.selectors;

import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.player.Player;
import com.bacon.selectors.player.PlayerSelector;
import lombok.AllArgsConstructor;

import static com.bacon.messaging.player.MessagingState.AWAIT_PLAYER;

@AllArgsConstructor
public class MessagingPlayerSelector implements PlayerSelector {
    private PlayerMessaging messaging;

    @Override
    public Player selectPlayer() {
        return messaging.await(AWAIT_PLAYER);
    }
}
