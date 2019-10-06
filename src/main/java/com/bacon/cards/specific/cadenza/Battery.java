package com.bacon.cards.specific.cadenza;

import com.bacon.effects.specific.cadenza.BatteryPrioBoost;
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
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Battery {
    @Autowired
    private ObjectProvider<BatteryPrioBoost> batteryPrioBoostProvider;

    private Map<EffectTrigger, List<CardEffect>> batteryEffects() {
        return effectsMap(
                triggeredEffect(EOB, batteryPrioBoostProvider.getObject())
        );
    }

    public Card battery() {
        return Card
                .builder()
                .cardType(STYLE)
                .name("Battery")
                .minRange(0)
                .maxRange(0)
                .power(1)
                .priority(valueOf(-1))
                .stunGuard(0)
                .soak(0)
                .cardEffects(batteryEffects())
                .build();
    }
}
