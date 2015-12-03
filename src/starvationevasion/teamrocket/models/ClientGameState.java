package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.WorldData;
import starvationevasion.server.Server;
import starvationevasion.sim.CardDeck;
import starvationevasion.teamrocket.main.GameController;
import starvationevasion.teamrocket.messages.EnumGameState;
import starvationevasion.teamrocket.models.ChatHistory;
import starvationevasion.teamrocket.models.PolicyVote;
import starvationevasion.teamrocket.models.Region;
import starvationevasion.teamrocket.server.Stopwatch;

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
  public EnumGameState gameState;
  public EnumRegion myRegion;
  // The current state of the game as defined by the EnumGameState
  public GameClock stopwatch; // Current timer of the stop
  public int currentYear; // Current year of game
  public int currentTurn; // Current turn count in the game
  public EnumPolicy[] hand; // Current player's hand
  public WorldData worldData; // all the word data
  public Map<EnumRegion, PolicyVote[]> policyVotes;
  // All players votes for each card
  public ChatHistory chatHistory; // Chat history
  public GameController gameController;


  public ClientGameState(EnumGameState gameState) {
    this.gameState = gameState;
  }

<<<<<<< HEAD:src/starvationevasion/teamrocket/models/ClientGameState.java
  public ClientGameState(EnumGameState gameState, EnumRegion region) {
    this.gameState = gameState;
    this.myRegion = region;
  }

  public ClientGameState(EnumGameState gameState, Stopwatch stopwatch,
                         int current_year, int current_turn, ChatHistory chatHistory)
=======
  public ClientGameState(EnumGameState gameState, GameClock stopwatch,
                         int current_year, int current_turn, Region[] regions,
                         Map<EnumRegion, CardDeck> region_decks,
                         ChatHistory chatHistory)
>>>>>>> Prepping various code for GameClock implementation.:src/starvationevasion/teamrocket/server/ClientGameState.java
  {
    this(gameState, stopwatch, current_year, current_turn, chatHistory, null);
  }

<<<<<<< HEAD:src/starvationevasion/teamrocket/models/ClientGameState.java
  public ClientGameState(EnumGameState gameState, Stopwatch stopwatch, int currentYear, int currentTurn,
                         ChatHistory chatHistory, Map<EnumRegion, PolicyVote[]> policyVotes)
=======
  public ClientGameState(EnumGameState gameState, GameClock stopwatch,
                         int currentYear, int currentTurn, Region[] regions,
                         Map<EnumRegion, CardDeck> regionDecks,
                         ChatHistory chatHistory,
                         Map<EnumRegion, PolicyVote[]> policyVotes)
>>>>>>> Prepping various code for GameClock implementation.:src/starvationevasion/teamrocket/server/ClientGameState.java
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
              "/password_file.tmpl");

    }
    if (gameType.equals("newMultiPlayer"))
    {

    }
    if (gameType.equals("joinMultiPlayer"))
    {
//        Client client = new Client(ip, port);
    }
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

}
