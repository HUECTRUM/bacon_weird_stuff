package com.bacon.gamefiles.cards.specific.cadenza;

import com.bacon.gamefiles.effects.specific.cadenza.PressDamageBoost;
import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.gameobjects.triggers.EffectTrigger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.bacon.gamefiles.gameobjects.cards.CardTriggeredEffect.triggeredEffect;
import static com.bacon.gamefiles.gameobjects.enums.CardType.BASE;
import static com.bacon.gamefiles.gameobjects.triggers.EffectTrigger.BEFORE_RANGE_CHECK;
import static com.bacon.gamefiles.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Press {
    @Autowired
    private ObjectProvider<PressDamageBoost> pressDamageBoostProvider;

    private Map<EffectTrigger, List<CardEffect>> pressEffects() {
        return effectsMap(
                triggeredEffect(BEFORE_RANGE_CHECK, pressDamageBoostProvider.getObject())
        );
    }

    public Card press() {
        return Card
                .builder()
                .cardType(BASE)
                .name("Press")
                .minRange(1)
                .maxRange(2)
                .power(1)
                .priority(valueOf(-0))
                .stunGuard(6)
                .soak(0)
                .cardEffects(pressEffects())
                .build();
    }
}

