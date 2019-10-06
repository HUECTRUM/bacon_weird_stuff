package com.bacon.messaging.player.selectors;

import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.messaging.player.state.AwaitPlayerMessagingState;
import com.bacon.player.Player;
import com.bacon.selectors.player.PlayerSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.player.Player.fromCharacter;

@Component
public class MessagingPlayerSelector implements PlayerSelector {
    @Autowired
    private PlayerMessaging messaging;
    @Autowired
    private AwaitPlayerMessagingState state;

    @Override
    public Player selectPlayer(GameInfoHolder holder) {
        return fromCharacter(messaging.await(state, holder.gameId));
    }
}
