package com.bacon.characters.specific.shekthur;

import com.bacon.cards.specific.shekthur.*;
import com.bacon.characters.Character;
import com.bacon.characters.UniqueAbility;
import com.bacon.gameobjects.cards.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static java.util.Arrays.asList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Shekthur extends Character {
    @Autowired
    private ShekthurUa shekthurUa;
    @Autowired
    private Brand brand;
    @Autowired
    private Unleashed unleashed;
    @Autowired
    private Reaver reaver;
    @Autowired
    private Combination combination;
    @Autowired
    private Jugular jugular;
    @Autowired
    private Spiral spiral;

    @Override
    public List<Card> bases() {
        return of(brand.BRAND);
    }

    @Override
    public List<Card> styles() {
        return asList(
                unleashed.UNLEASHED,
                reaver.REAVER,
                combination.COMBINATION,
                jugular.JUGULAR,
                spiral.SPIRAL
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
