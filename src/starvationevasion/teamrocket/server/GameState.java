package starvationevasion.teamrocket.server;

import starvationevasion.common.EnumRegion;
import starvationevasion.common.Policy;
import starvationevasion.common.PolicyCard;
import starvationevasion.sim.CardDeck;
import starvationevasion.teamrocket.messages.EnumGameState;
import starvationevasion.teamrocket.models.ChatHistory;
import starvationevasion.teamrocket.models.Discard;
import starvationevasion.teamrocket.models.PolicyVote;
import starvationevasion.teamrocket.models.Region;

import java.io.Serializable;
import java.util.ArrayList;

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
  public Region[] regions; // All the region stats
  public CardDeck cardDeck; // The player's deck of cards
  public Discard[] discard_piles; // All players' discard piles
  public PolicyCard[][] drafted_policies; // All drafted policies
  public PolicyVote[][] policy_votes; // All players votes for each card
  public ChatHistory chatHistory; // Chat history

  public GameState(Region[] regions, CardDeck cardDeck, Discard[] discard_piles, PolicyCard[][] drafted_policies) {
    this(regions, cardDeck, discard_piles, drafted_policies, null);
  }

  public GameState(Region[] regions, CardDeck cardDeck, Discard[] discard_piles, PolicyCard[][] drafted_policies, PolicyVote[][] policy_votes) {
    this.regions = regions;
    this.cardDeck = cardDeck;
    this.discard_piles = discard_piles;
    this.drafted_policies = drafted_policies;
  }
}
