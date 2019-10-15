package com.bacon.gamefiles.gameobjects.field;

import com.bacon.gamefiles.events.EventEmitter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.bacon.gamefiles.events.EventType.BOARD_CHANGED;
import static com.bacon.gamefiles.events.GameEvent.event;
import static java.util.Arrays.asList;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class Field {
    @Autowired
    private EventEmitter emitter;

    public UUID gameId;

    public static final int ONE_STARTING_POSITION = 2;
    public static final int TWO_STARTING_POSITION = 4;

    public List<String> spaces = asList(null, null, null, null, null, null, null);

    public void setPlayers(String playerOne, String playerTwo) {
        spaces.set(ONE_STARTING_POSITION, playerOne);
        spaces.set(TWO_STARTING_POSITION, playerTwo);

        emitter.emit(event(BOARD_CHANGED, of(this), gameId));
    }
}
