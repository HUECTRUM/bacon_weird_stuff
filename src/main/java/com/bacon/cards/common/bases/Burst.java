package com.bacon.cards.common.bases;

import com.bacon.effects.movement.common.Retreat;
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
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Burst {
    @Autowired
    private ObjectProvider<Retreat> retreatProvider;

    private Map<EffectTrigger, List<CardEffect>> BURST_EFFECTS_MAP = effectsMap(
        triggeredEffect(SOB, retreatProvider.getObject(of(1, 2)))
    );

    public final Card BURST = Card
            .builder()
            .cardType(BASE)
            .name("Burst")
            .minRange(2)
            .maxRange(3)
            .power(3)
            .priority(valueOf(1))
            .stunGuard(0)
            .soak(0)
            .cardEffects(BURST_EFFECTS_MAP)
            .build();
}
