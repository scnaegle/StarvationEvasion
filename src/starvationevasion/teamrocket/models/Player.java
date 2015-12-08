package starvationevasion.teamrocket.models;

import starvationevasion.common.*;
import starvationevasion.common.messages.ClientChatMessage;
import starvationevasion.common.messages.ServerChatMessage;
import starvationevasion.common.messages.VoteStatus;
import starvationevasion.server.ServerState;
import starvationevasion.teamrocket.AI.EnumAITypes;
import starvationevasion.teamrocket.PlayerInterface;
import starvationevasion.teamrocket.main.GameController;
import starvationevasion.teamrocket.messages.EnumGameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Player keeps track of the state of the game
 * as the game is being played. Player also stores
 * information for human, and AI that they need
 * to know about.
 */
public class Player implements PlayerInterface
{

  /**
   * Client's game state
   */
  private EnumGameState gameState;

  /**
   * World data information from the simulation
   */
  private WorldData worldData;

  /**
   * All history for each turn and each region
   */
  private Map<EnumRegion, RegionHistory> regionHistories = new HashMap<>();

  /**
   * Chat messaging history for player
   */
  private ChatHistory chatHistory = new ChatHistory();

  /**
   * The current voting status
   */
  private VoteStatus voteStatus;

  /**
   * Player's selected region.
   */
  public EnumRegion ENUM_REGION;

  /**
   * AI Level if AI is used
   * If Player is a human, this should be null
   */
  public final EnumAITypes AI;

  /**
   * Controller to add data to once
   * Player has made a decision
   */
  private GameController controller;

  /**
   * Card hand of the player
   */
  private EnumPolicy[] hand;

  private ArrayList<PolicyCard> selectedCards = new ArrayList<>();

  /**
   * Log in info for player
   */
  private String logIn;

  /**
   * Income of the player
   */
  private int income;

  /**
   * Creates a new player based on selected region.
   *
   * @param enumRegion the region that the player controls all stats are determined by this.
   * @param ai         level of the player if player is an AI
   * @param controller link to GameController
   */
  public Player(EnumRegion enumRegion, EnumAITypes ai, GameController controller)
  {
    this.ENUM_REGION = enumRegion;
    AI = ai;
    this.controller = controller;
    income = 10000000;

    // Initialize our region histories
    for (EnumRegion region : EnumRegion.values())
    {
      regionHistories.put(region, new RegionHistory(region));
    }
  }

  /**
   * Creates a player using only GameController link
   *
   * @param controller link to GameController
   */
  public Player(GameController controller)
  {
    this(null, null, controller);
  }

  public synchronized void setEnumRegion(EnumRegion enumRegion)
  {
    this.ENUM_REGION = enumRegion;
  }

  /**
   * Get region of player
   *
   * @return EnumRegion of player
   */
  public EnumRegion getEnumRegion()
  {
    return ENUM_REGION;
  }

  /**
   * Gets the EnumAITypes of the player if
   * it is an AI
   *
   * @return EnumAITypes if player is AI, otherwise null for human player
   */
  @Override
  public EnumAITypes getAIType()
  {
    return AI;
  }

  /**
   * Gets the hand of the player as array of EnumPolicies
   *
   * @return EnumPolicy[] of player's hand
   */
  public EnumPolicy[] getHand()
  {
    return hand;
  }

  /**
   * Get the hand of the player, but as an array of PolicyCards
   *
   * @return PolicyCard[] of player's hand
   */
  public PolicyCard[] getHandCards()
  {
    return Stream.of(hand).map(c -> PolicyCard.create(ENUM_REGION, c)).toArray(size -> new PolicyCard[size]);
  }

  @Override
  public PolicyCard getCard(int cardIndex)
  {
    if (hand != null && cardIndex < hand.length && hand[cardIndex] != null)
    {
      return PolicyCard.create(ENUM_REGION, hand[cardIndex]);
    }
    else
    {
      return null;
    }
  }

  /**
   * Update the player's hand with the new hand
   *
   * @param hand of new cards
   */
  public synchronized void setHand(EnumPolicy[] hand)
  {
    if (hand != null && hand.length > 0)
    {
      this.hand = hand;
    }
  }

  /**
   * Get the current income of the player
   *
   * @return player's income
   */
  public int getIncome()
  {
    return income;
  }

  /**
   * Updates the player's income to the new income
   *
   * @param income new income of player
   */
  public void updateIncome(int income)
  {
    this.income = income;
  }

  /********
   * Interface Methods
   ***********/
  @Override
  public String getLogIn()
  {
    return logIn;
  }

  @Override
  public int vote(EnumPolicy card, EnumRegion cardPlayedRegion)
  {
    return 0;
  }

  @Override
  public void discardCard(int cardPosition)
  {
    //hand[cardPosition] = null;
  }

  synchronized public void setGameState(ServerState serverState)
  {
    /*
    State of the server
   */
    switch (serverState)
    {
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

  /**
   * returns the games state, or phase of the game.
   *
   * @return the phase of the state
   */
  public EnumGameState getGameState()
  {
    return gameState;
  }

  @Override
  synchronized public void updateWorldData(WorldData worldData)
  {
    this.worldData = worldData;
    for (EnumRegion enumRegion : EnumRegion.values())
    {
      RegionHistory regionHistory = regionHistories.get(enumRegion);
      RegionData regionData = worldData.regionData[enumRegion.ordinal()];
      regionHistory.addTotalRevenue(regionData.revenueBalance);
      regionHistory.addPopulation(regionData.population);
      regionHistory.addUndernourished(regionData.undernourished);
      regionHistory.addHDI(regionData.humanDevelopmentIndex);
      for (EnumFood food : EnumFood.values())
      {
        regionHistory.addPricePerTon(food, worldData.foodPrice[food.ordinal()]);
        regionHistory.addCropProduced(food, regionData.foodProduced[food.ordinal()]);
        regionHistory.addCropRevenue(food, regionData.foodIncome[food.ordinal()]);
        regionHistory.addFoodExported(food, regionData.foodExported[food.ordinal()]);
        regionHistory.addFarmArea(food, regionData.farmArea[food.ordinal()]);
      }
    }
  }

  /**
   * Returns a Map of all the region histories.
   *
   * @return the history of a region
   */
  public Map<EnumRegion, RegionHistory> getRegionHistories()
  {
    return regionHistories;
  }

  /**
   * Returns an array of the region histories as apposed to the default
   * hashmap being stored.
   *
   * @return Array of history methods
   */
  public RegionHistory[] getRegionHistoriesArray()
  {
    RegionHistory[] regionHistoriesArray = new RegionHistory[EnumRegion.SIZE];
    for (Map.Entry<EnumRegion, RegionHistory> entry : regionHistories.entrySet())
    {
      regionHistoriesArray[entry.getKey().ordinal()] = entry.getValue();
    }
    return regionHistoriesArray;
  }

  /**
   * Sets the gamestate that the game is in
   *
   * @param gameState game state that we are passing in
   */
  @Override
  synchronized public void setGameState(EnumGameState gameState)
  {
    this.gameState = gameState;
  }

  /**
   * Updates the vote status of the player
   *
   * @param voteStatus of each player on each card
   */
  @Override
  synchronized public void updateVoteStatus(VoteStatus voteStatus)
  {
    this.voteStatus = voteStatus;
  }

  /**
   * recieve message from the server
   *
   * @param message from other player from server
   */
  @Override
  synchronized public void receiveChatMessage(ServerChatMessage message)
  {
    chatHistory.addMessage(message);
  }

  /**
   * Sending chat message
   *
   * @return Client chat message
   */
  @Override
  synchronized public ClientChatMessage sendChatMessage()
  {
    return null;
  }

  /**
   * Gets the chat history
   *
   * @return Gets the chat history
   */
  @Override
  public ChatHistory getChatHistory()
  {
    return chatHistory;
  }

  /**
   * gets the vote status
   *
   * @return the vote status of a player
   */
  @Override
  public VoteStatus getVoteStatus()
  {
    return voteStatus;
  }

  @Override
  public PolicyCard[] getDraftedCards() {
    return selectedCards.toArray(new PolicyCard[selectedCards.size()]);
  }
}
