package com.bacon.persistencefiles.gamedto.entities.cards;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardEffectEntity {
    public Class cardClass;
    public Object[] constructorArgs;
}
