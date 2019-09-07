package com.bacon.player;

import com.bacon.attacks.AttackPair;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PlayerBeatHolder {
    public AttackPair currentBeatPair = null;
    public int damageDealt = 0;
    public int damageTaken = 0;
    public boolean attackHit = true;
    public boolean stunned = false;
}
