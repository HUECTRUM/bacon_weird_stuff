package com.bacon.gamefiles.utils;

import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.gameobjects.cards.CardTriggeredEffect;
import com.bacon.gamefiles.gameobjects.triggers.EffectTrigger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Functions.identity;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public class CardInitUtils {
    public static Map<EffectTrigger, List<CardEffect>> EMPTY_EFFECTS_MAP =
            stream(EffectTrigger.values())
                    .collect(toMap(identity(), entry -> new ArrayList<>()));

    public static Map<EffectTrigger, List<CardEffect>> effectsMap(CardTriggeredEffect... effects) {
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
