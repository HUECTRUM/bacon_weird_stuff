package com.bacon.holders;

import com.bacon.gameobjects.triggers.EffectTrigger;
import com.bacon.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeatTriggerKey {
    public int beatNumber;
    public EffectTrigger trigger;
    public Player player;

    public static BeatTriggerKey trigger(int beatNumber, EffectTrigger trigger, Player player) {
        return new BeatTriggerKey(beatNumber, trigger, player);
    }
}
