package com.bacon.player;

import com.bacon.attacks.AttackPairBonus;
import com.bacon.characters.Character;
import com.bacon.gameobjects.cards.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bacon.utils.StreamUtils.filterList;
import static java.util.Collections.EMPTY_LIST;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class Player {
    private static int counter = 0; //TODO: A way to distinguish display names in mirror matches

    public String playerId;
    public int health;

    public Character character;

    public List<Card> discardOne;
    public List<Card> discardTwo;

    public Map<Integer, List<AttackPairBonus>> bonuses;

    public List<Card> availableBases() {
        return filterOutDiscards(character.basesKit());
    }

    public List<Card> availableBasesAfterClash(List<Card> playedCards) {
        return filterList(
                character.basesKit(),
                card -> !discardOne.contains(card) && !discardTwo.contains(card) && !playedCards.contains(card)
        );
    }

    public List<Card> availableStyles() {
        return filterOutDiscards(character.stylesKit());
    }

    private List<Card> filterOutDiscards(List<Card> cards) {
        return filterList(cards, card -> !discardOne.contains(card) && !discardTwo.contains(card));
    }

    public void attachBonus(int beatNum, AttackPairBonus bonus) {
        bonuses.putIfAbsent(beatNum, EMPTY_LIST);
        bonuses.get(beatNum).add(bonus);
    }

    //constructors
    public static Player fromCharacter(Character character) {
        return Player.builder()
                .playerId(character.displayName() + (counter++))
                .health(20)
                .character(character)
                .discardOne(new ArrayList<>(EMPTY_LIST))
                .discardTwo(new ArrayList<>(EMPTY_LIST))
                .bonuses(new HashMap<>())
                .build();
    }
}
