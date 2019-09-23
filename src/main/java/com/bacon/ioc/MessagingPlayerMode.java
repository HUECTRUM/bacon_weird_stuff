package com.bacon.ioc;

import com.bacon.messaging.player.PlayerMessaging;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class MessagingPlayerMode extends PlayerMode {
    public MessagingPlayerMode() {
        //todo: seems like a hack
        super();

        PlayerMessaging messaging = new PlayerMessaging();
        super.clashBaseSelector = messaging.selectorContainer.clashSelector;
        super.anteSelector = messaging.selectorContainer.anteSelector;
        super.playerSelector = messaging.selectorContainer.playerSelector;
        super.discardSelector = messaging.selectorContainer.discardSelector;
        super.pairSelector = messaging.selectorContainer.pairSelector;
        super.choiceSelector = messaging.selectorContainer.choiceSelector;
        super.effectOrderSelector = messaging.selectorContainer.effectOrderSelector;
    }
}
