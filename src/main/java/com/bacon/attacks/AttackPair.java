package com.bacon.attacks;

import com.bacon.gameobjects.cards.Card;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.gameobjects.triggers.EffectTrigger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static com.bacon.utils.StreamUtils.*;
import static java.math.BigDecimal.ZERO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttackPair {
    public List<Card> cards;

    public List<CardEffect> triggeredEffects(EffectTrigger trigger) {
        return flatMapList(mapList(cards, card -> card.cardEffects.get(trigger)));
    }

    public BigDecimal totalPriority() {
        return cards
                .stream()
                .map(card -> card.priority)
                .reduce(ZERO, BigDecimal::add);
    }

    public int minRange() {
        return sumInteger(cards, card -> card.minRange);
    }

    public int maxRange() {
        return sumInteger(cards, card -> card.maxRange);
    }

    public int soak() {
        return sumInteger(cards, card -> card.soak);
    }

    public int power() {
        return sumInteger(cards, card -> card.power);
    }

    //constructors
    public static AttackPair fromCards(List<Card> cards) {
        return new AttackPair(cards);
    }
}
