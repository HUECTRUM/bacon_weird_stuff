package com.bacon.messaging.player.selectors;

import com.bacon.attacks.AttackPair;
import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.messaging.player.state.AwaitPairMessagingState;
import com.bacon.player.Player;
import com.bacon.selectors.pairs.PairSelector;
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
