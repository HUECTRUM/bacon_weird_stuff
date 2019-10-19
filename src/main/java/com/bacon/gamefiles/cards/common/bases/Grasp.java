package com.bacon.gamefiles.cards.common.bases;

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
import static com.bacon.gamefiles.gameobjects.enums.CardType.BASE;
import static com.bacon.gamefiles.gameobjects.triggers.EffectTrigger.OH;
import static com.bacon.gamefiles.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Grasp {
    @Autowired
    private ObjectProvider<MoveOpponent> moveOpponentProvider;

    private Map<EffectTrigger, List<CardEffect>> graspEffects() {
        return effectsMap(
                triggeredEffect(OH, moveOpponentProvider.getObject(of(-1, 1)))
        );
    }

    public Card grasp() {
        return Card
                .builder()
                .cardType(BASE)
                .name("Grasp")
                .minRange(1)
                .maxRange(1)
                .power(2)
                .priority(valueOf(5))
                .stunGuard(0)
                .soak(0)
                .cardEffects(graspEffects())
                .build();
    }
}
