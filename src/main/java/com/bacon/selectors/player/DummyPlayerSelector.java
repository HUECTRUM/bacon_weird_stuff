package com.bacon.selectors.player;

import com.bacon.characters.dummy.DummyOne;
import com.bacon.characters.specific.shekthur.Shekthur;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bacon.player.Player.fromCharacter;

@Component
@Slf4j
public class DummyPlayerSelector implements PlayerSelector {
    @Autowired
    private Shekthur shekthur;
    @Autowired
    private DummyOne dummyOne;

    int choiceNum = 0;

    @Override
    public Player selectPlayer(GameInfoHolder holder) {
        ++choiceNum;
        log.info("Player select called");
        return choiceNum % 2 == 1 ? fromCharacter(shekthur) : fromCharacter(dummyOne);
    }
}
