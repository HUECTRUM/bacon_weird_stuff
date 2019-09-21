package com.bacon.json.serializers;

import com.bacon.gameobjects.cards.Card;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CardSerializer extends StdSerializer<Card> {
    public CardSerializer() {
        this(null);
    }

    protected CardSerializer(Class<Card> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(Card value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.name);
    }
}
