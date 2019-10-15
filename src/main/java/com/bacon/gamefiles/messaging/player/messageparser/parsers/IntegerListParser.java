package com.bacon.gamefiles.messaging.player.messageparser.parsers;

import com.bacon.gamefiles.messaging.player.messageparser.MessageParser;
import com.bacon.gamefiles.messaging.player.messageparser.ParsedState;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.bacon.gamefiles.messaging.player.messageparser.ParsedState.parsed;

@Component
public class IntegerListParser implements MessageParser<List<Integer>> {
    private static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public ParsedState<List<Integer>> parse(String msg) {
        String json = String.format("[%s]", msg);

        try {
            return parsed(MAPPER.readValue(json, new TypeReference<List<Integer>>(){}), true);
        } catch (IOException e) {
            return parsed(null, false);
        }
    }
}
