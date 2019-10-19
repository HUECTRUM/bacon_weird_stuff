package com.bacon.gamefiles.messaging.player.selectors;

import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.messaging.player.PlayerMessaging;
import com.bacon.gamefiles.messaging.player.state.AwaitClashMessagingState;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.clash.ClashBaseSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagingClashSelector implements ClashBaseSelector {
    @Autowired
    private PlayerMessaging messaging;
    @Autowired
    private AwaitClashMessagingState state;

    @Override
    public Card selectBase(Player player, GameInfoHolder holder) {
        return messaging.await(state, holder.gameId);
    }
}
