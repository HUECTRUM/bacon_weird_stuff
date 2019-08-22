package com.bacon.attacks;

import com.bacon.gameobjects.cards.Card;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static com.bacon.utils.StreamUtils.sumInteger;
import static java.math.BigDecimal.ZERO;

@Component
public class AttackPairStatsCalculator {
    public BigDecimal totalPriority(List<Card> cards) {
        return cards
                .stream()
                .map(card -> card.priority)
                .reduce(ZERO, BigDecimal::add);
    }

    public int minRange(List<Card> cards) {
        return sumInteger(cards, card -> card.minRange);
    }

    public int maxRange(List<Card> cards) {
        return sumInteger(cards, card -> card.maxRange);
    }

    public int power(List<Card> cards) {
        return sumInteger(cards, card -> card.power);
    }
}
