package com.bacon.gamefiles.player;

import com.bacon.gamefiles.attacks.AttackPair;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PlayerBeatHolder {
    public AttackPair currentBeatPair = null;
    public int damageDealt = 0;
    public int damageTaken = 0;
    public boolean attackHit = true;
    public boolean stunned = false;
}
