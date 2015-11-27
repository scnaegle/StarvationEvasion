package starvationevasion.teamrocket.server;

import starvationevasion.common.EnumRegion;
//import starvationevasion.common.Policy;
//import starvationevasion.common.PolicyCard;
import starvationevasion.sim.CardDeck;
import starvationevasion.teamrocket.messages.EnumGameState;
import starvationevasion.teamrocket.models.ChatHistory;
import starvationevasion.teamrocket.models.Discard;
import starvationevasion.teamrocket.models.PolicyVote;
import starvationevasion.teamrocket.models.Region;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by scnaegl on 11/22/15.
 *
 * Represent the state of the StarvationEvasion game from one player's
 * point of view. The full state of the game is kept on the server. The
 * server sends messages of type GameState to each player when the game
 * state changes. Every player recieves a different message that reflects
 * their own view of the status of the game.
 */
public class GameState implements Serializable {
  public EnumGameState gameState; // The current state of the game as defined by the EnumGameState
  public Stopwatch stopwatch; // Current timer of the stop
  public int current_year; // Current year of game
  public int current_turn; // Current turn count in the game
  public Region[] regions; // All the region stats
  public Map<EnumRegion,CardDeck> region_decks; // All player's cards
  public Map<EnumRegion,PolicyVote[]> policy_votes; // All players votes for each card
  public ChatHistory chatHistory; // Chat history

  public GameState(EnumGameState gameState, Stopwatch stopwatch, int current_year, int current_turn, Region[] regions, Map<EnumRegion,CardDeck> region_decks, ChatHistory chatHistory) {
    this(gameState, stopwatch, current_year, current_turn, regions, region_decks, chatHistory, null);
  }

  public GameState(EnumGameState gameState, Stopwatch stopwatch, int current_year, int current_turn, Region[] regions, Map<EnumRegion,CardDeck> region_decks, ChatHistory chatHistory, Map<EnumRegion,PolicyVote[]> policy_votes) {
    this.gameState = gameState;
    this.stopwatch = stopwatch;
    this.current_year = current_year;
    this.current_turn = current_turn;
    this.regions = regions;
    this.region_decks = region_decks;
    this.chatHistory = chatHistory;
    this.policy_votes = policy_votes;
  }
}
