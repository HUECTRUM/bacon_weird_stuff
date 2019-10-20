package com.bacon.aifiles.ais.midbeatoptimal.processing.processors.choice;

import com.bacon.aifiles.ais.midbeatoptimal.processing.containers.GameChoiceContainer;
import com.bacon.aifiles.ais.midbeatoptimal.processing.processors.GenericProcessor;
import com.bacon.gamefiles.holders.GameInfoHolder;

import java.util.List;

import static com.bacon.aifiles.general.enums.GameDecisionType.OPPONENT;
import static com.bacon.aifiles.general.enums.GameDecisionType.PLAYER;

public class ProcessorChoiceSelectorHelper {
    public static int choose(GameChoiceContainer gameChoiceContainer, GenericProcessor genericProcessor,
                            GameInfoHolder holder, List<?> choices, boolean samePlayer) {
        Object choice = gameChoiceContainer.nextChoices.remove(holder.gameId);
        if (choice != null) {
            return (Integer) choice;
        }

        genericProcessor.processNext(holder, samePlayer ? PLAYER : OPPONENT, choices);
        return 0;
    }
}
