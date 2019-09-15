package com.bacon.selectors.ante;

import com.bacon.holders.GameInfoHolder;
import com.bacon.messaging.player.PlayerMessaging;
import com.bacon.player.Player;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MessagingAnteSelector implements AnteSelector {
    private PlayerMessaging playerMessaging;

    @Override
    public int anteChoice(GameInfoHolder holder, Player player, List<?> choices) {
        return 0;
    }
}
