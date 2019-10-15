package com.bacon.gamefiles.ioc;

import com.bacon.gamefiles.selectors.ante.AnteSelector;
import com.bacon.gamefiles.selectors.ante.RandomAnteSelector;
import com.bacon.gamefiles.selectors.choices.ChoiceSelector;
import com.bacon.gamefiles.selectors.choices.RandomChoiceSelector;
import com.bacon.gamefiles.selectors.clash.ClashBaseSelector;
import com.bacon.gamefiles.selectors.clash.RandomBaseSelector;
import com.bacon.gamefiles.selectors.discards.DiscardSelector;
import com.bacon.gamefiles.selectors.discards.RandomDiscardSelector;
import com.bacon.gamefiles.selectors.effectorder.EffectOrderSelector;
import com.bacon.gamefiles.selectors.effectorder.SequentialEffectOrderSelector;
import com.bacon.gamefiles.selectors.pairs.PairSelector;
import com.bacon.gamefiles.selectors.pairs.RandomPairSelector;
import com.bacon.gamefiles.selectors.player.DummyPlayerSelector;
import com.bacon.gamefiles.selectors.player.PlayerSelector;
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
