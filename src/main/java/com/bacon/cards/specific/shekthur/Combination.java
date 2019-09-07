package com.bacon.cards.specific.shekthur;

import com.bacon.effects.specific.shekthur.CombinationBeforeRangeMiss;
import com.bacon.effects.specific.shekthur.CombinationOH;
import com.bacon.effects.specific.shekthur.CominationISoak;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.gameobjects.triggers.EffectTrigger.*;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;

public class Combination {
    private static Map<EffectTrigger, List<CardEffect>> COMBINATION_EFFECTS = effectsMap(
            triggeredEffect(BEFORE_RANGE_CHECK, new CombinationBeforeRangeMiss()),
            triggeredEffect(OH, new CombinationOH()),
            triggeredEffect(BEFORE_DAMAGE, new CominationISoak())
    );

    public static final Card COMBINATION = Card
            .builder()
            .cardType(STYLE)
            .name("Combination")
            .minRange(0)
            .maxRange(0)
            .power(2)
            .priority(valueOf(0))
            .stunGuard(0)
            .soak(0)
            .cardEffects(COMBINATION_EFFECTS)
            .build();
}
