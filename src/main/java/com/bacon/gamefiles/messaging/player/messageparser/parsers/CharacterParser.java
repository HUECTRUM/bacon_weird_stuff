package com.bacon.gamefiles.messaging.player.messageparser.parsers;

import com.bacon.gamefiles.characters.Character;
import com.bacon.gamefiles.characters.PlayableCharacters;
import com.bacon.gamefiles.messaging.player.messageparser.MessageParser;
import com.bacon.gamefiles.messaging.player.messageparser.ParsedState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.messaging.player.messageparser.ParsedState.parsed;

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
