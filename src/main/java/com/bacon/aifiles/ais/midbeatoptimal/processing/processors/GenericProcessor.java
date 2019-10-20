package com.bacon.aifiles.ais.midbeatoptimal.processing.processors;

import com.bacon.aifiles.ais.midbeatoptimal.processing.MidbeatOptimalProcessingModeP1;
import com.bacon.aifiles.ais.midbeatoptimal.processing.containers.BeatDecisionTreeContainer;
import com.bacon.aifiles.ais.midbeatoptimal.processing.containers.GameChoiceContainer;
import com.bacon.aifiles.general.enums.GameDecisionType;
import com.bacon.aifiles.general.trees.BeatDecisionTree;
import com.bacon.aifiles.general.trees.BeatDecisionTreeHolder;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.persistencefiles.mappers.GameCopyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.bacon.aifiles.ais.midbeatoptimal.processing.keys.GameBeatKey.gameBeatKey;
import static com.bacon.aifiles.general.trees.BeatDecisionTree.fromGame;
import static com.bacon.gamefiles.statemachine.GameStates.GAME_END;
import static com.bacon.gamefiles.statemachine.GameStates.RECYCLE;

@Component
@AllArgsConstructor
public class GenericProcessor {
    private final BeatDecisionTreeContainer container;
    private final GameChoiceContainer gameChoiceContainer;

    private final GameCopyService gameCopyService;
    private final MidbeatOptimalProcessingModeP1 mode;

    public void processNext(GameInfoHolder holder, GameDecisionType type, List<?> choices) {
        BeatDecisionTreeHolder treeHolder = getCurrentHolder(holder);

        createNode(holder, type);
        for (Object decision : choices) {
            treeHolder.currentNode = treeHolder.tree.createChild(type, decision);
            gameChoiceContainer.nextChoices.put(holder.gameId, decision);
            gameCopyService.deepCopy(holder, mode, mode).runActionsUntil(Arrays.asList(GAME_END, RECYCLE));
        }
        holder.processingStopped = true;
    }

    private void createNode(GameInfoHolder holder, GameDecisionType type) {
        BeatDecisionTreeHolder treeHolder = getCurrentHolder(holder);

        BeatDecisionTree node = fromGame(gameCopyService.deepCopy(holder, mode, mode), type);

        if (treeHolder.currentNode == null) {
            treeHolder.tree = node;
        }
        treeHolder.currentNode = node;
    }

    private BeatDecisionTreeHolder getCurrentHolder(GameInfoHolder holder) {
        return container.decisionTrees.computeIfAbsent(
                gameBeatKey(holder.gameId, holder.beatInfoHolder.beatNumber),
                (key) -> new BeatDecisionTreeHolder());
    }
}
