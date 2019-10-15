package com.bacon.gamefiles.messaging.player.messageparser;

public interface MessageParser<T> {
    ParsedState<T> parse(String msg);
}
