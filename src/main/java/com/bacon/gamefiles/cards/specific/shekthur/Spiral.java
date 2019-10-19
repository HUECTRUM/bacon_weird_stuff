package com.bacon.gamefiles.cards.specific.shekthur;

import com.bacon.gamefiles.effects.specific.shekthur.SpiralBA;
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
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Spiral {
    @Autowired
    private ObjectProvider<SpiralBA> spiralBAProvider;

    private Map<EffectTrigger, List<CardEffect>> spiralEffects() {
        return effectsMap(
                triggeredEffect(BA, spiralBAProvider.getObject())
        );
    }

    public Card spiral() {
        return Card
                .builder()
                .cardType(STYLE)
                .name("Spiral")
                .minRange(0)
                .maxRange(0)
                .power(0)
                .priority(valueOf(-1))
                .stunGuard(0)
                .soak(0)
                .cardEffects(spiralEffects())
                .build();
    }
}
