package com.bacon.gamefiles.gameobjects.cards;

import com.bacon.gamefiles.gameobjects.enums.CardType;
import com.bacon.gamefiles.gameobjects.triggers.EffectTrigger;
import com.bacon.gamefiles.json.serializers.CardSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@JsonSerialize(using = CardSerializer.class)
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
