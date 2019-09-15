package com.bacon.messaging.player.selectors;

import com.bacon.gameobjects.cards.Card;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.player.Player;
import com.bacon.selectors.discards.DiscardSelector;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.bacon.messaging.player.MessagingState.AWAIT_DISCARDS;

@AllArgsConstructor
public class MessagingDiscardSelector implements DiscardSelector {
    private PlayerMessaging messaging;

    @Override
    public List<List<Card>> selectDiscards(Player player) {
        return messaging.await(AWAIT_DISCARDS);
    }
}
