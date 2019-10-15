package com.bacon.gamefiles.cards.specific.dummy.dummyone;

import com.bacon.gamefiles.gameobjects.cards.Card;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.gameobjects.enums.CardType.STYLE;
import static com.bacon.gamefiles.utils.CardInitUtils.EMPTY_EFFECTS_MAP;
import static java.math.BigDecimal.valueOf;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class DummyOneStyleThree {
    public Card dummyOneStyleThree() {
        return Card
                .builder()
                .cardType(STYLE)
                .name("DummyOneStyleThree")
                .minRange(3)
                .maxRange(3)
                .power(0)
                .priority(valueOf(0))
                .stunGuard(0)
                .soak(0)
                .cardEffects(EMPTY_EFFECTS_MAP)
                .build();
    }
}
