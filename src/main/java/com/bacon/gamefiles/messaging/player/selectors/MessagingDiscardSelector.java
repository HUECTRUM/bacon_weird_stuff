package com.bacon.gamefiles.messaging.player.selectors;

import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.messaging.player.PlayerMessaging;
import com.bacon.gamefiles.messaging.player.state.AwaitDiscardsMessagingState;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.discards.DiscardSelector;
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
