package com.bacon.cards.common.bases;

import com.bacon.effects.movement.common.AdvanceDodge;
import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.bacon.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gameobjects.enums.CardType.BASE;
import static com.bacon.gameobjects.triggers.EffectTrigger.SOB;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Dodge {
    @Autowired
    private ObjectProvider<AdvanceDodge> advanceDodgeProvider;

    private Map<EffectTrigger, List<CardEffect>> DODGE_EFFECTS_MAP = effectsMap(
            triggeredEffect(SOB, advanceDodgeProvider.getObject(List.of(1, 2, 3, -1, -2, -3)))
    );

    public final Card DODGE = Card
            .builder()
            .cardType(BASE)
            .name("Dodge")
            .minRange(null)
            .maxRange(null)
            .power(0)
            .priority(valueOf(3))
            .stunGuard(0)
            .soak(0)
            .cardEffects(DODGE_EFFECTS_MAP)
            .build();
}
