package com.bacon.attacks;

import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;
import com.bacon.utils.StreamUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.ToIntFunction;

import static com.bacon.attacks.AttackPairBonusType.*;
import static com.bacon.utils.StreamUtils.*;
import static java.math.BigDecimal.ZERO;
import static java.util.Collections.EMPTY_LIST;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttackPair {
    public List<Card> cards;
    //TODO: MOVE TO PLAYER!!!
    public List<AttackPairBonus> bonuses;

    public List<CardEffect> triggeredEffects(EffectTrigger trigger) {
        return flatMapList(mapList(cards, card -> card.cardEffects.get(trigger)));
    }

    public BigDecimal totalPriority() {
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

    public Integer minRange() {
        if (cards.stream().anyMatch(card -> card.minRange == null)) {
            return null;
        }
        return calculateValue(card -> card.minRange, MINRANGE);
    }

    public Integer maxRange() {
        if (cards.stream().anyMatch(card -> card.maxRange == null)) {
            return null;
        }
        return calculateValue(card -> card.maxRange, MAXRANGE);
    }

    public int soak() {
        return calculateValue(card -> card.soak, SOAK);
    }

    public int stunGuard() {
        return calculateValue(card -> card.stunGuard, STUNGUARD);
    }

    public int power() {
        return calculateValue(card -> card.power, POWER);
    }

    private int calculateValue(ToIntFunction<? super Card> statFunction, AttackPairBonusType bonusType) {
        int bonusValue = sumInteger(
                filterList(bonuses, b -> b.type == bonusType),
                b -> (int) b.value
        );
        return sumInteger(cards, statFunction) + bonusValue;
    }

    //constructors
    public static AttackPair fromCards(List<Card> cards) {
        return new AttackPair(cards, EMPTY_LIST);
    }

    @Override
    public String toString() {
        return String.format("%s: %s~%s/%s/%s/SG %s/A %s with %s bonuses",
                String.join(" ", StreamUtils.mapList(cards, card -> card.name)),
                minRange(), maxRange(), power(), totalPriority(), stunGuard(), soak(), bonuses
        );
    }
}
