package com.bacon.gamefiles.messaging.player.selectors;

import com.bacon.gamefiles.attacks.AttackPair;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.messaging.player.PlayerMessaging;
import com.bacon.gamefiles.messaging.player.state.AwaitPairMessagingState;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.pairs.PairSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagingPairSelector implements PairSelector {
    @Autowired
    private PlayerMessaging messaging;
    @Autowired
    private AwaitPairMessagingState state;

    @Override
    public AttackPair selectPair(Player player, GameInfoHolder holder) {
        return messaging.await(state, holder.gameId);
    }
}
