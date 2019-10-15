package com.bacon.persistencefiles.gamedto.entities.player;

import com.bacon.persistencefiles.gamedto.entities.cards.AttackPairEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerBeatEntity {
    public AttackPairEntity currentBeatPair;
    public int damageDealt;
    public int damageTaken;
    public boolean attackHit;
    public boolean stunned;
}
