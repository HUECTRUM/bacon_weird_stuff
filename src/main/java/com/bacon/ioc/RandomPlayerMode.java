package com.bacon.ioc;

import com.bacon.selectors.ante.AnteSelector;
import com.bacon.selectors.ante.RandomAnteSelector;
import com.bacon.selectors.choices.ChoiceSelector;
import com.bacon.selectors.choices.RandomChoiceSelector;
import com.bacon.selectors.clash.ClashBaseSelector;
import com.bacon.selectors.clash.RandomBaseSelector;
import com.bacon.selectors.discards.DiscardSelector;
import com.bacon.selectors.discards.RandomDiscardSelector;
import com.bacon.selectors.effectorder.EffectOrderSelector;
import com.bacon.selectors.effectorder.SequentialEffectOrderSelector;
import com.bacon.selectors.pairs.PairSelector;
import com.bacon.selectors.pairs.RandomPairSelector;
import com.bacon.selectors.player.DummyPlayerSelector;
import com.bacon.selectors.player.PlayerSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomPlayerMode extends PlayerMode {
    @Autowired
    private RandomBaseSelector clashBaseSelector;
    @Autowired
    RandomAnteSelector anteSelector;
    @Autowired
    DummyPlayerSelector playerSelector;
    @Autowired
    RandomDiscardSelector discardSelector;
    @Autowired
    RandomPairSelector pairSelector;
    @Autowired
    RandomChoiceSelector choiceSelector;
    @Autowired
    SequentialEffectOrderSelector effectOrderSelecor;

    @Override
    ClashBaseSelector clashBaseSelector() {
        return clashBaseSelector;
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
        return effectOrderSelecor;
    }
}
