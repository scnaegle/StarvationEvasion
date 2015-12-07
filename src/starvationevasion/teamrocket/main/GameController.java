package starvationevasion.teamrocket.main;

import starvationevasion.common.*;
import starvationevasion.common.messages.*;
import starvationevasion.server.Server;
import starvationevasion.server.ServerConstants;
import starvationevasion.server.ServerState;
import starvationevasion.teamrocket.PlayerInterface;
import starvationevasion.teamrocket.gui.EnumScene;
import starvationevasion.teamrocket.messages.EnumGameState;
import starvationevasion.teamrocket.models.ClientGameState;
import starvationevasion.teamrocket.models.Player;
import starvationevasion.teamrocket.models.RegionHistory;
import starvationevasion.teamrocket.server.Client;

import java.util.*;

/**
 * GameController handles the main game logic and movement between phases.
 */
public class GameController
{
  public PlayerInterface player;
  private final Main MAIN;
  private HashMap<EnumRegion, RegionHistory> regions = new HashMap<>();
  private boolean singlePlayer;
  private boolean newMultiPlayer;
  private boolean joinMultiPlayer;
  private String playerUsername = "player";
  private String playerPassword = "pass";
  private String playerIP;
  private String playerPort;
  private String salt;
  private boolean gotSalt = false;


  private PolicyCard draft1, draft2;

  private Stack<String> error_messages = new Stack<>();
  private boolean successfulLogin = false;
  private Client client;

  private AvailableRegions availableRegions;
  private EnumScene currentScene;
  private boolean needToInitialize;

  private boolean AI;

  public int currentYear;
  public int currentTurn;

  GameController(Main main, boolean AI)
  {
    this.MAIN = main;
    for (EnumRegion enumRegion : EnumRegion.values())
    {
      regions.put(enumRegion, new RegionHistory(enumRegion));
    }
  }

  GameController(Main main) {
    this(main, false);
  }


  /**
   * Destroys any old games, starts a new game with selected region.
   *
   * @param region player's starting region
   * @return a copy of the new player for convenience.
   */
  public PlayerInterface startSinglePlayerGame(EnumRegion region)
  {
    destroyGame(); //Destroy old game if exists.
    this.player = new Player(region, null, this);

    //BEGIN placeholder hand code should be removed once hand is retrieved from the server
   EnumPolicy[] hand = new EnumPolicy[7];

    for (int i = 0; i < 7; i++)
    {
      EnumPolicy policy = EnumPolicy.values()[Util.rand.nextInt(EnumPolicy.values().length)];
      hand[i] = policy; //PolicyCard.create(player.ENUM_REGION, policy);
    }
    player.setHand(hand);
    //END placeholder code.

    needToInitialize = true;
    changeScene(EnumScene.DRAFT_PHASE);

    Server server = new Server(GameController.class.getResource("/config/sologame.tsv").getPath(), null);
    server.setDaemon(true);
    server.start(); //start() needs to be public to start our own copy.
    startClientAndAttemptLogin("127.0.0.1");

    //Server will need to spawn a bunch of AI Clients
    //Need to detect what zones are left and fill with AI


    //Need to send ready to start message to server


    //

    return this.player;
  }


  private void startClientAndAttemptLogin(String host) {
    if (host != null) {
      client = new Client(host, ServerConstants.DEFAULT_PORT, this);
    } else {
      client = new Client(playerIP, Integer.parseInt(playerPort), this);
    }

    try
    {
      while(!gotSalt)
      {
        Thread.sleep(17l);
      }
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }

    client.send(new Login(playerUsername, salt, playerPassword));
  }


  private void initializeGame(String gameType, String ip, int port)
  {
  }

  public void switchToScene(ServerState serverState)
  {
    switch (serverState)
    {
      case LOGIN:
        if (player.getEnumRegion() == null)
        {
          switchToSelectRegion();
        }
        else
        {
          switchToGameScene();
        }
        break;
      case BEGINNING:
        switchToGameScene();
        break;
      case DRAWING:
        break;
      case DRAFTING:
        break;
      case VOTING:
        break;
      case WIN:
        break;
      case LOSE:
        break;
      case END:
        break;
    }
  }

  /**
   * Switches scene to select region scene
   */
  public void switchToSelectRegion()
  {
    changeScene(EnumScene.REGION_CHOICE);
  }

  /**
   * Switches scene to login scene
   */
  public void switchToLoginScene()
  {
    changeScene(EnumScene.LOGIN);
  }

  public void switchToGameScene()
  {
    needToInitialize = true;
    changeScene(EnumScene.GAME_ROOM);
  }

  void changeScene(EnumScene nextScene)
  {
    currentScene = nextScene;
    MAIN.setScene(currentScene);

  }

  public void openChat()
  {
    MAIN.openChat();
  }

  /**
   * Get the card text of the card in the given position
   * of the player's hand
   *
   * @param cardPosition position of card in hand
   * @return text of card
   */
  public PolicyCard getCard(int cardPosition)
  {
    return player.getCard(cardPosition);
  }

  /**
   * Handles the actions the player can do, play or discard cards, from
   * the GUI and update the Player class with this information
   *
   * @param cardPositions array of card positions that is related to the action being down
   * @param playedCard    True is the cards are being played, False if cards are being discarded
   */
  public void playerAction(int[] cardPositions, boolean playedCard)
  {
    if (!playedCard)
    {
      for (int i = 0; i < cardPositions.length; i++)
      {
        player.discardCard(cardPositions[i]);
      }
    }

    else
    {
      if (cardPositions.length == 1)
      {
        player.selectedCards(cardPositions[0], -1);
      }
      else if (cardPositions.length == 2)
      {
        player.selectedCards(cardPositions[0], cardPositions[1]);
      }
      else
      {
        player.selectedCards(-1, -1);
      }
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
    needToInitialize = true;
    changeScene(EnumScene.VOTE_PHASE);
  }

  /**
   * switches to the main game scene after voting is finsihed
   */
  public void finishedVoting()
  {
    needToInitialize = true;
    changeScene(EnumScene.DRAFT_PHASE);
  }

  /**
   * returns the region corresponding to the Enum a region is passed in
   *
   * @param enumRegion the enum of a region
   * @return the region
   */
  public RegionHistory getRegion(EnumRegion enumRegion)
  {
    return regions.get(enumRegion);
  }

  /**
   * For MainGuiController use.
   *
   * @return saved region.
   */
  public EnumRegion getMyRegion()
  {
    return player.getEnumRegion();
  }

  public void setAvailableRegions(AvailableRegions availableRegions)
  {
    this.availableRegions = availableRegions;

    for (Map.Entry<EnumRegion, String> entry : availableRegions.takenRegions.entrySet())
    {
      if(Objects.equals(entry.getValue(), playerUsername))
      {
        if(entry.getKey() != getMyRegion())
        {
          //TODO set our region if assigned by server.
        }
      }
    }
  }

  public AvailableRegions getAvailableRegions()
  {
    return availableRegions;
  }

  /**
   * Tries to login into the designated server.
   *
   * @param username
   * @param password
   * @param ipAddress
   * @param networkPort
   * @return
   */
  public boolean tryLogin(String username, String password, String ipAddress, String networkPort)
  {
    this.playerUsername = username;
    this.playerPassword = password;
    this.playerIP = ipAddress;
    this.playerPort = networkPort;
    startClientAndAttemptLogin(null);

    while(!getSuccessfulLogin()) {
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return true;
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
   * @param readyToBegin
   */
  public void setStartGame(ReadyToBegin readyToBegin)
  {
    Main.GAME_CLOCK.setTimeLeft((readyToBegin.gameStartServerTime - readyToBegin.currentServerTime) * 1000);
    player.setGameState(EnumGameState.BEGINNING);
    // TODO update GUI as needed.
  }

  /**
   * Store the new Regions
   *
   * @param worldData
   */
  public void updateWorldData(WorldData worldData)
  {
    player.updateWorldData(worldData);
    // TODO update GUI with the new world data - probably graphs and such
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
  public void setVoteStatus(VoteStatus voteStatus)
  {
    player.updateVoteStatus(voteStatus);
    // TODO update GUI with new vote status
  }

  public void setPlayerVote(EnumRegion region, VoteType voteType)
  {

  }

  /**
   * Store the hands of all players
   */
  public void setHand(EnumPolicy[] hand) {
    player.setHand(hand);
    // TODO update GUI with the new hand
  }

  public void setGameState(ServerState gameState) {
    player.setGameState(gameState);
    // TODO update GUI with the new game state
  }

  /**
   * Process new chat message.
   *
   * @param message
   */
  public void setChat(ServerChatMessage message)
  {
    player.receiveChatMessage(message);
    //TODO update GUI with new message
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
    else if (joinMultiPlayer)
    {
      mode = "joinMultiPlayer";
    }
    return mode;
  }

  public boolean validAddress(String address)
  {
    final String regex = "^\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+$";

    return address.matches(regex);
  }

  public void savePlayerPort(String playerPort)
  {
    this.playerPort = playerPort;
  }

  public boolean validPort(String port)
  {
    final String regex = "^\\d{2,6}+$";

    return port.matches(regex);
  }

  public String getPlayerIP()
  {
    return playerIP;
  }

  public String getPlayerPort()
  {
    return playerPort;
  }

  public void addErrorMessage(String error_message)
  {
    error_messages.push(error_message);
  }

  public String getErrorMessage()
  {
    return error_messages.pop();
  }

  public void setSuccessfulLogin(boolean successfulLogin)
  {
    this.successfulLogin = successfulLogin;
  }

  public boolean getSuccessfulLogin()
  {
    return successfulLogin;
  }

  public String getPlayerUsername()
  {
    return playerUsername;
  }

  public void updateTimer(PhaseStart phaseStart) {
    Main.GAME_CLOCK.setTimeLeft((phaseStart.phaseEndTime - phaseStart.currentServerTime) * 1000);
    setGameState(phaseStart.currentGameState);
    // TODO update GUI appropriately
  }

  public EnumScene getCurrentScene()
  {
    return currentScene;
  }

  public boolean initGUI()
  {
    boolean status = needToInitialize;
    needToInitialize = false;
    return status;
  }

  public void setSalt(String salt)
  {
    this.salt = salt;
    this.gotSalt = true;
  }

  public void playDrafts(PolicyCard draft1, PolicyCard draft2)
  {
    //TODO send to client. No need to update player since turn is over. Player shouldn't need this information.

  }
}
