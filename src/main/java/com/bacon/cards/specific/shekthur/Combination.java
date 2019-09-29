package com.bacon.cards.specific.shekthur;

import com.bacon.effects.specific.shekthur.CombinationBeforeRangeMiss;
import com.bacon.effects.specific.shekthur.CombinationOH;
import com.bacon.effects.specific.shekthur.CominationISoak;
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
import static com.bacon.gameobjects.triggers.EffectTrigger.*;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Combination {
    @Autowired
    private ObjectProvider<CombinationBeforeRangeMiss> combinationBeforeRangeMissProvider;
    @Autowired
    private ObjectProvider<CombinationOH> combinationOHProvider;
    @Autowired
    private ObjectProvider<CominationISoak> combinationISoakProvider;

    private Map<EffectTrigger, List<CardEffect>> COMBINATION_EFFECTS = effectsMap(
            triggeredEffect(BEFORE_RANGE_CHECK, combinationBeforeRangeMissProvider.getObject()),
            triggeredEffect(OH, combinationOHProvider.getObject()),
            triggeredEffect(BEFORE_DAMAGE, combinationISoakProvider.getObject())
    );

    public final Card COMBINATION = Card
            .builder()
            .cardType(STYLE)
            .name("Combination")
            .minRange(0)
            .maxRange(0)
            .power(2)
            .priority(valueOf(0))
            .stunGuard(0)
            .soak(0)
            .cardEffects(COMBINATION_EFFECTS)
            .build();
}
