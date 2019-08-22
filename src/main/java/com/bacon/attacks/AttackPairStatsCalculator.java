package com.bacon.attacks;

import com.bacon.gameobjects.cards.Card;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

@Component
public class AttackPairStatsCalculator {
    public BigDecimal totalPriority(List<Card> cards) {
        return cards
                .stream()
                .map(card -> card.priority)
                .reduce(ZERO, BigDecimal::add);
    }
}
