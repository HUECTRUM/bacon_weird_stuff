package com.bacon.gamefiles.ioc.selector;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.player.Player;
import com.bacon.gamefiles.selectors.Selector;

import java.util.HashMap;
import java.util.Map;

public class SelectorHolder<T extends Selector> {
    /**
     * Used to store selectors until the players are resolved.
     * Integer represents order: 1 for first player, 2 for second.
     */
    public Map<Integer, T> orderedModes = new HashMap<>();
    public Map<Player, T> selectors = new HashMap<>();

    public T get(Player player, GameInfoHolder holder) {
        return selectors.computeIfAbsent(
                player,
                pl -> holder.playerOne.equals(pl) ? orderedModes.get(1) : orderedModes.get(2)
        );
    }

    public T getByOrder(int order) {
        return orderedModes.get(order);
    }
}
