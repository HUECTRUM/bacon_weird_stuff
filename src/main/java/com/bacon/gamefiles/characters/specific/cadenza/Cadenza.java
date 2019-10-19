package com.bacon.gamefiles.characters.specific.cadenza;

import com.bacon.gamefiles.cards.specific.cadenza.*;
import com.bacon.gamefiles.characters.Character;
import com.bacon.gamefiles.characters.UniqueAbility;
import com.bacon.gamefiles.gameobjects.cards.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Cadenza extends Character {
    @Autowired
    private CadenzaUa cadenzaUa;
    @Autowired
    private Press press;
    @Autowired
    private Battery battery;
    @Autowired
    private Clockwork clockwork;
    @Autowired
    private Grapnel grapnel;
    @Autowired
    private Hydraulic hydraulic;
    @Autowired
    private Mechanical mechanical;

    @Override
    public List<Card> bases() {
        return of(press.press());
    }

    @Override
    public List<Card> styles() {
        return of(battery.battery(), clockwork.clockwork(), grapnel.grapnel(), hydraulic.hydraulic(), mechanical.mechanical());
    }

    @Override
    public String name() {
        return "Cadenza";
    }

    @Override
    public String displayName() {
        return "C";
    }

    @Override
    public UniqueAbility ua() {
        return cadenzaUa;
    }

    @Override
    public Object[] additionalData() {
        return new Object[]{cadenzaUa.tokens};
    }

    @Override
    public void setData(Object[] data) {
        cadenzaUa.tokens = (Integer) data[0];
    }
}
