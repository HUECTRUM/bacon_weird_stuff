package com.bacon.aifiles.ais.midbeatoptimal.processing.processors.order;

import com.bacon.aifiles.ais.midbeatoptimal.processing.containers.GameChoiceContainer;
import com.bacon.aifiles.ais.midbeatoptimal.processing.permutations.PermutationGenerator;
import com.bacon.aifiles.ais.midbeatoptimal.processing.processors.GenericProcessor;
import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.holders.GameInfoHolder;

import java.util.List;

import static com.bacon.aifiles.general.enums.GameDecisionType.OPPONENT;
import static com.bacon.aifiles.general.enums.GameDecisionType.PLAYER;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class ProcessorEffectOrderSelectorHelper {
    public static List<Integer> orderEffects(GameChoiceContainer gameChoiceContainer, GenericProcessor genericProcessor,
                                            PermutationGenerator permutationGenerator, GameInfoHolder gameInfoHolder,
                                            List<CardEffect> effects, boolean samePlayer) {
        Object choice = gameChoiceContainer.nextChoices.remove(gameInfoHolder.gameId);
        if (choice != null) {
            return (List<Integer>) choice;
        }

        List<Integer> identityPermutation = range(0, effects.size()).boxed().collect(toList());
        genericProcessor.processNext(
                gameInfoHolder,
                samePlayer ? PLAYER : OPPONENT,
                permutationGenerator.allPermutations(identityPermutation)
        );
        return identityPermutation;
    }
}
