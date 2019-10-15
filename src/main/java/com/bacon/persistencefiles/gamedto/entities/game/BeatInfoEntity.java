package com.bacon.persistencefiles.gamedto.entities.game;

import com.bacon.persistencefiles.gamedto.entities.cards.AttackPairEntity;
import com.bacon.persistencefiles.gamedto.entities.player.PlayerEntity;

public class BeatInfoEntity {
    public int beatNumber;

    public PlayerEntity activePlayer;
    public PlayerEntity reactivePlayer;

    public AttackPairEntity activePlayerPair;
    public AttackPairEntity reactivePlayerPair;

    public ClashInfoEntity clashInfoHolder;
}
