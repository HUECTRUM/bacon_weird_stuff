package com.bacon.statemachine.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
}
