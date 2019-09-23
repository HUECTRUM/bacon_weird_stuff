package com.bacon.messaging.player;

import com.bacon.messaging.player.messageparser.MessageParser;
import com.bacon.messaging.player.messageparser.parsers.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MessagingState {
    NORMAL(null),
    AWAIT_CLASH(new BaseParser()),
    AWAIT_ANTE(new IntegerParser()),
    AWAIT_PLAYER(new CharacterParser()),
    AWAIT_DISCARDS(new DiscardParser()),
    AWAIT_PAIR(new AttackPairParser()),
    AWAIT_CHOICE(new IntegerParser()),
    AWAIT_EFFECT_ORDER(new IntegerListParser());

    public MessageParser messageParser;
}
