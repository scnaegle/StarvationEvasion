package starvationevasion.teamrocket.main;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;
import starvationevasion.common.messages.Login;
import starvationevasion.common.messages.RegionChoice;
import starvationevasion.teamrocket.AI.AI;
import starvationevasion.teamrocket.AI.EnumAITypes;
import starvationevasion.teamrocket.gui.GuiController;
import starvationevasion.teamrocket.models.Card;
import starvationevasion.teamrocket.models.Player;
import starvationevasion.teamrocket.models.Region;
import starvationevasion.teamrocket.server.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * GameController handles the main game logic and movement between phases.
 */
public class GameController
{
  private Player player;
  private final Main MAIN;
  private HashMap<EnumRegion, Region> regions = new HashMap<>();
  private GuiController gui;
  private Stopwatch stopwatch;
  private boolean singlePlayer;
  private boolean newMultiPlayer;
  private boolean joinMultiPlayer;
  private String playerUsername;
  private String playerPassword;
  private String playerIP;
  private String playerPort;

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

    MAIN.switchScenes(3);

    return this.player;
  }

  /**
   * Switches scene to select region scene
   */
  public void switchToSelectRegion()
  {
    MAIN.switchScenes(2);
  }

  /**
   * Switches scene to login scene
   */
  public void switchToLoginScene(){MAIN.switchScenes(5);}
  public void switchToGameScene(){MAIN.switchScenes(6);}
  public void openChat(){ MAIN.openChat();}

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

  /**
   * Switches scenes to card drafting scene
   */
  public void finishedCardDraft()
  {
    MAIN.switchScenes(4);
  }

  /**
   * switches to the main game scene after voting is finsihed
   */
  public void finishedVoting()
  {
    MAIN.switchScenes(3);
  }

  /**
   * returns the region corrospoinding to the Enum a region is passed in
   * @param enumRegion the enum of a region
   * @return the region
   */
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

  /**
   * Stores our login information for the server.
   * @param login information of logins
   */
  public void setLogin(Login login)
  {

  }

  /**
   * This method handles when a region selection is performed.
   * @param choice The region choice
   */
  public void setSelectRegion(RegionChoice choice)
  {

  }

  /**
   * Start the game after the game room is done.
   * @param message
   */
  public void setStartGame(String message)
  {

  }

  /**
   * Update the timer with time left in current stage.
   * @param timerInterval seconds
   */
  public void setTimer(Stopwatch stopwatch)
  {
    if (this.stopwatch == null) {
      this.stopwatch = new Stopwatch(stopwatch.getInterval());
    } else {
      this.stopwatch.setInterval(stopwatch.getInterval());
    }
  }

  /**
   * Store the new Regions
   * @param newRegions
   */
  public void setRegionStats(ArrayList<Region> newRegions)
  {

  }

  /**
   * Sets the cards that will be voted on in voting stage.
   * @param cards cards to vote on.
   */
  public void setVotingCards(ArrayList<PolicyCard> cards)
  {

  }

  /**
   * Store the values of who is ready for this stage.
   */
  public void setReady()
  {

  }

  /**
   * Set the vote valutes of who has voted for what.
   */
  public void setVote()
  {

  }

  /**
   * Store the hands of all players
   */
  public void setDraw()
  {

  }

  /**
   * Process new chat message.
   * @param message
   */
  public void setChat(String message)
  {

  }

  public void setSinglePlayerMode(boolean on)
  {
    singlePlayer = on;
  }


  public void setNewMultiPlayerMode(boolean on)
  {
    newMultiPlayer = on;
  }

  public void setJoinMultiPlayerMode(boolean on)
  {
    joinMultiPlayer = on;
  }

  public String getPlayerMode()
  {
    String mode = null;
    if(singlePlayer) mode = "singlePlayer";
    else if(newMultiPlayer) mode = "newMultiPlayer";
    else if(joinMultiPlayer) mode =  "joinMultiPlayer";

    return mode;
  }

  public void savePlayerUsername(String playerUsername)
  {
    this.playerUsername = playerUsername;
  }

  public void savePlayerPassword(String playerPassword)
  {
    this.playerPassword = playerPassword;
  }

  public void savePlayerIP(String playerIP)
  {
    this.playerIP = playerIP;
  }

  public void savePlayerPort(String playerPort)
  {
    this.playerPort = playerPort;
  }
}
