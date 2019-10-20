package com.bacon.aifiles.ais.midbeatoptimal.processing.processors.choice;

import com.bacon.aifiles.ais.midbeatoptimal.processing.containers.GameChoiceContainer;
import com.bacon.aifiles.ais.midbeatoptimal.processing.processors.GenericProcessor;
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

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
@RequiredArgsConstructor
@Slf4j
public class SecondPlayerProcessorChoiceSelector implements ChoiceSelector {
    @Autowired
    private GameChoiceContainer gameChoiceContainer;
    @Autowired
    private GenericProcessor genericProcessor;

    @Override
    public int choose(GameInfoHolder holder, Player player, CardEffect effect, List<?> choices) {
        return ProcessorChoiceSelectorHelper.choose(
                gameChoiceContainer,
                genericProcessor,
                holder,
                choices,
                player.playerId.equals(holder.playerTwo.playerId)
        );
    }
}
