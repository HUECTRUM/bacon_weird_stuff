package com.bacon.gameobjects.cards;

import com.bacon.gameobjects.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    public CardType cardType;
    public String name;

    public int minRange;
    public int maxRange;
    public int power;
    public BigDecimal priority;
    public int stunGuard;
    public int soak;

    public List<CardEffect> effects;
}
