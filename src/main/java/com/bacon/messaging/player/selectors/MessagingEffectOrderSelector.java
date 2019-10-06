package com.bacon.messaging.player.selectors;

import com.bacon.events.EventEmitter;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.messaging.player.state.AwaitEffectOrderMessagingState;
import com.bacon.player.Player;
import com.bacon.selectors.effectorder.EffectOrderSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.events.EventType.EFFECT_ORDER_SELECT;
import static com.bacon.events.GameEvent.event;
import static com.bacon.utils.StreamUtils.mapList;

@Component
public class MessagingEffectOrderSelector implements EffectOrderSelector {
    @Autowired
    private PlayerMessaging messaging;
    @Autowired
    private AwaitEffectOrderMessagingState state;
    @Autowired
    private EventEmitter eventEmitter;

    @Override
    public List<Integer> effectOrder(Player player, GameInfoHolder gameInfoHolder, List<CardEffect> effects) {
        eventEmitter.emit(event(EFFECT_ORDER_SELECT, mapList(effects, CardEffect::effectName)));
        return messaging.await(state);
    }
}
