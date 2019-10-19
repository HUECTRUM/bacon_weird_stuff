package com.bacon.gamefiles.utils;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapUtils {
    public static <T, R, V> Map<T, V> mapValues(Map<T, R> map, Function<R, V> valueMapper) {
        return map
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> valueMapper.apply(entry.getValue())));
    }
}
