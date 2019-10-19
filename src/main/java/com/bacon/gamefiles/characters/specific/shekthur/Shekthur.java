package com.bacon.gamefiles.characters.specific.shekthur;

import com.bacon.gamefiles.cards.specific.shekthur.*;
import com.bacon.gamefiles.characters.Character;
import com.bacon.gamefiles.characters.UniqueAbility;
import com.bacon.gamefiles.gameobjects.cards.Card;
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
        return of(brand.brand());
    }

    @Override
    public List<Card> styles() {
        return asList(
                unleashed.unleashed(),
                reaver.reaver(),
                combination.combination(),
                jugular.jugular(),
                spiral.spiral()
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

    @Override
    public Object[] additionalData() {
        return new Object[] {shekthurUa.tokens};
    }

    @Override
    public void setData(Object[] data) {
        shekthurUa.tokens = (Integer)data[0];
    }
}
