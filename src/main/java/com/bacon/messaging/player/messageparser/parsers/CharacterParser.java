package com.bacon.messaging.player.messageparser.parsers;

import com.bacon.characters.Character;
import com.bacon.characters.PlayableCharacters;
import com.bacon.messaging.player.messageparser.MessageParser;
import com.bacon.messaging.player.messageparser.ParsedState;

import static com.bacon.messaging.player.messageparser.ParsedState.parsed;
import static java.util.Arrays.stream;

public class CharacterParser implements MessageParser<Character> {
    @Override
    public ParsedState<Character> parse(String msg) {
        return stream(PlayableCharacters.values())
                .filter(ch -> ch.name().toLowerCase().equals(msg.toLowerCase()))
                .findAny()
                .map(ch -> parsed(ch.character, true))
                .orElse(parsed(null, false));
    }
}
