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

  // fills a hand till it is full with 7 cards.
  private void fillHand()
  {
    if(cardsInHand.size()<=7)
    {
      cardsInHand.add(deck.drawCard());
      fillHand();
    }
  }

  /**
   * uses a policy to be acted on by a player.
   * @param card chooses which card we are using
   */
  public void usePolicy(int card)
  {
    // use way to use policy in code
    cardsInHand.remove(card);
    fillHand();
  }

  /**
   * This is getting a card from his hand
   * @param card the card number in the array list
   * @return a card in your hand
   */
  public PolicyCard getCardInHand(int card)
  {
    return cardsInHand.get(card);
  }




}
