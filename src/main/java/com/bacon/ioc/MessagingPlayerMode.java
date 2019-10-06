package com.bacon.ioc;

import com.bacon.messaging.player.selectors.*;
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
    private MessagingAnteSelector anteSelector;
    @Autowired
    private MessagingChoiceSelector choiceSelector;
    @Autowired
    private MessagingClashSelector clashSelector;
    @Autowired
    private MessagingDiscardSelector discardSelector;
    @Autowired
    private MessagingEffectOrderSelector effectOrderSelector;
    @Autowired
    private MessagingPairSelector pairSelector;
    @Autowired
    private MessagingPlayerSelector playerSelector;

    @Override
    ClashBaseSelector clashBaseSelector() {
        return clashSelector;
    }

    @Override
    AnteSelector anteSelector() {
        return anteSelector;
    }

    @Override
    PlayerSelector playerSelector() {
        return playerSelector;
    }

    @Override
    DiscardSelector discardSelector() {
        return discardSelector;
    }

    @Override
    PairSelector pairSelector() {
        return pairSelector;
    }

    @Override
    ChoiceSelector choiceSelector() {
        return choiceSelector;
    }

    @Override
    EffectOrderSelector effectOrderSelector() {
        return effectOrderSelector;
    }
}
