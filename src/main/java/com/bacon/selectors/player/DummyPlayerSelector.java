package com.bacon.selectors.player;

import com.bacon.characters.dummy.DummyOne;
import com.bacon.player.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.bacon.player.Player.fromCharacter;

@Component
@Slf4j
public class DummyPlayerSelector implements PlayerSelector {
    @Override
    public Player selectPlayer() {
        log.info("Player select called");
        return fromCharacter(new DummyOne());
    }
}
