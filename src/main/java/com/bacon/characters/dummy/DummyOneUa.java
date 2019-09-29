package com.bacon.characters.dummy;

import com.bacon.characters.UniqueAbility;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class DummyOneUa implements UniqueAbility {
    @Override
    public String description() {
        return "Dummy one ua";
    }

    @Override
    public List<?> anteSelections(GameInfoHolder holder) {
        return singletonList("");
    }

    @Override
    public void applySelection(GameInfoHolder holder, Player player, int index) {

    }

    @Override
    public String toString() {
        return "DummyOneUa{}";
    }
}
