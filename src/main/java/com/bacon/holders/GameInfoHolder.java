package com.bacon.holders;

import com.bacon.gameobjects.field.Field;
import com.bacon.player.Player;
import com.bacon.statemachine.resolvers.ResolversContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameInfoHolder {
   @Autowired
   public ResolversContainer resolversContainer;

    public Player playerOne;
    public Player playerTwo;

    public Field field = new Field();
}
