package com.bacon.persistencefiles.gamedto.entities.game;

import com.bacon.persistencefiles.gamedto.entities.cards.CardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClashInfoEntity {
    public List<CardEntity> firstPlayerBasesPlayed;
    public List<CardEntity> secondPlayerBasesPlayed;
}
