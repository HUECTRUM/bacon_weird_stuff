package com.bacon.ioc;

import com.bacon.holders.GameInfoHolder;
import com.bacon.selectors.ante.AnteSelector;
import com.bacon.selectors.choices.ChoiceSelector;
import com.bacon.selectors.clash.ClashBaseSelector;
import com.bacon.selectors.discards.DiscardSelector;
import com.bacon.selectors.pairs.PairSelector;
import com.bacon.selectors.player.PlayerSelector;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class PlayerMode {
    public ClashBaseSelector clashBaseSelector;
    public AnteSelector anteSelector;
    public PlayerSelector playerSelector;
    public DiscardSelector discardSelector;
    public PairSelector pairSelector;
    public ChoiceSelector choiceSelector;

    public void inject(GameInfoHolder holder, int order) {
        //todo: reflection?
        holder.resolversContainer.priorityResolver
                .clashResolver.clashBaseSelectors.orderedModes.put(order, clashBaseSelector);
        holder.resolversContainer
                .anteResolver.selectors.orderedModes.put(order, anteSelector);
        holder.resolversContainer
                .characterSelectionResolver.playerSelectors.orderedModes.put(order, playerSelector);
        holder.resolversContainer
                .discardResolver.discardSelectors.orderedModes.put(order, discardSelector);
        holder.resolversContainer
                .pairSelectionResolver.pairSelectors.orderedModes.put(order, pairSelector);
        holder.resolversContainer
                .effectsResolver.choiceSelectors.orderedModes.put(order, choiceSelector);
    }
}