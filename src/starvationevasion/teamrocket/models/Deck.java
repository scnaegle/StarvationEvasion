package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Deck {
  ArrayList<PolicyCard> deck = new ArrayList<>();
  ArrayList<PolicyCard> discard = new ArrayList<>();

  private EnumRegion region;

  public Deck(EnumRegion region) {
    this.region = region;
    this.deck = createDeck(region);
    this.discard = new ArrayList<>();
  }

  /**
   * Draw a random card from the deck.
   * @return random PolicyCard
   */
  public PolicyCard drawCard() {
    if (outOfCards()) shuffle();
    Random rand = new Random();
    return deck.remove(rand.nextInt(deck.size()));
  }

  /**
   * Discard the given card, which is then moved to the 'discard pile'.
   * @param card PolicyCard to discard
   */
  public void discard(PolicyCard card) {
    this.discard.add(card);
  }

  /**
   * Shuffling the deck moves all of the cards in the discard pile, back into
   * the deck so that they can be drawn again.
   */
  public void shuffle() {
    for(PolicyCard card : discard) {
      discard.remove(card);
      deck.add(card);
    }
  }

  /**
   * Check if the deck is out of cards in the deck.
   * @return True if there are no more cards, False otherwise
   */
  public boolean outOfCards() {
    return deck.isEmpty();
  }

  /**
   * Pull in all the cards to a create a new deck for the given region.
   * @param region EnumRegion for which the deck is for
   * @return list of PolicyCards for the new deck
   */
  private ArrayList<PolicyCard> createDeck(EnumRegion region) {
    ArrayList<PolicyCard> cards = new ArrayList<>();
    // TODO pull cards from XML file
    return cards;
  }

}
