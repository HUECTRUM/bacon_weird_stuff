package com.bacon.gameobjects.cards;

import com.bacon.gameobjects.triggers.EffectTrigger;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardTriggeredEffect {
    public EffectTrigger effectTrigger;
    public CardEffect cardEffect;

    public static CardTriggeredEffect triggeredEffect(EffectTrigger trigger, CardEffect effect) {
        return new CardTriggeredEffect(trigger, effect);
    }
}
