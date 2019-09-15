package com.bacon.messaging.player.selectors;

import com.bacon.attacks.AttackPair;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.player.Player;
import com.bacon.selectors.pairs.PairSelector;
import lombok.AllArgsConstructor;

import static com.bacon.messaging.player.MessagingState.AWAIT_PAIR;

@AllArgsConstructor
public class MessagingPairSelector implements PairSelector {
    private PlayerMessaging messaging;

    @Override
    public AttackPair selectPair(Player player) {
        return messaging.await(AWAIT_PAIR);
    }
}
