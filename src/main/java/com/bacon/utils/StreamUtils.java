package com.bacon.utils;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

public class StreamUtils {
    public static <T, R> List<R> mapList(List<T> firstList, Function<? super T, ? extends R> mapper) {
        return firstList.stream().map(mapper).collect(toList());
    }

    public static <T> List<T> concatLists(List<T> firstList, List<T> secondList) {
        return concat(firstList.stream(), secondList.stream()).collect(toList());
    }
}
