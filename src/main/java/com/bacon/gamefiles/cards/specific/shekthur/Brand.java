package com.bacon.gamefiles.cards.specific.shekthur;

import com.bacon.gamefiles.effects.specific.shekthur.BrandAA;
import com.bacon.gamefiles.effects.specific.shekthur.BrandISG;
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
import static com.bacon.gamefiles.gameobjects.triggers.EffectTrigger.AA;
import static com.bacon.gamefiles.gameobjects.triggers.EffectTrigger.BEFORE_DAMAGE;
import static com.bacon.gamefiles.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Brand {
    @Autowired
    private ObjectProvider<BrandAA> brandAAProvider;
    @Autowired
    private ObjectProvider<BrandISG> brandISGProvider;

    private Map<EffectTrigger, List<CardEffect>> brandEffects() {
        return effectsMap(
                triggeredEffect(BEFORE_DAMAGE, brandISGProvider.getObject()),
                triggeredEffect(AA, brandAAProvider.getObject())
        );
    }

    public Card brand() {
        return Card
                .builder()
                .cardType(BASE)
                .name("Brand")
                .minRange(1)
                .maxRange(2)
                .power(3)
                .priority(valueOf(2))
                .stunGuard(0)
                .soak(0)
                .cardEffects(brandEffects())
                .build();
    }
}
