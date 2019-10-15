package com.bacon.gamefiles.statemachine.conditions;

public enum AttackCheckTransitionConditions implements StateTransitionCondition {
    MISS, NO_DAMAGE, STUN, PLAYER_DEAD
}
