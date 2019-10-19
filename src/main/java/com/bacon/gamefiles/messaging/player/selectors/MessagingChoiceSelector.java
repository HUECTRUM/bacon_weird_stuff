package com.bacon.gamefiles.messaging.player.selectors;

import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.messaging.player.PlayerMessaging;
import com.bacon.gamefiles.messaging.player.state.AwaitChoiceMessagingState;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.choices.ChoiceSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.events.EventType.EFFECT_CHOICE;
import static com.bacon.gamefiles.events.GameEvent.event;
import static java.util.List.of;

@Component
public class MessagingChoiceSelector implements ChoiceSelector {
    @Autowired
    private PlayerMessaging messaging;
    @Autowired
    private AwaitChoiceMessagingState state;
    @Autowired
    private EventEmitter eventEmitter;

    @Override
    public int choose(GameInfoHolder holder, Player player, CardEffect effect, List<?> choices) {
        eventEmitter.emit(event(EFFECT_CHOICE, of(effect.effectName(), choices), holder.gameId));
        return messaging.await(state, holder.gameId);
    }
}
