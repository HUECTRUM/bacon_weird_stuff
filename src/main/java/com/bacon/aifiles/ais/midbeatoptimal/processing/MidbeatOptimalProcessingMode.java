package com.bacon.aifiles.ais.midbeatoptimal.processing;

import com.bacon.gamefiles.ioc.PlayerMode;
import com.bacon.gamefiles.selectors.ante.AnteSelector;
import com.bacon.gamefiles.selectors.choices.ChoiceSelector;
import com.bacon.gamefiles.selectors.choices.RandomChoiceSelector;
import com.bacon.gamefiles.selectors.clash.ClashBaseSelector;
import com.bacon.gamefiles.selectors.discards.DiscardSelector;
import com.bacon.gamefiles.selectors.effectorder.EffectOrderSelector;
import com.bacon.gamefiles.selectors.effectorder.SequentialEffectOrderSelector;
import com.bacon.gamefiles.selectors.pairs.PairSelector;
import com.bacon.gamefiles.selectors.player.PlayerSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MidbeatOptimalProcessingMode extends PlayerMode {
    @Autowired
    RandomChoiceSelector choiceSelector;
    @Autowired
    SequentialEffectOrderSelector effectOrderSelector;

    @Override
    public ClashBaseSelector clashBaseSelector() {
        return null;
    }

    @Override
    public AnteSelector anteSelector() {
        return null;
    }

    @Override
    public PlayerSelector playerSelector() {
        return null;
    }

    @Override
    public DiscardSelector discardSelector() {
        return null;
    }

    @Override
    public PairSelector pairSelector() {
        return null;
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
