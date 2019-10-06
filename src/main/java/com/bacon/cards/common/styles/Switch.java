package com.bacon.cards.common.styles;

import com.bacon.gameobjects.cards.Card;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.gameobjects.enums.CardType.STYLE;
import static com.bacon.utils.CardInitUtils.EMPTY_EFFECTS_MAP;
import static java.math.BigDecimal.valueOf;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Switch {
    public Card switchCard() {
        return Card
                .builder()
                .cardType(STYLE)
                .name("Switch")
                .minRange(0)
                .maxRange(0)
                .power(0)
                .priority(valueOf(0))
                .stunGuard(0)
                .soak(0)
                .cardEffects(EMPTY_EFFECTS_MAP)
                .build();
    }
}
