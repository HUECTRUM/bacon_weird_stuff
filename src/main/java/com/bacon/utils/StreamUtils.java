package com.bacon.utils;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

public class StreamUtils {
    public static <T> List<T> concatLists(List<T> firstList, List<T> secondList) {
        return concat(firstList.stream(), secondList.stream()).collect(toList());
    }
}
