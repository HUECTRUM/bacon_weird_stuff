package com.bacon.gamefiles.player;

import com.bacon.gamefiles.attacks.AttackPairBonus;
import com.bacon.gamefiles.attacks.AttackPairBonusType;
import com.bacon.gamefiles.characters.Character;
import com.bacon.gamefiles.gameobjects.cards.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static com.bacon.gamefiles.utils.StreamUtils.count;
import static com.bacon.gamefiles.utils.StreamUtils.filterList;
import static java.util.Collections.singletonList;

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

    public PlayerBeatHolder beatHolder;
    public List<PlayerBeatHolder> prevBeats;

    public Map<Integer, List<AttackPairBonus>> bonuses;

    public boolean hasBonus(int beatNumber, AttackPairBonusType type) {
        return count(
                bonuses.getOrDefault(beatNumber, new ArrayList<>()),
                b -> b.type == type
        ) > 0;
    }

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
        bonuses.putIfAbsent(beatNum, new ArrayList<>());
        bonuses.get(beatNum).add(bonus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerId.equals(player.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }

    //constructors
    public static Player fromCharacter(Character character) {
        return Player.builder()
                .playerId(character.displayName() + (counter++))
                .health(20)
                .character(character)
                .discardOne(new ArrayList<>())
                .discardTwo(new ArrayList<>())
                .bonuses(new HashMap<>())
                .prevBeats(new ArrayList<>(singletonList(new PlayerBeatHolder())))
                .beatHolder(new PlayerBeatHolder())
                .build();
    }
}
