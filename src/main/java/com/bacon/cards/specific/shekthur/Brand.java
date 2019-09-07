package com.bacon.cards.specific.shekthur;

import com.bacon.effects.specific.shekthur.BrandAA;
import com.bacon.effects.specific.shekthur.BrandISG;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;

import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gameobjects.enums.CardType.BASE;
import static com.bacon.gameobjects.triggers.EffectTrigger.AA;
import static com.bacon.gameobjects.triggers.EffectTrigger.BEFORE_DAMAGE;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;

public class Brand {
    private static Map<EffectTrigger, List<CardEffect>> BRAND_EFFECTS = effectsMap(
            triggeredEffect(BEFORE_DAMAGE, new BrandISG()),
            triggeredEffect(AA, new BrandAA())
    );

    public static final Card BRAND = Card
            .builder()
            .cardType(BASE)
            .name("Brand")
            .minRange(1)
            .maxRange(2)
            .power(3)
            .priority(valueOf(2))
            .stunGuard(0)
            .soak(0)
            .cardEffects(BRAND_EFFECTS)
            .build();
}
