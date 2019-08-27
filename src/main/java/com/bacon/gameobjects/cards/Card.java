package com.bacon.gameobjects.cards;

import com.bacon.gameobjects.enums.CardType;
import com.bacon.gameobjects.triggers.EffectTrigger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    public CardType cardType;
    public String name;

    public Integer minRange;
    public Integer maxRange;
    public int power;
    public BigDecimal priority;
    public int stunGuard;
    public int soak;

    public Map<EffectTrigger, List<CardEffect>> cardEffects;
}
