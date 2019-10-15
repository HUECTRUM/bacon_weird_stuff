package com.bacon.gamefiles.messaging.player.selectors;

import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.messaging.player.PlayerMessaging;
import com.bacon.gamefiles.messaging.player.state.AwaitEffectOrderMessagingState;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.effectorder.EffectOrderSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.events.EventType.EFFECT_ORDER_SELECT;
import static com.bacon.gamefiles.events.GameEvent.event;
import static com.bacon.gamefiles.utils.StreamUtils.mapList;

@Component
public class MessagingEffectOrderSelector implements EffectOrderSelector {
    @Autowired
    private PlayerMessaging messaging;
    @Autowired
    private AwaitEffectOrderMessagingState state;
    @Autowired
    private EventEmitter eventEmitter;

    @Override
    public List<Integer> effectOrder(Player player, GameInfoHolder holder, List<CardEffect> effects) {
        eventEmitter.emit(event(EFFECT_ORDER_SELECT, mapList(effects, CardEffect::effectName), holder.gameId));
        return messaging.await(state, holder.gameId);
    }
}
