package com.bacon.aifiles.ais.midbeatoptimal.processing.containers;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class GameChoiceContainer {
    public Map<UUID, Object> nextChoices = new HashMap<>();
}
