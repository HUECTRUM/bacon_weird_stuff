package com.bacon.gamefiles.random;

import org.springframework.stereotype.Component;

import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

@Component
public class Randomizer {
    private static final Random random = new Random();

    public boolean coinFlip() {
        return randomize(2) == 0;
    }

    public int randomize(int size) {
        return (random.nextInt() & MAX_VALUE) % size;
    }

    public NumberPair randomizeTwoNumbers(int size) {
        int first = (random.nextInt() & MAX_VALUE) % size;
        int second = first;

        while (second == first) {
            second = (random.nextInt() & MAX_VALUE) % size;
        }

        return new NumberPair(first, second);
    }
}
