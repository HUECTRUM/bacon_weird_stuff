package com.bacon.characters;

import com.bacon.gameobjects.Card;

import java.util.List;

import static com.bacon.cards.common.CommonCards.COMMON_BASES;
import static com.bacon.cards.common.CommonCards.COMMON_STYLES;
import static com.bacon.utils.StreamUtils.concatLists;

public interface Character {
    List<Card> bases();
    List<Card> styles();

    default List<Card> basesKit() {
        return concatLists(bases(), COMMON_BASES);
    }

    default List<Card> stylesKit() {
        return concatLists(styles(), COMMON_STYLES);
    }
}
