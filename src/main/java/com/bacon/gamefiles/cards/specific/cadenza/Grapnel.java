package com.bacon.gamefiles.cards.specific.cadenza;

import com.bacon.gamefiles.effects.movement.common.MoveOpponent;
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
import static com.bacon.gamefiles.gameobjects.enums.CardType.STYLE;
import static com.bacon.gamefiles.gameobjects.triggers.EffectTrigger.OH;
import static com.bacon.gamefiles.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Grapnel {
    @Autowired
    private ObjectProvider<MoveOpponent> moveOpponentProvider;

    private Map<EffectTrigger, List<CardEffect>> grapnelEffects() {
        return effectsMap(
                triggeredEffect(OH, moveOpponentProvider.getObject(of(0, -1, -2, -3)))
        );
    }

    public Card grapnel() {
        return Card
                .builder()
                .cardType(STYLE)
                .name("Grapnel")
                .minRange(2)
                .maxRange(4)
                .power(0)
                .priority(valueOf(0))
                .stunGuard(0)
                .soak(0)
                .cardEffects(grapnelEffects())
                .build();
    }
}
