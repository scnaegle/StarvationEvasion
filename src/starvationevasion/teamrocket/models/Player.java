package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;
import starvationevasion.teamrocket.AI.EnumAITypes;
import starvationevasion.teamrocket.PlayerInterface;
import starvationevasion.teamrocket.main.GameController;

import java.util.LinkedList;

public class Player implements PlayerInterface
{

  /**
   * Player's selected region.
   */
  public final EnumRegion ENUM_REGION;

  /**
   * AI Level if AI is used
   * If not using AI, this should be null
   */
  public final EnumAITypes AI;

  /**
   * Controller to add data to once
   * Player has made a decision
   */
  private GameController controller;

  /**
   * Card hand of the player
   */
  private LinkedList<PolicyCard> hand;

  private PolicyCard[] selectedCards;

  /**
   * Log in info for player
   */
  private String logIn;

  /**
   * Income of the player
   */
  private long income;

  /**
   * Creates a new player based on selected region.
   *
   * @param enumRegion the region that the player controls all stats are determined by this.
   */
  public Player(EnumRegion enumRegion, EnumAITypes ai, GameController controller)
  {
    this.ENUM_REGION = enumRegion;
    AI = ai;
    this.controller = controller;
    income = 10000000;
  }

  /**
   * Gets the hand of the player
   * @return player hand
   */
  public LinkedList<PolicyCard> getHand(){return hand;}

  /**
   * Update the player's hand with the new hand
   * @param hand of new cards
   */
  public void setHand(LinkedList<PolicyCard> hand){this.hand = hand;}

  /**
   * Get the current income of the player
   * @return player's income
   */
  public long getIncome(){return income;}

  /**
   * Updates the player's income to the new income
   * @param income new income of player
   */
  public void updateIncome(long income){this.income = income;}

  /**
   * Set the selected cards from the GUI so the player knows
   * which cards were selected
   * If there is no card position, then -1 should be passed for
   * that card position
   * @param card1 position in hand of first card
   * @param card2 position in hand of second card
   */
  public void selectedCards(int card1, int card2)
  {
    int selectionSize = 2;

    if(card1 < 0 || card2 < 0) selectionSize = 1;
    else if(card1 < 0 && card2 < 0) selectionSize = 0;

    selectedCards = new PolicyCard[selectionSize];

    if(selectionSize > 0)
    {
      if(card1 < 0) selectedCards[0] = hand.get(card2);
      else selectedCards[0] = hand.get(card1);
    }
    if(selectionSize > 1) selectedCards[1] = hand.get(card2);
  }

  /********Interface Methods ***********/
  @Override
  public String getLogIn() {
    return logIn;
  }

  @Override
  public PolicyCard[] getDraftedCards() {
    return selectedCards;
  }

  @Override
  public int vote(PolicyCard card, EnumRegion cardPlayedRegion) {
    return 0;
  }

  @Override
  public void discardCard(int cardPosition)
  {
    hand.remove(cardPosition);
  }

  @Override
  public void addCard(PolicyCard card)
  {
    hand.add(card);
  }
}
