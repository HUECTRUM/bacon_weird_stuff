package com.bacon.gamefiles.attacks;

import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.gamefiles.gameobjects.triggers.EffectTrigger;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.utils.StreamUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.ToIntFunction;

import static com.bacon.gamefiles.attacks.AttackPairBonusType.*;
import static com.bacon.gamefiles.utils.StreamUtils.*;
import static java.math.BigDecimal.ZERO;
import static java.util.Collections.EMPTY_LIST;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttackPair {
    public List<Card> cards;

    public List<CardEffect> triggeredEffects(EffectTrigger trigger) {
        return flatMapList(mapList(cards, card -> card.cardEffects.get(trigger)));
    }

    public BigDecimal totalPriority(List<AttackPairBonus> bonuses) {
        BigDecimal cardPrio = cards
                .stream()
                .map(card -> card.priority)
                .reduce(ZERO, BigDecimal::add);
        BigDecimal bonusPrio = filterList(bonuses, b -> b.type == PRIORITY)
                .stream()
                .map(x -> (BigDecimal)x.value)
                .reduce(ZERO, BigDecimal::add);
        return cardPrio.add(bonusPrio);
    }

    public BigDecimal totalPriority(Player player, int beatNum) {
        return totalPriority(player.bonuses.getOrDefault(beatNum, EMPTY_LIST));
    }

    public Integer minRange(List<AttackPairBonus> bonuses) {
        if (cards.stream().anyMatch(card -> card.minRange == null)) {
            return null;
        }
        return calculateValue(card -> card.minRange, MINRANGE, bonuses);
    }

    public Integer minRange(Player player, int beatNum) {
        return minRange(player.bonuses.getOrDefault(beatNum, EMPTY_LIST));
    }

    public Integer maxRange(List<AttackPairBonus> bonuses) {
        if (cards.stream().anyMatch(card -> card.maxRange == null)) {
            return null;
        }
        return calculateValue(card -> card.maxRange, MAXRANGE, bonuses);
    }

    public Integer maxRange(Player player, int beatNum) {
        return maxRange(player.bonuses.getOrDefault(beatNum, EMPTY_LIST));
    }

    public int soak(List<AttackPairBonus> bonuses) {
        return calculateValue(card -> card.soak, SOAK, bonuses);
    }

    public Integer soak(Player player, int beatNum) {
        return soak(player.bonuses.getOrDefault(beatNum, EMPTY_LIST));
    }

    public int stunGuard(List<AttackPairBonus> bonuses) {
        return calculateValue(card -> card.stunGuard, STUNGUARD, bonuses);
    }

    public Integer stunGuard(Player player, int beatNum) {
        return stunGuard(player.bonuses.getOrDefault(beatNum, EMPTY_LIST));
    }

    public int power(List<AttackPairBonus> bonuses) {
        return calculateValue(card -> card.power, POWER, bonuses);
    }

    public Integer power(Player player, int beatNum) {
        return power(player.bonuses.getOrDefault(beatNum, EMPTY_LIST));
    }

    private int calculateValue(ToIntFunction<? super Card> statFunction, AttackPairBonusType bonusType, List<AttackPairBonus> bonuses) {
        int bonusValue = sumInteger(
                filterList(bonuses, b -> b.type == bonusType),
                b -> (int) b.value
        );
        return sumInteger(cards, statFunction) + bonusValue;
    }

    //constructors
    public static AttackPair fromCards(List<Card> cards) {
        return new AttackPair(cards);
    }

    @Override
    public String toString() {
        return String.format("%s: %s~%s/%s/%s/SG %s/A %s",
                String.join(" ", StreamUtils.mapList(cards, card -> card.name)),
                minRange(EMPTY_LIST), maxRange(EMPTY_LIST),
                power(EMPTY_LIST), totalPriority(EMPTY_LIST), stunGuard(EMPTY_LIST), soak(EMPTY_LIST)
        );
    }
}
