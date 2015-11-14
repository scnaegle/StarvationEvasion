package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Deck {
  ArrayList<PolicyCard> cards = new ArrayList<>();
  ArrayList<PolicyCard> discard_pile = new ArrayList<>();

  private EnumRegion region;

  public Deck(EnumRegion region) {
    this.region = region;
    this.cards = createDeck(region);
    this.discard_pile = new ArrayList<>();
  }

  public PolicyCard drawCard() {
    Random rand = new Random();
    return cards.get(rand.nextInt(cards.size()));
  }

  public void shuffle() {
    for(PolicyCard card : discard_pile) {
      this.cards.add(card);
    }
  }

  private ArrayList<PolicyCard> createDeck(EnumRegion region) {
    ArrayList<PolicyCard> cards = new ArrayList<>();
    // TODO pull cards from XML file
    return cards;
  }
}
