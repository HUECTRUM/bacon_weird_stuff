package com.bacon.statemachine.resolvers;

import com.bacon.gameobjects.cards.Card;
import com.bacon.holders.GameInfoHolder;
import com.bacon.ioc.selector.SelectorHolder;
import com.bacon.selectors.discards.DiscardSelector;
import com.bacon.statemachine.conditions.StateTransitionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Slf4j
@Scope(value = SCOPE_PROTOTYPE)
public class DiscardResolver {
    public SelectorHolder<DiscardSelector> discardSelectors = new SelectorHolder<>();

    public StateTransitionCondition selectDiscards(GameInfoHolder holder) {
        List<List<Card>> playerOneDiscards = discardSelectors.get(holder.playerOne, holder).selectDiscards(holder.playerOne);
        List<List<Card>> playerTwoDiscards = discardSelectors.get(holder.playerTwo, holder).selectDiscards(holder.playerTwo);

        holder.playerOne.discardOne.addAll(playerOneDiscards.get(0));
        holder.playerOne.discardTwo.addAll(playerOneDiscards.get(1));

        holder.playerTwo.discardOne.addAll(playerTwoDiscards.get(0));
        holder.playerTwo.discardTwo.addAll(playerTwoDiscards.get(1));

        log.info("Discard state routine ended. Players one {} two {}",
                holder.playerOne, holder.playerTwo);
        return EMPTY;
    }
}
