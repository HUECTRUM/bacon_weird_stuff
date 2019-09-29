package com.bacon.messaging.player.selectors;

import com.bacon.gameobjects.cards.Card;
import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.messaging.player.state.AwaitClashMessagingState;
import com.bacon.player.Player;
import com.bacon.selectors.clash.ClashBaseSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagingClashSelector implements ClashBaseSelector {
    @Autowired
    private PlayerMessaging messaging;
    @Autowired
    private AwaitClashMessagingState state;

    @Override
    public Card selectBase(Player player, GameInfoHolder gameInfoHolder) {
        return messaging.await(state);
    }
}
