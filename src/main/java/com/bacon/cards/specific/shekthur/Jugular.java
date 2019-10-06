package com.bacon.cards.specific.shekthur;

import com.bacon.effects.movement.common.MoveOpponent;
import com.bacon.effects.specific.shekthur.JugularEoB;
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
import static com.bacon.gameobjects.triggers.EffectTrigger.OH;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Jugular {
    @Autowired
    private ObjectProvider<MoveOpponent> moveOpponentProvider;
    @Autowired
    private ObjectProvider<JugularEoB> jugularEobProvider;

    private Map<EffectTrigger, List<CardEffect>> jugularEffects() {
        return effectsMap(
                triggeredEffect(OH, moveOpponentProvider.getObject(of(-1, 1))),
                triggeredEffect(EOB, jugularEobProvider.getObject())
        );
    }

    public Card jugular() {
        return Card
                .builder()
                .cardType(STYLE)
                .name("Jugular")
                .minRange(0)
                .maxRange(0)
                .power(1)
                .priority(valueOf(2))
                .stunGuard(0)
                .soak(0)
                .cardEffects(jugularEffects())
                .build();
    }
}
