package com.bacon.gamefiles.ioc;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.selectors.ante.AnteSelector;
import com.bacon.gamefiles.selectors.choices.ChoiceSelector;
import com.bacon.gamefiles.selectors.clash.ClashBaseSelector;
import com.bacon.gamefiles.selectors.discards.DiscardSelector;
import com.bacon.gamefiles.selectors.effectorder.EffectOrderSelector;
import com.bacon.gamefiles.selectors.pairs.PairSelector;
import com.bacon.gamefiles.selectors.player.PlayerSelector;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class PlayerMode {
    public abstract ClashBaseSelector clashBaseSelector();
    public abstract AnteSelector anteSelector();
    public abstract PlayerSelector playerSelector();
    public abstract DiscardSelector discardSelector();
    public abstract PairSelector pairSelector();
    public abstract ChoiceSelector choiceSelector();
    public abstract EffectOrderSelector effectOrderSelector();

    public void inject(GameInfoHolder holder, int order) {
        //todo: reflection?
        holder.resolversContainer.priorityResolver
                .clashResolver.clashBaseSelectors.orderedModes.put(order, clashBaseSelector());
        holder.resolversContainer
                .anteResolver.selectors.orderedModes.put(order, anteSelector());
        holder.resolversContainer
                .characterSelectionResolver.playerSelectors.orderedModes.put(order, playerSelector());
        holder.resolversContainer
                .discardResolver.discardSelectors.orderedModes.put(order, discardSelector());
        holder.resolversContainer
                .pairSelectionResolver.pairSelectors.orderedModes.put(order, pairSelector());
        holder.resolversContainer
                .effectsResolver.choiceSelectors.orderedModes.put(order, choiceSelector());
        holder.resolversContainer
                .effectsResolver.effectOrderSelectorSelectors.orderedModes.put(order, effectOrderSelector());
    }
}
