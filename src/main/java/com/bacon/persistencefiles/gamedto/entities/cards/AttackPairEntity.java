package com.bacon.persistencefiles.gamedto.entities.cards;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AttackPairEntity {
    public List<CardEntity> cards;
}
