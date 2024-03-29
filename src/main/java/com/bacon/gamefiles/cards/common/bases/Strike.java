package com.bacon.gamefiles.cards.common.bases;

import com.bacon.gamefiles.gameobjects.cards.Card;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.gameobjects.enums.CardType.BASE;
import static com.bacon.gamefiles.utils.CardInitUtils.EMPTY_EFFECTS_MAP;
import static java.math.BigDecimal.valueOf;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Strike {
    public Card strike() {
        return Card
                .builder()
                .cardType(BASE)
                .name("Strike")
                .minRange(1)
                .maxRange(1)
                .power(4)
                .priority(valueOf(3))
                .stunGuard(5)
                .soak(0)
                .cardEffects(EMPTY_EFFECTS_MAP)
                .build();
    }
}
