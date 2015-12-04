package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Deck {
  private static final int MAX_CARDS_IN_HAND = 7;
  private ArrayList<PolicyCard> deck = new ArrayList<>();
  private ArrayList<PolicyCard> hand = new ArrayList<>();
  private ArrayList<PolicyCard> discard = new ArrayList<>();
  private ArrayList<PolicyCard> played = new ArrayList<>();

  private Player player;

  private RegionHistory region;
  /**
   * Create a new deck with reference to the player it was created for.
   * @param region region this de+ck belongs to.
   */
  public Deck(RegionHistory region) {
    this.region = region;
  //  this.deck = createDeck(player.getPlayerRegionEnum());
    this.hand = new ArrayList<>();
    this.discard = new ArrayList<>();
    this.played = new ArrayList<>();
  }

  /**
   * Get the cards that are currently in the player's hand
   * @return ArrayList of card in the players hand
   */
  public ArrayList<PolicyCard> getHand() {
    return hand;
  }

  /**
   * Get the cards that are currently selected to be played.
   * @return ArrayList of cards selected to be played.
   */
  public ArrayList<PolicyCard> getPlayed() {
    return played;
  }

  /**
   * Draw a random card from the deck.
   * @return random PolicyCard
   */
  public PolicyCard drawCard() {
    if (outOfCards()) shuffle();
    if (hasMaxCardsInHand()) throw new RuntimeException("Too many cards in hand already.");
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
   * Fills a hand till it is full with 7 cards.
   */
  public void fillHand() {
    if(!hasMaxCardsInHand()) {
      hand.add(drawCard());

      fillHand();
    }
  }

  /**
   * Check to see if the player has the max number of cards in their hand
   * @return True if player has max, False if not
   */
  private boolean hasMaxCardsInHand() {
    return hand.size() >= MAX_CARDS_IN_HAND;
  }

  /**
   * Plays the card corresponding to the given index in the player's hand.
   * @param card_index Index of PolicyCard in players hand to be played
   */
  public void playCard(int card_index)
  {
    // use way to use policy in code
    played.add(hand.remove(card_index));
  }

  /**
   * Plays the given card
   * @param card PolicyCard that player wants to play.
   */
  public void playCard(PolicyCard card) {
    played.add(card);
    hand.remove(card);
  }

  /**
   * Return a played card to the player's hand
   * @param card Card to return to player's hand
   */
  public void returnCardToHand(PolicyCard card) {
    hand.add(card);
    played.remove(card);
  }

  /**
   * Return a played card to the player's hand
   * @param card_index Index of card in the played pile
   */
  public void returnCardToHand(int card_index) {
    hand.add(played.remove(card_index));
  }

  /**
   * This is getting a card from his hand
   * @param card the card number in the array list
   * @return a card in your hand
   */
  public PolicyCard getCardInHand(int card)
  {
    return hand.get(card);
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
