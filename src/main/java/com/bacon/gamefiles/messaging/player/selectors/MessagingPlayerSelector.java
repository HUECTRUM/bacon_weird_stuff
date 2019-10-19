package com.bacon.gamefiles.messaging.player.selectors;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.messaging.player.PlayerMessaging;
import com.bacon.gamefiles.messaging.player.state.AwaitPlayerMessagingState;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.player.PlayerSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.player.Player.fromCharacter;

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
