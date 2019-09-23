package com.bacon.messaging.player.selectors;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.player.Player;
import com.bacon.selectors.effectorder.EffectOrderSelector;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.bacon.messaging.player.MessagingState.AWAIT_EFFECT_ORDER;

@AllArgsConstructor
public class MessagingEffectOrderSelector implements EffectOrderSelector {
    private PlayerMessaging messaging;

    @Override
    public List<Integer> effectOrder(Player player, GameInfoHolder gameInfoHolder, List<CardEffect> effects) {
        return messaging.await(AWAIT_EFFECT_ORDER);
    }
}
