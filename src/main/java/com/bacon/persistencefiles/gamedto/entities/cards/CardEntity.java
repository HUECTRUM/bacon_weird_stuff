package com.bacon.persistencefiles.gamedto.entities.cards;

import com.bacon.gamefiles.gameobjects.triggers.EffectTrigger;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class CardEntity {
    public String methodName;
    public Map<EffectTrigger, List<CardEffectEntity>> cardEffects;
}
