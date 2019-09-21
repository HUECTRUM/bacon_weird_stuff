package com.bacon.statemachine.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class ResolversContainer {
    @Autowired
    public CharacterSelectionResolver characterSelectionResolver;
    @Autowired
    public DiscardResolver discardResolver;
    @Autowired
    public Recycler recycler;
    @Autowired
    public PairSelectionResolver pairSelectionResolver;
    @Autowired
    public PriorityResolver priorityResolver;
    @Autowired
    public RangeChecker rangeChecker;
    @Autowired
    public DamageResolver damageResolver;
    @Autowired
    public TriggeredEffectsResolver effectsResolver;
    @Autowired
    public StunCheckResolver stunCheckResolver;
    @Autowired
    public AnteResolver anteResolver;
    @Autowired
    public PreRevealResolver preRevealResolver;
}
