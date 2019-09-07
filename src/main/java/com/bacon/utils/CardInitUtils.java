package com.bacon.utils;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.cards.CardTriggeredEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.triggers.EffectTrigger.*;
import static java.util.Arrays.stream;
import static java.util.Map.of;
import static java.util.stream.Collectors.toMap;

public class CardInitUtils {
    //todo: init from trigger values
    public static Map<EffectTrigger, List<CardEffect>> EMPTY_EFFECTS_MAP = new HashMap<>(of(
            REVEAL, new ArrayList<>(),
            SOB, new ArrayList<>(),
            BA, new ArrayList<>(),
            BEFORE_RANGE_CHECK, new ArrayList<>(),
            OH, new ArrayList<>(),
            BEFORE_DAMAGE, new ArrayList<>(),
            OD, new ArrayList<>(),
            AA, new ArrayList<>(),
            EOB, new ArrayList<>()
    ));

    public static Map<EffectTrigger, List<CardEffect>> effectsMap(CardTriggeredEffect ...effects) {
        Map<EffectTrigger, List<CardEffect>> map = deepCopy(EMPTY_EFFECTS_MAP);
        stream(effects).forEach(e -> map.get(e.effectTrigger).add(e.cardEffect));
        return map;
    }

    private static Map<EffectTrigger, List<CardEffect>> deepCopy(Map<EffectTrigger, List<CardEffect>> map) {
        return map
                .entrySet()
                .stream()
                .collect(toMap(e -> e.getKey(), e -> new ArrayList(e.getValue())));
    }
}
