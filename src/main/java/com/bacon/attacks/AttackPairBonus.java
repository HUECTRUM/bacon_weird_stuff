package com.bacon.attacks;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttackPairBonus {
    public AttackPairBonusType type;
    public Object value;

    public static AttackPairBonus of(AttackPairBonusType type, Object value) {
        return new AttackPairBonus(type, value);
    }

    public static AttackPairBonus of(AttackPairBonusType type) {
        return new AttackPairBonus(type, null);
    }
}
