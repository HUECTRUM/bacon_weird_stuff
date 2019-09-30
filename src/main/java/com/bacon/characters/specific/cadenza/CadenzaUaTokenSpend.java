package com.bacon.characters.specific.cadenza;

import com.bacon.attacks.AttackPairBonus;
import com.bacon.events.EventEmitter;
import com.bacon.gameobjects.cards.CardEffect;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bacon.attacks.AttackPairBonusType.STUNGUARD;
import static com.bacon.events.EventType.P1_UA_CHANGE;
import static com.bacon.events.EventType.P2_UA_CHANGE;
import static com.bacon.events.GameEvent.event;
import static java.util.List.of;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class CadenzaUaTokenSpend implements CardEffect {
    @Autowired
    private EventEmitter emitter;

    @Override
    public String effectName() {
        return "Cadenza UA token spend";
    }

    @Override
    public List<?> choices(Player player, GameInfoHolder gameInfoHolder) {
        CadenzaUa ua = (CadenzaUa) player.character.ua();
        return ua.tokens > 0 ? of(0, 1) : of(0);
    }

    @Override
    public void apply(Player player, GameInfoHolder holder, int choiceIndex) {
        if (choiceIndex == 0) {
            return;
        }

        CadenzaUa ua = (CadenzaUa) player.character.ua();
        ua.tokens -= 1;
        player.attachBonus(holder.infoHelper.currentBeatNumber(holder), AttackPairBonus.of(STUNGUARD, 9000));

        emitter.emit(event(player.equals(holder.playerOne) ? P1_UA_CHANGE : P2_UA_CHANGE, of(ua.description())));
    }
}
