package com.bacon.gamefiles.characters;

import com.bacon.gamefiles.cards.common.CommonCards;
import com.bacon.gamefiles.gameobjects.cards.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.utils.StreamUtils.concatLists;
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

    //These 2 methods are used to copy and move games states. They provide direct access to the
    //character or UA details that would otherwise be inaccessible since are hidden behind common interfaces
    //(e.g. setting Shekthur's token amount)
    public abstract Object[] additionalData();
    public abstract void setData(Object[] data);
}
