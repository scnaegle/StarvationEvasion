package starvationevasion.teamrocket.models;

import starvationevasion.common.*;
import starvationevasion.server.Server;
import starvationevasion.server.ServerState;
import starvationevasion.sim.Region;
import starvationevasion.teamrocket.main.GameController;
import starvationevasion.teamrocket.messages.EnumGameState;
import starvationevasion.teamrocket.server.GameClock;

import java.io.Serializable;
import java.util.IllegalFormatException;
import java.util.Map;

/**
 * Created by scnaegl on 11/22/15.
 * <p>
 * Represent the state of the StarvationEvasion game from one player's
 * point of view. The full state of the game is kept on the server. The
 * server sends messages of type ClientGameState to each player when the game
 * state changes. Every player receives a different message that reflects
 * their own view of the status of the game.
 */
public class ClientGameState implements Serializable
{
  public ServerState serverState;
  public EnumGameState gameState;
  public EnumRegion myRegion;
  // The current state of the game as defined by the EnumGameState
  public GameClock stopwatch; // Current timer of the stop
  public int currentYear; // Current year of game
  public int currentTurn; // Current turn count in the game
  public EnumPolicy[] hand; // Current player's hand
  public WorldData worldData; // all the word data
  public Map<EnumRegion, RegionHistory> regionHistories;
  public Map<EnumRegion, PolicyVote[]> policyVotes;
  // All players votes for each card
  public ChatHistory chatHistory; // Chat history
  public GameController gameController;


  public ClientGameState(EnumGameState gameState) {
    this.gameState = gameState;
  }

  public ClientGameState(EnumGameState gameState, EnumRegion region) {
    this.gameState = gameState;
    this.myRegion = region;
  }

  public ClientGameState(EnumGameState gameState, GameClock stopwatch,
                         int current_year, int current_turn, ChatHistory chatHistory)

  {
    this(gameState, stopwatch, current_year, current_turn, chatHistory, null);
  }

  public ClientGameState(EnumGameState gameState, GameClock stopwatch, int currentYear, int currentTurn,
                         ChatHistory chatHistory, Map<EnumRegion, PolicyVote[]> policyVotes)

  {
    this.gameState = gameState;
    this.stopwatch = stopwatch;
    this.currentYear = currentYear;
    this.currentTurn = currentTurn;
    this.chatHistory = chatHistory;
    this.policyVotes = policyVotes;
  }


  private void initializeGame(String gameType, String ip, int port)
  {
    if (gameType.equals("singlePlayer"))
    {
//      Client client = new Client("127.0.0.1", ServerConstants.DEFAULT_PORT);
      //Will need to spawn a bunch of AI Clients
      Server server = new Server(
          "file://C:/Users/Tyler/Desktop/School/CS351/StarvationEvasion/data" +
              "/password_file.tmpl", null);

    }
    if (gameType.equals("newMultiPlayer"))
    {

    }
    if (gameType.equals("joinMultiPlayer"))
    {
//        Client client = new Client(ip, port);
    }
  }

  synchronized public void setGameState(ServerState serverState) {
    this.serverState = serverState;
    switch(serverState) {
      case LOGIN:
        this.gameState = EnumGameState.GAME_ROOM;
        break;
      case BEGINNING:
        this.gameState = EnumGameState.BEGINNING;
        break;
      case DRAWING:
        break;
      case DRAFTING:
        this.gameState = EnumGameState.DRAFTING;
        break;
      case VOTING:
        this.gameState = EnumGameState.VOTING;
        break;
      case WIN:
        this.gameState = EnumGameState.END;
        break;
      case LOSE:
        this.gameState = EnumGameState.END;
        break;
      case END:
        this.gameState = EnumGameState.END;
        break;
    }
  }

  synchronized public void setHand(EnumPolicy[] hand) {
    this.hand = hand;
  }

  /**
   * returns the games state, or phase of the game.
   *
   * @return the phase of the state
   */
  public EnumGameState getGameState()
  {
    return gameState;
  }

  /**
   * The time that has elapsed
   *
   * @return the time that has elapsed
   */
  public GameClock getStopwatch()
  {
    return stopwatch;
  }

  /**
   * returns the current year that the game is on
   *
   * @return the current year of the game
   */
  public int getCurrentYear()
  {
    return currentYear;
  }

  /**
   * The turn that we are on.
   *
   * @return the current turn that everybody is on
   */
  public int getCurrentTurn()
  {
    return currentTurn;
  }

  /**
   * Gets the chat history
   *
   * @return the chat history that people have used
   */
  public ChatHistory getChatHistory()
  {
    return chatHistory;
  }

  /**
   * getting the votes that people have voted on
   *
   * @return the policies that people have voted on
   */
  public Map<EnumRegion, PolicyVote[]> getPolicyVotes()
  {
    return policyVotes;
  }

  public void updateGameState(EnumGameState turnPhase)
  {
    switch (turnPhase)
    {
      case LOGIN:

      case DRAFTING:
        currentTurn++;
        currentYear += 3;

      case VOTING:

      case BEGINNING:
        // This should do the simulation. From Joels code it looks as though
        // It will be simulated once and then use the simulator to build on
        // itself
        // till 2050
        // Simulator simulator = new Simulator(1980);

        //need to check to make sure that things are valid

        String typeOfPlayerGame = gameController.getPlayerMode();
        String ip = gameController.getPlayerIP();
        String portString = gameController.getPlayerPort();
        int portNumber;
        try
        {
          portNumber = Integer.parseInt(portString);
//          initlizeGame(typeOfPlayerGame, ip, portNumber);
        }
        catch (IllegalFormatException e)
        {
          e.printStackTrace();
        }


      case VISUALIZATION:

      case END:
        // we will have to place a method to determine the winner
        // Server.determineWinner();
    }
  }

  synchronized public void updateWorldData(WorldData worldData) {
    this.worldData = worldData;
    for(EnumRegion enumRegion : EnumRegion.values()) {
      RegionHistory regionHistory = regionHistories.get(enumRegion);
      RegionData regionData = worldData.regionData[enumRegion.ordinal()];
      regionHistory.addTotalRevenue(regionData.revenueBalance);
      regionHistory.addPopulation(regionData.population);
      regionHistory.addUndernourished(regionData.undernourished);
      regionHistory.addHDI(regionData.humanDevelopmentIndex);
      for (EnumFood food : EnumFood.values()) {
        regionHistory.addCropProduced(food, regionData.foodProduced[food.ordinal()]);
        regionHistory.addCropRevenue(food, regionData.foodIncome[food.ordinal()]);
        regionHistory.addFoodExported(food, regionData.foodExported[food.ordinal()]);
        regionHistory.addFarmArea(food, regionData.farmArea[food.ordinal()]);
      }
    }
  }

}
