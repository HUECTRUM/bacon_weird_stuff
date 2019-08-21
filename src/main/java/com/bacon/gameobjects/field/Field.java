package com.bacon.gameobjects.field;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class Field {
    List<String> spaces = asList(null, null, null, null, null, null, null);
}
