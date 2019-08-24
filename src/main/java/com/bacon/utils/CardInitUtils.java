package com.bacon.utils;

import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.triggers.EffectTrigger.*;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Map.of;

public class CardInitUtils {
    public static Map<EffectTrigger, List<CardEffect>> emptyEffectsMap() {
        return new HashMap<>(of(
                REVEAL, new ArrayList<CardEffect>(EMPTY_LIST),
                SOB, new ArrayList<CardEffect>(EMPTY_LIST),
                BA, new ArrayList<CardEffect>(EMPTY_LIST),
                OH, new ArrayList<CardEffect>(EMPTY_LIST),
                OD, new ArrayList<CardEffect>(EMPTY_LIST),
                AA, new ArrayList<CardEffect>(EMPTY_LIST),
                EOB, new ArrayList<CardEffect>(EMPTY_LIST)
        ));
    }
}
