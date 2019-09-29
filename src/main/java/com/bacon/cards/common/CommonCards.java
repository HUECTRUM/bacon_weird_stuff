package com.bacon.cards.common;

import com.bacon.cards.common.bases.*;
import com.bacon.cards.common.styles.Switch;
import com.bacon.gameobjects.cards.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static java.util.Arrays.asList;

@Component
public class CommonCards {
   @Autowired
   private Burst burst;
   @Autowired
   private Shot shot;
   @Autowired
   private Strike strike;
   @Autowired
   private Drive drive;
   @Autowired
   private Grasp grasp;
   @Autowired
   private Dodge dodge;
   @Autowired
   private Switch switchCard;

   public List<Card> COMMON_BASES = asList(burst.BURST, shot.SHOT, strike.STRIKE, drive.DRIVE, grasp.GRASP, dodge.DODGE);
   public List<Card> COMMON_STYLES = of(switchCard.SWITCH);
}
