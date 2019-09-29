package com.bacon.characters.specific.shekthur;

import com.bacon.characters.Character;
import com.bacon.characters.UniqueAbility;
import com.bacon.gameobjects.cards.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.cards.specific.shekthur.Brand.BRAND;
import static com.bacon.cards.specific.shekthur.Combination.COMBINATION;
import static com.bacon.cards.specific.shekthur.Jugular.JUGULAR;
import static com.bacon.cards.specific.shekthur.Reaver.REAVER;
import static com.bacon.cards.specific.shekthur.Spiral.SPIRAL;
import static com.bacon.cards.specific.shekthur.Unleashed.UNLEASHED;
import static com.google.common.collect.ImmutableList.of;
import static java.util.Arrays.asList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Shekthur implements Character {
    @Autowired
    private ShekthurUa shekthurUa;

    @Override
    public List<Card> bases() {
        return of(BRAND);
    }

    @Override
    public List<Card> styles() {
        return asList(
                UNLEASHED,
                REAVER,
                COMBINATION,
                JUGULAR,
                SPIRAL
        );
    }

    @Override
    public String name() {
        return "Shekthur";
    }

    @Override
    public String displayName() {
        return "Sh";
    }

    @Override
    public UniqueAbility ua() {
        return shekthurUa;
    }
}
