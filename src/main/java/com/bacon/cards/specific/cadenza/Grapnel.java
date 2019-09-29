package com.bacon.cards.specific.cadenza;

import com.bacon.effects.movement.common.MoveOpponent;
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
import static com.bacon.gameobjects.triggers.EffectTrigger.OH;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Grapnel {
    @Autowired
    private ObjectProvider<MoveOpponent> moveOpponentProvider;

    private Map<EffectTrigger, List<CardEffect>> GRAPNEL_EFFECTS = effectsMap(
            triggeredEffect(OH, moveOpponentProvider.getObject(of(0, -1, -2, -3)))
    );

    public final Card GRAPNEL = Card
            .builder()
            .cardType(STYLE)
            .name("Grapnel")
            .minRange(2)
            .maxRange(4)
            .power(0)
            .priority(valueOf(0))
            .stunGuard(0)
            .soak(0)
            .cardEffects(GRAPNEL_EFFECTS)
            .build();
}
