package com.bacon.cards.specific.shekthur;

import com.bacon.effects.movement.common.Retreat;
import com.bacon.effects.specific.shekthur.UnleashedEob;
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
import static com.bacon.gameobjects.triggers.EffectTrigger.AA;
import static com.bacon.gameobjects.triggers.EffectTrigger.EOB;
import static com.bacon.utils.CardInitUtils.effectsMap;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Unleashed {
    @Autowired
    private ObjectProvider<Retreat> retreatProvider;
    @Autowired
    private ObjectProvider<UnleashedEob> unleashedEobProvider;

    private Map<EffectTrigger, List<CardEffect>> UNLEASHED_EFFECTS = effectsMap(
            triggeredEffect(AA, retreatProvider.getObject(of(1, 2))),
            triggeredEffect(EOB, unleashedEobProvider.getObject())
    );

    public final Card UNLEASHED = Card
            .builder()
            .cardType(STYLE)
            .name("Unleashed")
            .minRange(0)
            .maxRange(1)
            .power(-1)
            .priority(valueOf(0))
            .stunGuard(0)
            .soak(0)
            .cardEffects(UNLEASHED_EFFECTS)
            .build();
}
