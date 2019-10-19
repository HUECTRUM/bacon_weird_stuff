package com.bacon.persistencefiles.gamedto.entities.player;

import com.bacon.gamefiles.attacks.AttackPairBonus;
import com.bacon.persistencefiles.gamedto.entities.cards.CardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class PlayerEntity {
    public String playerId;
    public int health;

    public CharacterEntity character;

    public List<CardEntity> discardOne;
    public List<CardEntity> discardTwo;

    public PlayerBeatEntity beatHolder;
    public List<PlayerBeatEntity> prevBeats;

    public Map<Integer, List<AttackPairBonus>> bonuses;
}
