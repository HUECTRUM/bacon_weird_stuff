package com.bacon.messaging.player.selectors;

import com.bacon.gameobjects.cards.Card;
import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.messaging.player.state.AwaitDiscardsMessagingState;
import com.bacon.player.Player;
import com.bacon.selectors.discards.DiscardSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessagingDiscardSelector implements DiscardSelector {
    @Autowired
    private PlayerMessaging messaging;
    @Autowired
    private AwaitDiscardsMessagingState state;

    @Override
    public List<List<Card>> selectDiscards(Player player, GameInfoHolder holder) {
        return messaging.await(state, holder.gameId);
    }
}
