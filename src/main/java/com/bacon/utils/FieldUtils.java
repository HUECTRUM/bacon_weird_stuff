package com.bacon.utils;

import com.bacon.holders.GameInfoHolder;

import java.util.List;

import static java.lang.Math.abs;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class FieldUtils {
    public static int playerDist(GameInfoHolder holder) {
        List<Integer> playersPoints = range(0, holder.field.spaces.size())
                .filter(ind -> holder.field.spaces.get(ind) != null)
                .boxed()
                .collect(toList());

        return abs(playersPoints.get(0) - playersPoints.get(1));
    }
}
