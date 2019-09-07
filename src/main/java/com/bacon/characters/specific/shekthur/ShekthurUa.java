package com.bacon.characters.specific.shekthur;

import com.bacon.characters.UniqueAbility;
import com.bacon.holders.GameInfoHolder;
import com.bacon.player.Player;

import java.util.List;

import static com.bacon.attacks.AttackPairBonus.of;
import static com.bacon.attacks.AttackPairBonusType.PRIORITY;
import static com.bacon.gameobjects.triggers.EffectTrigger.OD;
import static com.bacon.holders.BeatTriggerKey.trigger;
import static java.lang.Math.min;
import static java.math.BigDecimal.valueOf;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class ShekthurUa implements UniqueAbility {
    public int tokens = 3;

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
        holder.addEffect(trigger(beatNum, OD, player), ShekthurUaRegain.EFFECT);
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
