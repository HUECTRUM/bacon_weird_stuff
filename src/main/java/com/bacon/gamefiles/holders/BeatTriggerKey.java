package com.bacon.gamefiles.holders;

import com.bacon.gamefiles.gameobjects.triggers.EffectTrigger;
import com.bacon.gamefiles.player.Player;
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
