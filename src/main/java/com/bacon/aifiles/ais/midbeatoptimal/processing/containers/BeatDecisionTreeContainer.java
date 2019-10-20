package com.bacon.aifiles.ais.midbeatoptimal.processing.containers;

import com.bacon.aifiles.ais.midbeatoptimal.processing.keys.GameBeatKey;
import com.bacon.aifiles.general.trees.BeatDecisionTreeHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BeatDecisionTreeContainer {
    public Map<GameBeatKey, BeatDecisionTreeHolder> decisionTrees = new HashMap<>();
}
