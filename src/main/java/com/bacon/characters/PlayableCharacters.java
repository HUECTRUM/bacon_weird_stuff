package com.bacon.characters;

import com.bacon.characters.specific.cadenza.Cadenza;
import com.bacon.characters.specific.shekthur.Shekthur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class PlayableCharacters {
    @Autowired
    private Shekthur shekthur;
    @Autowired
    private Cadenza cadenza;

    public List<Character> characters() {
        return asList(shekthur, cadenza);
    }
}
