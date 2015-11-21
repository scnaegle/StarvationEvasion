package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.AI.EnumAITypes;
import starvationevasion.teamrocket.PlayerInterface;
import starvationevasion.teamrocket.main.GameController;

import java.util.LinkedList;

/**
 * Created by scnaegl on 11/14/15.
 */
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
  private LinkedList<Card> hand;

  private String logIn;

  /**
   * Creates a new player based on selected region.
   *
   * @param enumRegion the region that the player controls all stats are determined by this.
   */
  public Player(EnumRegion enumRegion, EnumAITypes ai, GameController controller, LinkedList<Card> hand)
  {
    this.ENUM_REGION = enumRegion;
    AI = ai;
    this.controller = controller;
    this.hand = hand;
  }

  public LinkedList<Card> getHand(){return hand;}
  public void setHand(LinkedList<Card> hand){this.hand = hand;}

  /********Interface Methods ***********/
  @Override
  public String getLogIn() {
    return logIn;
  }

  @Override
  public Card[] playSelectedCards() {
    return new Card[0];
  }

  @Override
  public int vote(Card card, EnumRegion cardPlayedRegion) {
    return 0;
  }

  @Override
  public void discardCard(int discardXNumCards) {

  }

  @Override
  public void addCard(Card card) {

  }
}
