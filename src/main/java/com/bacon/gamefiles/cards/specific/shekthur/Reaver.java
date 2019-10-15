package com.bacon.gamefiles.cards.specific.shekthur;

import com.bacon.gamefiles.effects.movement.common.Advance;
import com.bacon.gamefiles.effects.specific.shekthur.ReaverODPush;
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
import static com.bacon.gamefiles.gameobjects.triggers.EffectTrigger.EOB;
import static com.bacon.gamefiles.gameobjects.triggers.EffectTrigger.OD;
import static com.bacon.gamefiles.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Reaver {
    @Autowired
    private ObjectProvider<Advance> advanceProvider;
    @Autowired
    private ObjectProvider<ReaverODPush> reaverODPushProvider;

    private Map<EffectTrigger, List<CardEffect>> reaverEffects() {
        return effectsMap(
                triggeredEffect(OD, reaverODPushProvider.getObject()),
                triggeredEffect(EOB, advanceProvider.getObject(of(1, 2)))
        );
    }

    public Card reaver() {
        return Card
                .builder()
                .cardType(STYLE)
                .name("Reaver")
                .minRange(0)
                .maxRange(1)
                .power(0)
                .priority(valueOf(0))
                .stunGuard(0)
                .soak(0)
                .cardEffects(reaverEffects())
                .build();
    }
}
