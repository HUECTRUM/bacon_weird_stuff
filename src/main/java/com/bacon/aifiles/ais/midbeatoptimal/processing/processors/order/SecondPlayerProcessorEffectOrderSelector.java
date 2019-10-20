package com.bacon.aifiles.ais.midbeatoptimal.processing.processors.order;

import com.bacon.aifiles.ais.midbeatoptimal.processing.containers.GameChoiceContainer;
import com.bacon.aifiles.ais.midbeatoptimal.processing.permutations.PermutationGenerator;
import com.bacon.aifiles.ais.midbeatoptimal.processing.processors.GenericProcessor;
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

import static com.bacon.aifiles.ais.midbeatoptimal.processing.processors.order.ProcessorEffectOrderSelectorHelper.orderEffects;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
@RequiredArgsConstructor
@Slf4j
public class SecondPlayerProcessorEffectOrderSelector implements EffectOrderSelector {
    @Autowired
    private GameChoiceContainer gameChoiceContainer;
    @Autowired
    private GenericProcessor genericProcessor;
    @Autowired
    private PermutationGenerator permutationGenerator;

    @Override
    public List<Integer> effectOrder(Player player, GameInfoHolder gameInfoHolder, List<CardEffect> effects) {
        return orderEffects(
                gameChoiceContainer,
                genericProcessor,
                permutationGenerator,
                gameInfoHolder,
                effects,
                player.playerId.equals(gameInfoHolder.playerTwo.playerId)
        );
    }
}
