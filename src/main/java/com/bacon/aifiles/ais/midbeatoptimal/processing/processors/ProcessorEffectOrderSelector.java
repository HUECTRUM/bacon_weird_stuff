package com.bacon.aifiles.ais.midbeatoptimal.processing.processors;

import com.bacon.aifiles.ais.midbeatoptimal.processing.containers.GameChoiceContainer;
import com.bacon.aifiles.ais.midbeatoptimal.processing.permutations.PermutationGenerator;
import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.effectorder.EffectOrderSelector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.aifiles.general.enums.GameDecisionType.OPPONENT;
import static com.bacon.aifiles.general.enums.GameDecisionType.PLAYER;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
@RequiredArgsConstructor
@Slf4j
public class ProcessorEffectOrderSelector implements EffectOrderSelector {
    @Autowired
    private GameChoiceContainer gameChoiceContainer;
    @Autowired
    private GenericProcessor genericProcessor;
    @Autowired
    private PermutationGenerator permutationGenerator;

    private final Player calculatedPlayer;

    @Override
    public List<Integer> effectOrder(Player player, GameInfoHolder gameInfoHolder, List<CardEffect> effects) {
        Object choice = gameChoiceContainer.nextChoices.remove(gameInfoHolder.gameId);
        if (choice != null) {
            return (List<Integer>) choice;
        }

        List<Integer> identityPermutation = range(0, effects.size()).boxed().collect(toList());
        genericProcessor.processNext(
                gameInfoHolder,
                player.playerId.equals(calculatedPlayer.playerId) ? PLAYER : OPPONENT,
                permutationGenerator.allPermutations(identityPermutation)
        );
        return identityPermutation;
    }
}
