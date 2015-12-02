package starvationevasion.teamrocket.main;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;
import starvationevasion.common.Util;
import starvationevasion.common.messages.Login;
import starvationevasion.common.messages.RegionChoice;
import starvationevasion.server.Server;
import starvationevasion.server.ServerConstants;
import starvationevasion.teamrocket.AI.AI;
import starvationevasion.teamrocket.AI.EnumAITypes;
import starvationevasion.teamrocket.gui.GuiController;
import starvationevasion.teamrocket.models.Card;
import starvationevasion.teamrocket.models.Player;
import starvationevasion.teamrocket.models.Region;
import starvationevasion.teamrocket.server.Client;
import starvationevasion.teamrocket.server.Stopwatch;

import java.nio.channels.NoConnectionPendingException;
import java.util.*;

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
  PolicyCard card;

  private Stack<String> error_messages = new Stack<>();
  private boolean successfullLogin = false;
  private Client client;

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
    this.player = new Player(region, null, this);

    PolicyCard card;
    LinkedList<PolicyCard> hand = new LinkedList<PolicyCard>();

    for(int i = 0; i < 7; i++)
    {
      EnumPolicy policy = EnumPolicy.values()[Util.rand.nextInt(EnumPolicy.values().length)];
      card = PolicyCard.create(player.ENUM_REGION,policy);
      hand.add(card);
    }
    player.setHand(hand);
    MAIN.switchScenes(3);
    if (singlePlayer)
    {
      Client client = new Client("127.0.0.1", ServerConstants.DEFAULT_PORT, this);
      //Will need to spawn a bunch of AI Clients
      Server server = new Server(
          "file://C:/Users/Tyler/Desktop/School/CS351/StarvationEvasion/data" +
              "/password_file.tmpl");

    }
    if (newMultiPlayer)
    {

    }
    if (joinMultiPlayer)
    {
      Client client = new Client(playerIP, Integer.parseInt(playerPort), this);
    }

    return this.player;
  }
  private void initializeGame(String gameType, String ip, int port)
  {
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
  public void switchToLoginScene()
  {
    MAIN.switchScenes(5);
  }

  public void switchToGameScene()
  {
    MAIN.switchScenes(6);
  }

  public void openChat()
  {
    MAIN.openChat();
  }

  /**
   * Get the card text of the card in the given position
   * of the player's hand
   * @param cardPosition position of card in hand
   * @return text of card
   */
  public String getCardText(int cardPosition)
  {
    return player.getHand().get(cardPosition).getGameText();
  }

  /**
   * Handles the actions the player can do, play or discard cards, from
   * the GUI and update the Player class with this information
   * @param cardPositions array of card positions that is related to the action being down
   * @param playedCard True is the cards are being played, False if cards are being discarded
   */
  public void playerAction(int[] cardPositions, boolean playedCard)
  {
    if(!playedCard)
      for(int i = 0; i < cardPositions.length; i++)
      {
        player.discardCard(cardPositions[i]);
      }

    else
    {
      if(cardPositions.length == 1) player.selectedCards(cardPositions[0], -1);
      else if(cardPositions.length == 2) player.selectedCards(cardPositions[0], cardPositions[1]);
      else player.selectedCards(-1,-1);
    }
  }

  /**
   * Destroys the current game cleanly.
   */
  private void destroyGame()
  {
    //Destruction code for old game. Make sure it is garbage collectable, end
    // any timers, threads, etc.
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
   *
   * @param enumRegion the enum of a region
   * @return the region
   */
  public Region getRegion(EnumRegion enumRegion)
  {
    return regions.get(enumRegion);
  }

  /**
   * For GuiController use.
   *
   * @return saved region.
   */
  public EnumRegion getMyRegion()
  {
    return player.ENUM_REGION;
  }

  /**
   * Stores our login information for the server.
   *
   * @param login information of logins
   */
  public void setLogin(Login login)
  {

  }

  /**
   * This method handles when a region selection is performed.
   *
   * @param choice The region choice
   */
  public void setSelectRegion(RegionChoice choice)
  {

  }

  /**
   * Start the game after the game room is done.
   *
   * @param message
   */
  public void setStartGame(String message)
  {

  }

  /**
   * Update the timer with time left in current stage.
   *
   * @param stopwatch seconds
   */
  public void setTimer(Stopwatch stopwatch)
  {
    if (this.stopwatch == null)
    {
      this.stopwatch = new Stopwatch(stopwatch.getInterval());
    }
    else
    {
      this.stopwatch.setInterval(stopwatch.getInterval());
    }
  }

  /**
   * Store the new Regions
   *
   * @param newRegions
   */
  public void setRegionStats(ArrayList<Region> newRegions)
  {

  }

  /**
   * Sets the cards that will be voted on in voting stage.
   *
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
   *
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
    if (singlePlayer)
    {
      mode = "singlePlayer";
    }
    else if (newMultiPlayer)
    {
      mode = "newMultiPlayer";
    }
    else if (joinMultiPlayer) mode = "joinMultiPlayer";
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

  public String checkAddress(String address)
  {
    final String regex = "^\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+$";

    if (address.matches(regex)) {
      return "good";
    } else {
      return "bad";
    }
  }

  public void savePlayerPort(String playerPort)
  {
    this.playerPort = playerPort;
  }

  public String checkPort(String port)
  {
    final String regex = "^\\d{2,4}+$";

    if (port.matches(regex)) {
      return "good";
    } else {
      return "bad";
    }
  }

  public String getPlayerIP()
  {
    return playerIP;
  }

  public String getPlayerPort()
  {
    return playerPort;
  }

  public void addErrorMessage(String error_message) {
    error_messages.push(error_message);
  }

  public String getErrorMessage() {
    return error_messages.pop();
  }

  public void setSuccessfullLogin(boolean successfullLogin) {
    this.successfullLogin = successfullLogin;
  }

  public boolean getSuccessfullLogin() {
    return successfullLogin;
  }

  public boolean verifyIPAddress()
  {
    //parse ipaddress and make sure its good
    return true;
  }

  public String getPlayerUsername()
  {
    return playerUsername;
  }

}
