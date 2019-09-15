package com.bacon.messaging.player.selectors;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.player.Player;
import com.bacon.selectors.choices.ChoiceSelector;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.bacon.messaging.player.MessagingState.AWAIT_CHOICE;

@AllArgsConstructor
public class MessagingChoiceSelector implements ChoiceSelector {
    private PlayerMessaging messaging;

    @Override
    public int choose(GameInfoHolder holder, Player player, CardEffect effect, List<?> choices) {
        return messaging.await(AWAIT_CHOICE);
    }
}
