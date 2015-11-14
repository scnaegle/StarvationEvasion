package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;

import java.util.ArrayList;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Hand {
  Deck deck = new Deck(EnumRegion.CALIFORNIA);
  ArrayList<PolicyCard> cardsInHand = new ArrayList<>();

  private void initHand(){
    for(int i = 0; i<7; i++)
    {
      cardsInHand.add(deck.drawCard());
    }
  }
}
