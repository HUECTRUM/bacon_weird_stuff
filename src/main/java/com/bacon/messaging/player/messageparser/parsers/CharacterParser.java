package com.bacon.messaging.player.messageparser.parsers;

import com.bacon.characters.Character;
import com.bacon.characters.PlayableCharacters;
import com.bacon.messaging.player.messageparser.MessageParser;
import com.bacon.messaging.player.messageparser.ParsedState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.messaging.player.messageparser.ParsedState.parsed;

@Component
public class CharacterParser implements MessageParser<Character> {
    @Autowired
    private PlayableCharacters playableCharacters;

    @Override
    public ParsedState<Character> parse(String msg) {
        return playableCharacters.characters()
                .stream()
                .filter(ch -> ch.name().toLowerCase().equals(msg.toLowerCase()))
                .findAny()
                .map(ch -> parsed(ch, true))
                .orElse(parsed(null, false));
    }
}
