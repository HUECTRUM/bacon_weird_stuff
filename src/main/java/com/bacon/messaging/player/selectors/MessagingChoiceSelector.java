package com.bacon.messaging.player.selectors;

import com.bacon.events.EventEmitter;
import com.bacon.events.GameEvent;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.player.Player;
import com.bacon.selectors.choices.ChoiceSelector;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.bacon.events.EventType.EFFECT_CHOICE;
import static com.bacon.messaging.player.MessagingState.AWAIT_CHOICE;

@AllArgsConstructor
public class MessagingChoiceSelector implements ChoiceSelector {
    private PlayerMessaging messaging;

    @Override
    public int choose(GameInfoHolder holder, Player player, CardEffect effect, List<?> choices) {
        EventEmitter.INSTANCE.emit(GameEvent.event(EFFECT_CHOICE, List.of(effect.effectName(), choices)));
        return messaging.await(AWAIT_CHOICE);
    }
}
