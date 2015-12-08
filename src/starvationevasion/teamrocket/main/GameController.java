package starvationevasion.teamrocket.main;

import starvationevasion.common.*;
import starvationevasion.common.messages.*;
import starvationevasion.server.Server;
import starvationevasion.server.ServerConstants;
import starvationevasion.server.ServerState;
import starvationevasion.teamrocket.AI.EnumAITypes;
import starvationevasion.teamrocket.PlayerInterface;
import starvationevasion.teamrocket.gui.EnumScene;
import starvationevasion.teamrocket.messages.EnumGameState;
import starvationevasion.teamrocket.models.Player;
import starvationevasion.teamrocket.models.RegionHistory;
import starvationevasion.teamrocket.server.Client;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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
  private String salt;
  private boolean gotSalt = false;


  private PolicyCard draft1, draft2;

  private Stack<String> error_messages = new Stack<>();
  private Boolean successfulLogin;
  private Client client;

  private AvailableRegions availableRegions;
  private EnumScene currentScene;
  private boolean needToInitialize;

  private boolean AI = false;

  /**
   * This for us to keep track of whether or not the player is allowed by the server
   * to pick another region or if they are specified by the server to have a set one.
   */
  private boolean canPickRegion = true;

  public int currentYear;
  public int currentTurn;
  public EnumScene desiredScene;
  public int population;
  public int HDI;

  /**
   * The main controller that talks with the GUI in order to hear player input
   *
   * @param main The main class
   * @param AI Decides if there is an AI
   */
  public GameController(Main main, boolean AI)
  {
    this.AI = AI;
    this.MAIN = main;
    for (EnumRegion enumRegion : EnumRegion.values())
    {
      regions.put(enumRegion, new RegionHistory(enumRegion));
    }
    this.player = new Player(this);
  }

  public GameController(Main main) {
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
    boolean fromJar = GameController.class.getResource("/config/sologame.tsv").getPath().startsWith("jar:");
    Server server = null;
    Path soloGamePath = FileSystems.getDefault().getPath(".", "sologame.tsv");
    try {
      Files.write(soloGamePath, "player\tpass".getBytes(), StandardOpenOption.CREATE);
      if (fromJar) {
        server = new Server(soloGamePath.toString(),
            ("java -cp " + System.getProperty("user.dir") + "starvationevasion.teamrocket.server.Client --environment").split(" "));
      } else {
        server = new Server(soloGamePath.toString(),
            "java -classpath out/production/StarvationEvasion/ starvationevasion.teamrocket.server.Client --environment".split(" "));
      }
      server.setDaemon(true);
      server.start(); //start() needs to be public to start our own copy.
      startClientAndAttemptLogin("127.0.0.1");
      if(waitForLoginResponse()) {
        setChosenRegion(region);
      } else {
        //TODO show error and try again
      }

      // Clean up file
      Files.delete(soloGamePath);
    } catch (IOException e) {
      e.printStackTrace();
    }

    needToInitialize = true;
    changeScene(EnumScene.GAME_ROOM);
    return this.player;
  }

  private boolean startClientAndAttemptLogin(String host) {
    try
    {
    if (host != null) {
      client = new Client(host, ServerConstants.DEFAULT_PORT, this);
    } else {
      client = new Client(playerIP, ServerConstants.DEFAULT_PORT, this);
    }

      while(!gotSalt)
      {
        Thread.sleep(17l);
      }
    }
    catch (Exception e)
    {
      return false;
    }

    client.send(new Login(playerUsername, salt, playerPassword));

    return true;
  }

  /**
   * switches to a certain scene depending on what we want
   * @param serverState the type of state that we want to switch to
   */
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

  /**
   * switches to game scene
   */
  public void switchToGameScene()
  {
    needToInitialize = true;
    changeScene(EnumScene.GAME_ROOM);
  }

  /**
   * changes the scene that the player is in
   *
   * @param nextScene the next scene that people want to
   */
  public void changeScene(EnumScene nextScene)
  {
    currentScene = nextScene;
    desiredScene = nextScene;
    MAIN.setScene(currentScene);

  }

  /**
   * opens the chat for the player to use
   */
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

  /**
   * Set the list of available regions left to choose from
   * @param availableRegions Available regions to choose from
   */
  public void setAvailableRegions(AvailableRegions availableRegions)
  {
    this.availableRegions = availableRegions;
  }

  /**
   * Get the list of available regions that are left to choose from
   * @return AvailableRegions Available regions object that contains both the list
   *         of available regions and the list of already taken regions.
   */
  public AvailableRegions getAvailableRegions()
  {
    return availableRegions;
  }

  /**
   * Set the assigned region. This will create a new player is one hasn't already been
   * created as in the case of joining a hosted game.
   */
  public void setAssignedRegion(EnumRegion region) {
    if (player == null) {
      this.player = new Player(region, (AI ? EnumAITypes.BASIC : null), this);
    } else {
      player.setEnumRegion(region);
    }
  }

  /**
   * Set whether or not the current player is allowed to pick a new region, as specified
   * by the game server.
   * @param canPickRegion boolean True if can pick a region, False otherwise
   */
  public void setCanPickRegion(boolean canPickRegion) {
    this.canPickRegion = canPickRegion;
  }

  /**
   * Get whether or not the current player is allowed to pick a new region.
   * @return boolean True if can pick a region, False otherwise
   */
  public boolean getCanPickRegion() {
    return canPickRegion;
  }

  /**
   * Tries to login into the designated server.
   *
   * @param username The user name
   * @param password The password
   * @param ipAddress The IPAddress
   * @return True if this they are able to log in
   */
  public boolean tryLogin(String username, String password, String ipAddress)
  {
    this.playerUsername = username;
    this.playerPassword = password;
    this.playerIP = ipAddress;

    setSuccessfulLogin(null);
    if(!startClientAndAttemptLogin(null)) return false;

    return waitForLoginResponse();
  }

  /**
   * Start the game after the game room is done.
   *
   * @param readyToBegin decider to allow the player to be ready to start the game
   */
  public void setStartGame(ReadyToBegin readyToBegin)
  {
    if (!AI) {
      Main.GAME_CLOCK.setTimeLeft((readyToBegin.gameStartServerTime - readyToBegin.currentServerTime) * 1000);
    }
    player.setGameState(EnumGameState.BEGINNING);
    // TODO update GUI as needed.
  }

  /**
   * Store the new Regions
   *
   * @param worldData passed in data from the sim
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

  /**
   * Send a message to the server with the current player's vote preference.
   * This takes the region that the card belongs to and the voteType preference
   * for the player.
   * @param cardOwner the region that the card belongs to
   * @param voteType player's voting preference.
   */
  public void setPlayerVote(EnumRegion cardOwner, VoteType voteType)
  {
    client.send(new Vote(cardOwner, voteType));
  }

  /**
   * Sends the chosen region to the server
   * @param region Player's chosen region
   */
  public void setChosenRegion(EnumRegion region) {
    if (canPickRegion) {
      client.send(new RegionChoice(region));
    }
  }

  /**
   * Parse the BeginGame object from the server and start the game for player
   * @param beginGame Allows the game the game to begin
   */
  public void beginGame(BeginGame beginGame) {
    for(Map.Entry<EnumRegion, String> entry : beginGame.finalRegionChoices.entrySet()) {
      if (Objects.equals(playerUsername, entry.getValue()))
      {
        player.setEnumRegion(entry.getKey());
        break;
      }
    }
  }

  /**
   * Store the hands of all players
   */
  public void setHand(EnumPolicy[] hand) {
    player.setHand(hand);
    // TODO update GUI with the new hand
  }

  /**
   * Sets the new game state
   *
   * @param gameState the game state of what we want
   */
  public void setGameState(ServerState gameState) {
    player.setGameState(gameState);
    // TODO update GUI with the new game state
  }

  /**
   * Process new chat message.
   *
   * @param message the message that we are passing
   */
  public void setChat(ServerChatMessage message)
  {
    player.receiveChatMessage(message);
    //TODO update GUI with new message
  }

  /**
   * Sets the single player mode
   *
   * @param on boolean to decide if this is single player
   */
  public void setSinglePlayerMode(boolean on)
  {
    singlePlayer = on;
  }

  /**
   * Sets the multiplayer mode
   *
   * @param on decides if this is multiplayer
   */
  public void setJoinMultiPlayerMode(boolean on)
  {
    joinMultiPlayer = on;
  }

  /**
   * gets the playerMode that people can play
   *
   * @return the Playermode
   */
  public String getPlayerMode()
  {
    String mode = null;
    if (singlePlayer)
    {
      mode = "singlePlayer";
    }
    else if (joinMultiPlayer)
    {
      mode = "joinMultiPlayer";
    }
    return mode;
  }

  /**
   * lookss to see if it is given a valid IP address
   * @param address the IP adress
   * @return if it is a vlaid IP or not
   */
  public boolean validAddress(String address)
  {
    final String regex = "^\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+$";

    return address.matches(regex);
  }

  /**
   * Gets the IP address
   * @return the IP address
   */
  public String getPlayerIP()
  {
    return playerIP;
  }

  /**
   * Adds an error message
   *
   * @param error_message The error message
   */
  public void addErrorMessage(String error_message)
  {
    error_messages.push(error_message);
  }

  /**
   * Gets the error messages
   *
   * @return returns the error message
   */
  public String getErrorMessage()
  {
    return error_messages.pop();
  }

  /**
   * Successful login was succsessful
   *
   * @param successfulLogin login was successful
   */
  public void setSuccessfulLogin(Boolean successfulLogin)
  {
    this.successfulLogin = successfulLogin;
  }

  /**
   * Gets it if a player sucessfully logged in
   * @return if it was succseful login
   */
  public Boolean getSuccessfulLogin()
  {
    return successfulLogin;
  }

  /**
   * Since we need to wait to see if our login request was accepted, let's
   * spin in a loop until we get a login response back whether it was accepted
   * or denied.
   * @return True if login was successful, False otherwise
   */
  public boolean waitForLoginResponse() {
    while(getSuccessfulLogin() == null) {
      try {
        Thread.sleep(17l);
      } catch (InterruptedException e) {
        e.printStackTrace();
        return false;
      }
    }
    return getSuccessfulLogin();
  }

  /**
   * gets the players username
   * @return the username
   */
  public String getPlayerUsername()
  {
    return playerUsername;
  }

  /**
   * updates the timer.
   *
   * @param phaseStart starts the timer
   */
  public void updateTimer(PhaseStart phaseStart) {
    if (!AI) {
      Main.GAME_CLOCK.setTimeLeft((phaseStart.phaseEndTime - phaseStart.currentServerTime) * 1000);
    }
    setGameState(phaseStart.currentGameState);
    // TODO update GUI appropriately
  }

  /**
   * gets the currentScene
   * @return the current scene
   */
  public EnumScene getCurrentScene()
  {
    return currentScene;
  }

  /**
   * initlizes the GUI
   * @return the initalGUI
   */
  public boolean initGUI()
  {
    boolean status = needToInitialize;
    needToInitialize = false;
    return status;
  }

  /**
   * Salts the password
   * @param salt string to salt the password
   */
  public void setSalt(String salt)
  {
    this.salt = salt;
    this.gotSalt = true;
  }

  /**
   * draft cards that a player can use
   * @param draft1 the first card a player drafts
   * @param draft2 the second card a player drafts
   */
  public void playDrafts(PolicyCard draft1, PolicyCard draft2)
  {
    if(draft1 != null)
    {
      this.draft1 = draft1;
    }

    if(draft2 != null)
    {
      this.draft2 = draft2;
    }

    client.send(new DraftCard(draft1));
    client.send(new DraftCard(draft2));
  }

  /**
   * Gets the drafted cards that players have played
   * @return the policy cards
   */
  public PolicyCard[] getDraftedCards()
  {
    PolicyCard[] drafts = new PolicyCard[2];
    drafts[0] = draft1;
    drafts[1] = draft2;
    return drafts;
  }

  /**
   * sends a message to the server
   *
   * @param message the message
   */
  public void sendMessage(String message)
  {
    client.send(new ClientChatMessage(message, EnumRegion.US_REGIONS));
  }

  public void setPhaseStart(PhaseStart phaseStart)
  {
    if(!AI)
    {
      switch (phaseStart.currentGameState)
      {

        case LOGIN:
          break;
        case BEGINNING:
          break;
        case DRAWING:
          break;
        case DRAFTING:
          needToInitialize = true;
          desiredScene = EnumScene.DRAFT_PHASE;
          break;
        case VOTING:
          needToInitialize = true;
          desiredScene = EnumScene.VOTE_PHASE;
          break;
        case WIN:

          break;
        case LOSE:
          break;
        case END:
          break;
      }
    }
  }
}
