package starvationevasion.teamrocket.main;

import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;
import starvationevasion.teamrocket.models.Player;
import starvationevasion.teamrocket.models.Region;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * GameController handles the main game logic and movement between phases.
 */
public class GameController
{
  private Player player;
  private final Main MAIN;
  private HashMap<EnumRegion, Region> regions = new HashMap<>();

  GameController(Main main)
  {
    this.MAIN = main;
    for (EnumRegion enumRegion : EnumRegion.values())
    {
      regions.put(enumRegion, new Region(enumRegion));
    }
  }


  /**
   * Destroys any old games, starts a new game with selected region.
   *
   * @param region player's starting region
   * @return a copy of the new player for convenience.
   */
  public Player startNewGame(EnumRegion region)
  {
    destroyGame(); //Destroy old game if exists.
    this.player = new Player(region);

    MAIN.switchScenes(3);

    return this.player;
  }

  public void switchToSelectRegion()
  {
    MAIN.switchScenes(2);
  }

  /**
   * Returns the current hand of cards for the player.
   *
   * @return a Hand containing this client's hand.
   */
  public ArrayList<PolicyCard> getHand()
  {
    return null; //player.getRegion().getDeck().getHand();
  }

  /**
   * Destroys the current game cleanly.
   */
  private void destroyGame()
  {
    //Destruction code for old game. Make sure it is garbage collectable, end any timers, threads, etc.
  }

  public void finishedCardDraft()
  {
    MAIN.switchScenes(4);
  }

  public void finishedVoting()
  {
    MAIN.switchScenes(3);
  }

  public Region getRegion(EnumRegion enumRegion)
  {
    return regions.get(enumRegion);
  }

}
