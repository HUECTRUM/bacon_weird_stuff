package com.bacon.characters;

import com.bacon.cards.common.CommonCards;
import com.bacon.gameobjects.cards.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.utils.StreamUtils.concatLists;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public abstract class Character {
    @Autowired
    private CommonCards commonCards;

    public abstract List<Card> bases();
    public abstract List<Card> styles();

    public abstract String name();
    public abstract String displayName();

    public abstract UniqueAbility ua();

    public List<Card> basesKit() {
        return concatLists(bases(), commonCards.commonBases());
    }

    public List<Card> stylesKit() {
        return concatLists(styles(), commonCards.commonStyles());
    }
}
