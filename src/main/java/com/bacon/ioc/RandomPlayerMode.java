package com.bacon.ioc;

import com.bacon.selectors.ante.RandomAnteSelector;
import com.bacon.selectors.choices.RandomChoiceSelector;
import com.bacon.selectors.clash.RandomBaseSelector;
import com.bacon.selectors.discards.RandomDiscardSelector;
import com.bacon.selectors.effectorder.SequentialEffectOrderSelector;
import com.bacon.selectors.pairs.RandomPairSelector;
import com.bacon.selectors.player.DummyPlayerSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomPlayerMode extends PlayerMode {
    @Autowired
    public RandomPlayerMode(
            RandomBaseSelector clashBaseSelector,
            RandomAnteSelector anteSelector,
            DummyPlayerSelector playerSelector,
            RandomDiscardSelector discardSelector,
            RandomPairSelector pairSelector,
            RandomChoiceSelector choiceSelector,
            SequentialEffectOrderSelector effectOrderSelecor
    ) {
        super(clashBaseSelector, anteSelector, playerSelector, discardSelector,
                pairSelector, choiceSelector, effectOrderSelecor);
    }
}
