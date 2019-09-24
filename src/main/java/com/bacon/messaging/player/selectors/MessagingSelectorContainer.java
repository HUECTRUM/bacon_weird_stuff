package com.bacon.messaging.player.selectors;

import com.bacon.messaging.player.PlayerMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagingSelectorContainer {
    public MessagingAnteSelector anteSelector;
    public MessagingChoiceSelector choiceSelector;
    public MessagingClashSelector clashSelector;
    public MessagingDiscardSelector discardSelector;
    public MessagingPairSelector pairSelector;
    public MessagingPlayerSelector playerSelector;
    public MessagingEffectOrderSelector effectOrderSelector;

    @Autowired
    public MessagingSelectorContainer(PlayerMessaging playerMessaging) {
        anteSelector = new MessagingAnteSelector(playerMessaging);
        choiceSelector = new MessagingChoiceSelector(playerMessaging);
        clashSelector = new MessagingClashSelector(playerMessaging);
        discardSelector = new MessagingDiscardSelector(playerMessaging);
        pairSelector = new MessagingPairSelector(playerMessaging);
        playerSelector = new MessagingPlayerSelector(playerMessaging);
        effectOrderSelector = new MessagingEffectOrderSelector(playerMessaging);
    }
}
