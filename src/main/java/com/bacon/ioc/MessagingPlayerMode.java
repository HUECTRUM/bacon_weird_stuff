package com.bacon.ioc;

import com.bacon.messaging.player.PlayerMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MessagingPlayerMode extends PlayerMode {
    @Autowired
    private ApplicationContext context;

    public MessagingPlayerMode() {
        super();

        PlayerMessaging messaging = context.getBean(PlayerMessaging.class);
        super.clashBaseSelector = messaging.selectorContainer.clashSelector;
        super.anteSelector = messaging.selectorContainer.anteSelector;
        super.playerSelector = messaging.selectorContainer.playerSelector;
        super.discardSelector = messaging.selectorContainer.discardSelector;
        super.pairSelector = messaging.selectorContainer.pairSelector;
        super.choiceSelector = messaging.selectorContainer.choiceSelector;
    }
}
