package com.bacon.persistencefiles.gamedto.entities.player;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterEntity {
    public Class characterClass;
    public Object[] characterData;
}
