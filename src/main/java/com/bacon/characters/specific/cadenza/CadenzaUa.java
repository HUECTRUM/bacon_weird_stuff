package com.bacon.characters.specific.cadenza;

import com.bacon.characters.UniqueAbility;
import com.bacon.events.EventEmitter;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

import static com.bacon.attacks.AttackPairBonus.of;
import static com.bacon.attacks.AttackPairBonusType.ISG;
import static com.bacon.events.EventType.P1_UA_CHANGE;
import static com.bacon.events.EventType.P2_UA_CHANGE;
import static com.bacon.events.GameEvent.event;
import static com.bacon.gameobjects.triggers.EffectTrigger.ON_DAMAGE_TAKEN;
import static com.bacon.holders.BeatTriggerKey.trigger;
import static java.util.List.of;

public class CadenzaUa implements UniqueAbility {
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
        holder.addEffect(trigger(beatNum, ON_DAMAGE_TAKEN, player), new CadenzaUaTokenSpend());

        if (index == 0) {
            return;
        }

        tokens -= 1;
        player.attachBonus(beatNum, of(ISG));

        EventEmitter.INSTANCE.emit(event(player.equals(holder.playerOne) ? P1_UA_CHANGE : P2_UA_CHANGE, of(description())));
    }
}
