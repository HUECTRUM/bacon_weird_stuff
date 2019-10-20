package com.bacon.gamefiles.ioc;

import com.bacon.gamefiles.messaging.player.selectors.*;
import com.bacon.gamefiles.selectors.ante.AnteSelector;
import com.bacon.gamefiles.selectors.choices.ChoiceSelector;
import com.bacon.gamefiles.selectors.clash.ClashBaseSelector;
import com.bacon.gamefiles.selectors.discards.DiscardSelector;
import com.bacon.gamefiles.selectors.effectorder.EffectOrderSelector;
import com.bacon.gamefiles.selectors.pairs.PairSelector;
import com.bacon.gamefiles.selectors.player.PlayerSelector;
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
    public ClashBaseSelector clashBaseSelector() {
        return clashSelector;
    }

    @Override
    public AnteSelector anteSelector() {
        return anteSelector;
    }

    @Override
    public PlayerSelector playerSelector() {
        return playerSelector;
    }

    @Override
    public DiscardSelector discardSelector() {
        return discardSelector;
    }

    @Override
    public PairSelector pairSelector() {
        return pairSelector;
    }

    @Override
    public ChoiceSelector choiceSelector() {
        return choiceSelector;
    }

    @Override
    public EffectOrderSelector effectOrderSelector() {
        return effectOrderSelector;
    }
}
