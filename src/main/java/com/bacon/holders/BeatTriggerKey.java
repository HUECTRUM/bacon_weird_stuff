package com.bacon.holders;

import com.bacon.gameobjects.triggers.EffectTrigger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeatTriggerKey {
    public int beatNumber;
    public EffectTrigger trigger;
}
