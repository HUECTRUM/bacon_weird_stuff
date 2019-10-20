package com.bacon.aifiles.ais.midbeatoptimal.processing.processors;

import com.bacon.aifiles.ais.midbeatoptimal.processing.containers.GameChoiceContainer;
import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.choices.ChoiceSelector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.aifiles.general.enums.GameDecisionType.OPPONENT;
import static com.bacon.aifiles.general.enums.GameDecisionType.PLAYER;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
@RequiredArgsConstructor
@Slf4j
public class ProcessorChoiceSelector implements ChoiceSelector {
    @Autowired
    private GameChoiceContainer gameChoiceContainer;
    @Autowired
    private GenericProcessor genericProcessor;

    private final Player calculatedPlayer;

    @Override
    public int choose(GameInfoHolder holder, Player player, CardEffect effect, List<?> choices) {
        Object choice = gameChoiceContainer.nextChoices.remove(holder.gameId);
        if (choice != null) {
            return (Integer) choice;
        }

        genericProcessor.processNext(
                holder,
                player.playerId.equals(calculatedPlayer.playerId) ? PLAYER : OPPONENT,
                choices
        );
        return 0;
    }
}
