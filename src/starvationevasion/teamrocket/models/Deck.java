package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;

import java.util.ArrayList;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Deck {
  ArrayList<PolicyCard> cards = new ArrayList<>();
  ArrayList<PolicyCard> discard = new ArrayList<>();

  private EnumRegion region;

  public Deck(EnumRegion region) {
    this.region = region;
    this.cards = createDeck(region);
    this.discard = new ArrayList<>();
  }

  public PolicyCard drawCard() {
    PolicyCard card = cards.get(0);
    return card;
  }

  private ArrayList<PolicyCard> createDeck(EnumRegion region) {
    ArrayList<PolicyCard> cards = new ArrayList<>();
    // TODO pull cards from XML file
    return cards;
  }
}
