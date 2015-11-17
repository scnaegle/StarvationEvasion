package starvationevasion.teamrocket.main;

import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;
import starvationevasion.teamrocket.models.Deck;
import starvationevasion.teamrocket.models.Player;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * GameController handles the main game logic and movement between phases.
 */
public class GameController
{
  private Player player;

  GameController()
  {
    this.player = new Player(EnumRegion.CALIFORNIA);
  }

  /**
   * Starts the turn.
   */
  public void startTurn()
  {
    //Fill player hand
  }

  /**
   * Returns the current hand of cards for the player.
   * @return a Hand containing this client's hand.
   */
  public ArrayList<PolicyCard> getHand()
  {
    return player.getDeck().getHand();
  }
}
