package com.bacon.gameobjects.field;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class Field {
    public static final int ONE_STARTING_POSITION = 2;
    public static final int TWO_STARTING_POSITION = 4;

    public List<String> spaces = asList(null, null, null, null, null, null, null);

    public void setPlayers(String playerOne, String playerTwo) {
        spaces.set(ONE_STARTING_POSITION, playerOne);
        spaces.set(TWO_STARTING_POSITION, playerTwo);
    }
}
