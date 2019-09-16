package com.bacon.messaging.player.listener;

import com.bacon.events.EventListener;
import com.bacon.events.GameEvent;
import com.bacon.websocket.WsSender;

public class MessagingListener implements EventListener {
    @Override
    public void onEvent(GameEvent event) {
        WsSender.INSTANCE.sendMessage(prepareMessage(event));
    }

    private String prepareMessage(GameEvent event) {
        switch (event.type) {
            case REVEAL:
                return "REVEAL stage";
            case SOB:
                return "SOB stage";
            case BA:
                return "BA stage";
            case OH:
                return "OH stage";
            case OD:
                return "OD stage";
            case AA:
                return "AA stage";
            case EOB:
                return "EOB stage";
            case RECYCLE:
                return "RECYCLE stage";
            case D1_DISCARD_CHANGED:
            case D2_DISCARD_CHANGED:
            case P1_PAIR_SELECTED:
            case P2_PAIR_SELECTED:
                return event.args.get(0) + " " + event.args.get(1);
            case BOARD_CHANGED:
            case P1_ANTE:
            case P2_ANTE:
                return event.args.get(0).toString();
            case CHARACTER_SELECT:
                return "Character select. Type character name";
            case DISCARD_SELECT:
                return "Discard select. Type in discarded cards in d1 d1 d2 d2 order";
            case PAIR_SELECT:
                return String.format("Ante select from options %s. Type in the index chosen", event.args.get(0));
            case EFFECT_CHOICE:
                return String.format("Effect choice on effect %s from choices %s. Type in index chosen",
                        event.args.get(0), event.args.get(1));
            default:
                return "Unknown event received";
        }
    }
}
