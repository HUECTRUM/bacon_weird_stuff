package com.bacon.gamefiles.characters.specific.shekthur;

import com.bacon.gamefiles.characters.UniqueAbility;
import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.attacks.AttackPairBonus.of;
import static com.bacon.gamefiles.attacks.AttackPairBonusType.PRIORITY;
import static com.bacon.gamefiles.events.EventType.P1_UA_CHANGE;
import static com.bacon.gamefiles.events.EventType.P2_UA_CHANGE;
import static com.bacon.gamefiles.events.GameEvent.event;
import static com.bacon.gamefiles.gameobjects.triggers.EffectTrigger.OD;
import static com.bacon.gamefiles.holders.BeatTriggerKey.trigger;
import static java.lang.Math.min;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class ShekthurUa implements UniqueAbility {
    @Autowired
    private EventEmitter emitter;
    @Autowired
    private ShekthurUaRegain shekthurUaRegain;

    public int tokens = 3;

    @Override
    public String description() {
        return String.format("Shekthur UA %s tokens", tokens);
    }

    @Override
    public List<Integer> anteSelections(GameInfoHolder holder) {
        return range(0, tokens + 1)
                .boxed()
                .collect(toList());
    }

    @Override
    public void applySelection(GameInfoHolder holder, Player player, int index) {
        int tokensAnted = anteSelections(holder).get(index);

        tokens -= tokensAnted;

        int beatNum = holder.infoHelper.currentBeatNumber(holder);

        player.attachBonus(beatNum, of(PRIORITY, valueOf(tokensAnted)));
        holder.addEffect(trigger(beatNum, OD, player), shekthurUaRegain);

        emitter.emit(event(player.equals(holder.playerOne) ? P1_UA_CHANGE : P2_UA_CHANGE, of(description()), holder.gameId));
    }

    public void gainTokens(int gain) {
        tokens = min(tokens + gain, 5);
    }

    @Override
    public String toString() {
        return "ShekthurUa{" +
                "tokens=" + tokens +
                '}';
    }
}
