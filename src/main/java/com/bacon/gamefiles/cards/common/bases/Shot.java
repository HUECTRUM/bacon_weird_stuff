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
public class Shot {
    public Card shot() {
        return Card
                .builder()
                .cardType(BASE)
                .name("Shot")
                .minRange(1)
                .maxRange(4)
                .power(3)
                .priority(valueOf(2))
                .stunGuard(2)
                .soak(0)
                .cardEffects(EMPTY_EFFECTS_MAP)
                .build();
    }
}
