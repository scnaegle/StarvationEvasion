package starvationevasion.teamrocket.main;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;
import starvationevasion.teamrocket.AI.AI;
import starvationevasion.teamrocket.AI.EnumAITypes;
import starvationevasion.teamrocket.gui.GuiController;
import starvationevasion.teamrocket.models.Card;
import starvationevasion.teamrocket.models.Player;
import starvationevasion.teamrocket.models.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * GameController handles the main game logic and movement between phases.
 */
public class GameController
{
  private Player player;
  private AI ai;
  private final Main MAIN;
  private HashMap<EnumRegion, Region> regions = new HashMap<>();
  private GuiController gui;

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
    this.player = new Player(region, null, this, null);
    LinkedList<Card>hand = new LinkedList<>();
    Card card1 = new Card(EnumPolicy.Clean_River_Incentive);
    hand.add(card1);
    for(int i = 0; i < 6; i++)
    {
      Card card2 = new Card(EnumPolicy.GMO_Seed_Insect_Resistance_Research);
      hand.add(card2);
    }
    ai = new AI(EnumRegion.MIDDLE_AMERICA, EnumAITypes.DUMB, this, hand);
    ai.discardCard(3);
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

  /**
   * For GuiController use.
   * @return saved region.
   */
  public EnumRegion getMyRegion()
  {
   return player.ENUM_REGION;
  }

}
