package com.bacon.persistencefiles.gamedto.entities.game;

import com.bacon.gamefiles.holders.BeatTriggerKey;
import com.bacon.gamefiles.statemachine.GameStates;
import com.bacon.persistencefiles.gamedto.entities.cards.CardEffectEntity;
import com.bacon.persistencefiles.gamedto.entities.player.PlayerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class GameEntity {
    public UUID gameId;

    public GameStates state;

    public PlayerEntity playerOne;
    public PlayerEntity playerTwo;

    public Map<BeatTriggerKey, List<CardEffectEntity>> additionalEffects;

    public BeatInfoEntity beatInfoHolder;
    public List<BeatInfoEntity> prevBeats;
}
