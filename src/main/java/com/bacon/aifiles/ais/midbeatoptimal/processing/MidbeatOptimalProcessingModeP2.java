package com.bacon.aifiles.ais.midbeatoptimal.processing;

import com.bacon.aifiles.ais.midbeatoptimal.processing.processors.choice.SecondPlayerProcessorChoiceSelector;
import com.bacon.aifiles.ais.midbeatoptimal.processing.processors.order.SecondPlayerProcessorEffectOrderSelector;
import com.bacon.gamefiles.ioc.PlayerMode;
import com.bacon.gamefiles.selectors.ante.AnteSelector;
import com.bacon.gamefiles.selectors.choices.ChoiceSelector;
import com.bacon.gamefiles.selectors.clash.ClashBaseSelector;
import com.bacon.gamefiles.selectors.discards.DiscardSelector;
import com.bacon.gamefiles.selectors.effectorder.EffectOrderSelector;
import com.bacon.gamefiles.selectors.pairs.PairSelector;
import com.bacon.gamefiles.selectors.player.PlayerSelector;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MidbeatOptimalProcessingModeP2 extends PlayerMode {
    private final SecondPlayerProcessorChoiceSelector choiceSelector;
    private final SecondPlayerProcessorEffectOrderSelector effectOrderSelector;

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
