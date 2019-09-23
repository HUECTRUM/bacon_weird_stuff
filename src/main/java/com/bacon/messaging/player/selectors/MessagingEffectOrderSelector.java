package com.bacon.messaging.player.selectors;

import com.bacon.events.EventEmitter;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.player.Player;
import com.bacon.selectors.effectorder.EffectOrderSelector;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.bacon.events.EventType.EFFECT_ORDER_SELECT;
import static com.bacon.events.GameEvent.event;
import static com.bacon.messaging.player.MessagingState.AWAIT_EFFECT_ORDER;
import static com.bacon.utils.StreamUtils.mapList;

@AllArgsConstructor
public class MessagingEffectOrderSelector implements EffectOrderSelector {
    private PlayerMessaging messaging;

    @Override
    public List<Integer> effectOrder(Player player, GameInfoHolder gameInfoHolder, List<CardEffect> effects) {
        EventEmitter.INSTANCE.emit(event(EFFECT_ORDER_SELECT, mapList(effects, CardEffect::effectName)));
        return messaging.await(AWAIT_EFFECT_ORDER);
    }
}
