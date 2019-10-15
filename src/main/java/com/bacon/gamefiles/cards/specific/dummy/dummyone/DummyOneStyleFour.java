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
public class DummyOneStyleFour {
    public Card dummyOneStyleFour() {
        return Card
                .builder()
                .cardType(STYLE)
                .name("DummyOneStyleFour")
                .minRange(0)
                .maxRange(0)
                .power(0)
                .priority(valueOf(1))
                .stunGuard(0)
                .soak(0)
                .cardEffects(EMPTY_EFFECTS_MAP)
                .build();
    }
}
