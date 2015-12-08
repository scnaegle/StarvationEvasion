package starvationevasion.teamrocket;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;
import starvationevasion.common.WorldData;
import starvationevasion.common.messages.ClientChatMessage;
import starvationevasion.common.messages.ServerChatMessage;
import starvationevasion.common.messages.VoteStatus;
import starvationevasion.server.ServerState;
import starvationevasion.teamrocket.AI.EnumAITypes;
import starvationevasion.teamrocket.messages.EnumGameState;
import starvationevasion.teamrocket.models.ChatHistory;

/**
 * Methods that define what to call for a Player
 */
public interface PlayerInterface
{
  /**
   * Set the player's region
   */
  public void setEnumRegion(EnumRegion enumRegion);

  /**
   * Player's selected region.
   */
  public EnumRegion getEnumRegion();

  /**
   * AI Level if AI is used
   * If Player is a human, this should be null
   */
  public EnumAITypes getAIType();

  /**
   * Needs to get the log in information
   * for the Player
   * @return log in info
   */
  public String getLogIn();

  /**
   * Gets an array of all the cards that
   * are being played.
   * @return array of Cards that are selected to play
   */
  public PolicyCard[] getDraftedCards();


  /**
   * Player votes on the given card that
   * was played by that region
   * @param card that needs voting on
   * @param cardPlayedRegion region that the card was played from
   * @return 1 for vote for, -1 for vote against, 0 for abstain
   */
  public int vote(EnumPolicy card, EnumRegion cardPlayedRegion);

  /**
   * Player discards the card associated with
   * the given card number
   * @param cardPosition position in hand of card
   */
  public void discardCard(int cardPosition);

  /**
   * Adds a single card to the player's hand
   */
  public void addCard(EnumPolicy card);

  /**
   * Sets the hand of the player
   * @param hand of the player
   */
  public void setHand(EnumPolicy[] hand);

  /**
   * Returns the player's hand.
   * @return array of EnumPolicies.
   */
  public EnumPolicy[] getHand();

  /**
   * Gets the card at the specific index
   * @param card_index index of card being asked for
   * @return PolicyCard for the card at that index in the hand
   */
  public PolicyCard getCard(int card_index);

  /**
   * Set the selected cards from the GUI so the player knows
   * which cards were selected
   * If there is no card position, then -1 should be passed for
   * that card position
   * @param card1 position in hand of first card
   * @param card2 position in hand of second card
   */
  public void selectedCards(int card1, int card2);


  public void setGameState(ServerState serverState);

  public void setGameState(EnumGameState gameState);

  public void updateWorldData(WorldData worldData);

  /**
   * Update the votes all the players did for each card
   * @param voteStatus of each player on each card
   */
  public void updateVoteStatus(VoteStatus voteStatus);

  /**
   * Get chat message from the server
   * @param message from other player from server
   */
  public void receiveChatMessage(ServerChatMessage message);

  /**
   * Get the chat message from the player
   * @return message
   */
  public ClientChatMessage sendChatMessage();

  ChatHistory getChatHistory();
}
