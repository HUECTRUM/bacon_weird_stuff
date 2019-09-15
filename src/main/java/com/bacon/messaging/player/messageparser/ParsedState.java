package com.bacon.messaging.player.messageparser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParsedState<T> {
    public T value;
    public boolean parsed;

    public static <T> ParsedState<T> parsed(T value, boolean parsed) {
        return new ParsedState<>(value, parsed);
    }
}
