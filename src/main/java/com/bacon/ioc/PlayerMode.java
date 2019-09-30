package com.bacon.ioc;

import com.bacon.holders.GameInfoHolder;
import com.bacon.selectors.ante.AnteSelector;
import com.bacon.selectors.choices.ChoiceSelector;
import com.bacon.selectors.clash.ClashBaseSelector;
import com.bacon.selectors.discards.DiscardSelector;
import com.bacon.selectors.effectorder.EffectOrderSelector;
import com.bacon.selectors.pairs.PairSelector;
import com.bacon.selectors.player.PlayerSelector;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public abstract class PlayerMode {
    abstract ClashBaseSelector clashBaseSelector();
    abstract AnteSelector anteSelector();
    abstract PlayerSelector playerSelector();
    abstract DiscardSelector discardSelector();
    abstract PairSelector pairSelector();
    abstract ChoiceSelector choiceSelector();
    abstract EffectOrderSelector effectOrderSelector();

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
