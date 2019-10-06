package com.bacon.cards.common.bases;

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
import static com.bacon.gameobjects.enums.CardType.BASE;
import static com.bacon.gameobjects.triggers.EffectTrigger.BA;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Drive {
    @Autowired
    private ObjectProvider<Advance> advanceProvider;

    private Map<EffectTrigger, List<CardEffect>> driveEffects() {
        return effectsMap(
                triggeredEffect(BA, advanceProvider.getObject(asList(1, 2)))
        );
    }

    public Card drive() {
        return Card
                .builder()
                .cardType(BASE)
                .name("Drive")
                .minRange(1)
                .maxRange(1)
                .power(3)
                .priority(valueOf(4))
                .stunGuard(0)
                .soak(0)
                .cardEffects(driveEffects())
                .build();
    }
}
