package com.bacon.cards.common.bases;

import com.bacon.effects.movement.common.MoveOpponent;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gameobjects.enums.CardType.BASE;
import static com.bacon.gameobjects.triggers.EffectTrigger.OH;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;

public class Grasp {
    private static Map<EffectTrigger, List<CardEffect>> GRASP_EFFECTS_MAP = effectsMap(
            triggeredEffect(OH, new MoveOpponent(asList(-1, 1)))
    );

    public static final Card GRASP = Card
            .builder()
            .cardType(BASE)
            .name("Grasp")
            .minRange(1)
            .maxRange(1)
            .power(2)
            .priority(valueOf(5))
            .stunGuard(0)
            .soak(0)
            .cardEffects(GRASP_EFFECTS_MAP)
            .build();
}
