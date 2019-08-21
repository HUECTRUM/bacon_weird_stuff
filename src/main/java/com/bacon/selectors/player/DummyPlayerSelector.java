package com.bacon.selectors.player;

import com.bacon.characters.dummy.DummyOne;
import com.bacon.player.Player;
import org.springframework.stereotype.Component;

import static com.bacon.player.Player.fromCharacter;

@Component
public class DummyPlayerSelector implements PlayerSelector {
    @Override
    public Player selectPlayer() {
        return fromCharacter(new DummyOne());
    }
}
