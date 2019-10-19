package com.bacon.gamefiles.statemachine.resolvers;

import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.events.GameEvent;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.holders.beat.BeatInfoHolder;
import com.bacon.gamefiles.statemachine.conditions.StateTransitionCondition;
import com.bacon.gamefiles.statemachine.resolvers.internal.ClashResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.bacon.gamefiles.events.EventType.PRIORITY_ACTIVE;
import static com.bacon.gamefiles.statemachine.conditions.ClashTransitionConditions.CLASHED_OUT;
import static com.bacon.gamefiles.statemachine.conditions.RegularTransitionConditions.EMPTY;
import static java.util.List.of;

@Component
public class PriorityResolver {
    @Autowired
    public ClashResolver clashResolver;
    @Autowired
    private EventEmitter emitter;

    public StateTransitionCondition resolvePriority(GameInfoHolder holder) {
        int beatNum = holder.infoHelper.currentBeatNumber(holder);

        BeatInfoHolder beatInfoHolder = holder.beatInfoHolder;
        boolean clashesResolved = resolveClashes(holder, beatInfoHolder);
        if (!clashesResolved) {
            return CLASHED_OUT;
        }

        BigDecimal firstPlayerPrio = holder.playerOne.beatHolder.currentBeatPair.totalPriority(holder.playerOne, beatNum);
        BigDecimal secondPlayerPrio = holder.playerTwo.beatHolder.currentBeatPair.totalPriority(holder.playerTwo, beatNum);

        boolean firstPlayerFaster = firstPlayerPrio.compareTo(secondPlayerPrio) >= 0;
        beatInfoHolder.activePlayer = firstPlayerFaster? holder.playerOne : holder.playerTwo;
        beatInfoHolder.reactivePlayer = firstPlayerFaster ? holder.playerTwo : holder.playerOne;

        beatInfoHolder.activePlayerPair =
                firstPlayerFaster ? holder.playerOne.beatHolder.currentBeatPair : holder.playerTwo.beatHolder.currentBeatPair;
        beatInfoHolder.reactivePlayerPair =
                firstPlayerFaster ? holder.playerTwo.beatHolder.currentBeatPair : holder.playerOne.beatHolder.currentBeatPair;

        emitter.emit(GameEvent.event(PRIORITY_ACTIVE, of(beatInfoHolder.activePlayer.playerId), holder.gameId));
        return EMPTY;
    }

    private boolean resolveClashes(GameInfoHolder holder, BeatInfoHolder beatInfoHolder) {
        int beatNum = holder.infoHelper.currentBeatNumber(holder);

        BigDecimal firstPlayerPrio = holder.playerOne.beatHolder.currentBeatPair.totalPriority(holder.playerOne, beatNum);
        BigDecimal secondPlayerPrio = holder.playerTwo.beatHolder.currentBeatPair.totalPriority(holder.playerTwo, beatNum);

        while (firstPlayerPrio.compareTo(secondPlayerPrio) == 0) {
            boolean clashResolved = clashResolver.resolveClash(holder);
            if (!clashResolved) {
                return false;
            }

            firstPlayerPrio = holder.playerOne.beatHolder.currentBeatPair.totalPriority(holder.playerOne, beatNum);
            secondPlayerPrio = holder.playerTwo.beatHolder.currentBeatPair.totalPriority(holder.playerTwo, beatNum);
        }
        return true;
    }
}
