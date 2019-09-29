package com.bacon.cards.specific.cadenza;

import com.bacon.effects.movement.common.Advance;
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
import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.gameobjects.triggers.EffectTrigger.EOB;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Mechanical {
    @Autowired
    private ObjectProvider<Advance> advanceProvider;

    private Map<EffectTrigger, List<CardEffect>> MECHANICAL_EFFECTS = effectsMap(
            triggeredEffect(EOB, advanceProvider.getObject(of(0, 1, 2, 3)))
    );

    public final Card MECHANICAL = Card
            .builder()
            .cardType(STYLE)
            .name("Mechanical")
            .minRange(0)
            .maxRange(0)
            .power(2)
            .priority(valueOf(-2))
            .stunGuard(0)
            .soak(0)
            .cardEffects(MECHANICAL_EFFECTS)
            .build();
}
