package com.bacon.cards.common;

import com.bacon.gameobjects.Card;

import java.util.List;

import static com.bacon.cards.common.bases.Burst.BURST;
import static com.bacon.cards.common.bases.Dodge.DODGE;
import static com.bacon.cards.common.bases.Drive.DRIVE;
import static com.bacon.cards.common.bases.Grasp.GRASP;
import static com.bacon.cards.common.bases.Shot.SHOT;
import static com.bacon.cards.common.bases.Strike.STRIKE;
import static java.util.Arrays.asList;

public class CommonCards {
   public static List<Card> COMMON_BASES = asList(BURST, SHOT, STRIKE, DRIVE, GRASP, DODGE);
}
