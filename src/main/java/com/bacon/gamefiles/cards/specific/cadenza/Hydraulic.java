package com.bacon.gamefiles.cards.specific.cadenza;

import com.bacon.gamefiles.effects.movement.common.Advance;
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
import static com.bacon.gamefiles.gameobjects.triggers.EffectTrigger.BA;
import static com.bacon.gamefiles.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Hydraulic {
    @Autowired
    private ObjectProvider<Advance> advanceProvider;

    private Map<EffectTrigger, List<CardEffect>> hydraulicEffects() {
        return effectsMap(
                triggeredEffect(BA, advanceProvider.getObject(of(1)))
        );
    }

    public Card hydraulic() {
        return Card
                .builder()
                .cardType(STYLE)
                .name("Hydraulic")
                .minRange(0)
                .maxRange(0)
                .power(2)
                .priority(valueOf(-1))
                .stunGuard(0)
                .soak(1)
                .cardEffects(hydraulicEffects())
                .build();
    }
}
