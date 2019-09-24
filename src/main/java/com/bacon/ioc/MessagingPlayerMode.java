package com.bacon.ioc;

import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.selectors.ante.AnteSelector;
import com.bacon.selectors.choices.ChoiceSelector;
import com.bacon.selectors.clash.ClashBaseSelector;
import com.bacon.selectors.discards.DiscardSelector;
import com.bacon.selectors.effectorder.EffectOrderSelector;
import com.bacon.selectors.pairs.PairSelector;
import com.bacon.selectors.player.PlayerSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class MessagingPlayerMode extends PlayerMode {
    @Autowired
    private PlayerMessaging messaging;

    @Override
    ClashBaseSelector clashBaseSelector() {
        return messaging.selectorContainer.clashSelector;
    }

    @Override
    AnteSelector anteSelector() {
        return messaging.selectorContainer.anteSelector;
    }

    @Override
    PlayerSelector playerSelector() {
        return messaging.selectorContainer.playerSelector;
    }

    @Override
    DiscardSelector discardSelector() {
        return messaging.selectorContainer.discardSelector;
    }

    @Override
    PairSelector pairSelector() {
        return messaging.selectorContainer.pairSelector;
    }

    @Override
    ChoiceSelector choiceSelector() {
        return messaging.selectorContainer.choiceSelector;
    }

    @Override
    EffectOrderSelector effectOrderSelector() {
        return messaging.selectorContainer.effectOrderSelector;
    }
}
