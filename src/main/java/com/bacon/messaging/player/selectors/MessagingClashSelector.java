package com.bacon.messaging.player.selectors;

import com.bacon.gameobjects.cards.Card;
import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.player.Player;
import com.bacon.selectors.clash.ClashBaseSelector;
import lombok.AllArgsConstructor;

import static com.bacon.messaging.player.MessagingState.AWAIT_CLASH;

@AllArgsConstructor
public class MessagingClashSelector implements ClashBaseSelector {
    private PlayerMessaging messaging;

    @Override
    public Card selectBase(Player player, GameInfoHolder gameInfoHolder) {
        return messaging.await(AWAIT_CLASH);
    }
}
