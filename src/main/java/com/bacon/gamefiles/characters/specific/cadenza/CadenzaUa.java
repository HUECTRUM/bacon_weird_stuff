package com.bacon.gamefiles.characters.specific.cadenza;

import com.bacon.gamefiles.characters.UniqueAbility;
import com.bacon.gamefiles.events.EventEmitter;
import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.gamefiles.attacks.AttackPairBonus.of;
import static com.bacon.gamefiles.attacks.AttackPairBonusType.ISG;
import static com.bacon.gamefiles.events.EventType.P1_UA_CHANGE;
import static com.bacon.gamefiles.events.EventType.P2_UA_CHANGE;
import static com.bacon.gamefiles.events.GameEvent.event;
import static com.bacon.gamefiles.gameobjects.triggers.EffectTrigger.ON_DAMAGE_TAKEN;
import static com.bacon.gamefiles.holders.BeatTriggerKey.trigger;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class CadenzaUa implements UniqueAbility {
    @Autowired
    private EventEmitter emitter;
    @Autowired
    private CadenzaUaTokenSpend cadenzaUaTokenSpend;

    public int tokens = 3;

    @Override
    public String description() {
        return String.format("Cadenza UA %s tokens", tokens);
    }

    @Override
    public List<?> anteSelections(GameInfoHolder holder) {
        return tokens > 0 ? of(0, 1) : of(0);
    }

    @Override
    public void applySelection(GameInfoHolder holder, Player player, int index) {
        int beatNum = holder.infoHelper.currentBeatNumber(holder);
        holder.addEffect(trigger(beatNum, ON_DAMAGE_TAKEN, player), cadenzaUaTokenSpend);

        if (index == 0) {
            return;
        }

        tokens -= 1;
        player.attachBonus(beatNum, of(ISG));

        emitter.emit(event(player.equals(holder.playerOne) ? P1_UA_CHANGE : P2_UA_CHANGE, of(description()), holder.gameId));
    }
}
