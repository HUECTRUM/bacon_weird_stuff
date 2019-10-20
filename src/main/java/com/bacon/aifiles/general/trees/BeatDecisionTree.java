package com.bacon.aifiles.general.trees;

import com.bacon.aifiles.general.enums.GameDecisionType;
import com.bacon.gamefiles.holders.GameInfoHolder;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class BeatDecisionTree {
    public GameInfoHolder gameHolder;
    public Map<Object, BeatDecisionTree> edges;
    public BeatDecisionTree parent;
    public GameDecisionType decisionType;

    public BeatDecisionTree createChild(GameDecisionType decisionType, Object transition) {
        BeatDecisionTree child = new BeatDecisionTree(null, new HashMap<>(), this, decisionType);
        edges.put(transition, child);
        return child;
    }

    public static BeatDecisionTree fromGame(GameInfoHolder gameHolder, GameDecisionType decisionType) {
        return new BeatDecisionTree(gameHolder, new HashMap<>(), null, decisionType);
    }
}
