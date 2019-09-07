package com.bacon.cards.common.bases;

import com.bacon.effects.movement.common.Advance;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gameobjects.enums.CardType.BASE;
import static com.bacon.gameobjects.triggers.EffectTrigger.BA;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;

public class Drive {
    private static Map<EffectTrigger, List<CardEffect>> DRIVE_EFFECTS_MAP = effectsMap(
            triggeredEffect(BA, new Advance(asList(1, 2)))
    );

    public static final Card DRIVE = Card
            .builder()
            .cardType(BASE)
            .name("Drive")
            .minRange(1)
            .maxRange(1)
            .power(3)
            .priority(valueOf(4))
            .stunGuard(0)
            .soak(0)
            .cardEffects(DRIVE_EFFECTS_MAP)
            .build();
}
