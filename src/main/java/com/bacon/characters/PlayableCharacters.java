package com.bacon.characters;

import com.bacon.characters.specific.cadenza.Cadenza;
import com.bacon.characters.specific.shekthur.Shekthur;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PlayableCharacters {
    SHEKTHUR(new Shekthur()),
    CADENZA(new Cadenza());

    public Character character;
}
