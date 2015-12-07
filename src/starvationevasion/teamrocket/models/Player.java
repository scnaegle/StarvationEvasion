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
  private ServerState serverState;
  private EnumGameState gameState;
  private WorldData worldData;
  private Map<EnumRegion, RegionHistory> regionHistories;
  private Map<EnumRegion, PolicyVote[]> policyVotes;
  private ChatHistory chatHistory = new ChatHistory();
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
   * @param ai level of the player if player is an AI
   * @param controller link to GameController
   */
  public Player(EnumRegion enumRegion, EnumAITypes ai, GameController controller)
  {
    this.ENUM_REGION = enumRegion;
    AI = ai;
    this.controller = controller;
    income = 10000000;
  }

  /**
   * Creates a player using only GameController link
   * @param controller link to GameController
   */
  public Player(GameController controller) {
    this(null, null, controller);
  }

  public synchronized void setEnumRegion(EnumRegion enumRegion) {
    this.ENUM_REGION = enumRegion;
  }

  /**
   * Get region of player
   * @return EnumRegion of player
   */
  public EnumRegion getEnumRegion() {
    return ENUM_REGION;
  }

  /**
   * Gets the EnumAITypes of the player if
   * it is an AI
   * @return EnumAITypes if player is AI, otherwise null for human player
   */
  @Override
  public EnumAITypes getAIType() {
    return AI;
  }

  /**
   * Gets the hand of the player as array of EnumPolicies
   * @return EnumPolicy[] of player's hand
   */
  public EnumPolicy[] getHand(){return hand;}

  /**
   * Get the hand of the player, but as an array of PolicyCards
   * @return PolicyCard[] of player's hand
   */
  public PolicyCard[] getHandCards() {
    return Stream.of(hand).map(c -> PolicyCard.create(ENUM_REGION, c)).toArray(size -> new PolicyCard[size]);
  }

  @Override
  public PolicyCard getCard(int card_index) {
    return PolicyCard.create(ENUM_REGION, hand[card_index]);
  }

  /**
   * Update the player's hand with the new hand
   * @param hand of new cards
   */
  public synchronized void setHand(EnumPolicy[] hand){this.hand = hand;}

  /**
   * Get the current income of the player
   * @return player's income
   */
  public int getIncome(){return income;}

  /**
   * Updates the player's income to the new income
   * @param income new income of player
   */
  public void updateIncome(int income){this.income = income;}

  /**
   * Set the selected cards from the GUI so the player knows
   * which cards were selected
   * If there is no card position, then -1 should be passed for
   * that card position
   * @param card1 position in hand of first card
   * @param card2 position in hand of second card
   */
  public void selectedCards(int card1, int card2)
  {
    selectedCards.clear();
    selectCard(card1);
    selectCard(card2);
  }

  private void selectCard(int card_index) {
    try {
      if (!selectedCards.contains(hand[card_index])) {
        PolicyCard card = PolicyCard.create(ENUM_REGION, hand[card_index]);
        selectedCards.add(card);
      }
    } catch (IndexOutOfBoundsException e) {
      // do nothing
    }
  }

  /**
   * count nulls in array
   * @return num of nulls
   */
  private  int countNulls()
  {
    int nullCount = 0;

    for(int i = 0; i < hand.length; i++)
    {
      if(hand[i] == null) nullCount++;
    }
    return nullCount;
  }

  /**
   * Gets the size of the hand
   * Needs to count number of nulls to see
   * how many cards have still in hand
   * @return number of cards left in hand
   */
  public int getHandSize()
  {
    return hand.length - countNulls();
  }

  /********Interface Methods ***********/
  @Override
  public String getLogIn() {
    return logIn;
  }

  @Override
  public PolicyCard[] getDraftedCards() {
    return selectedCards.toArray(new PolicyCard[selectedCards.size()]);
  }

  @Override
  public int vote(EnumPolicy card, EnumRegion cardPlayedRegion) {
    return 0;
  }

  @Override
  public void discardCard(int cardPosition)
  {
    hand[cardPosition] = null;
  }

  @Override
  public void addCard(EnumPolicy card)
  {
    for(int i = 0; i < hand.length; i++)
    {
      if(hand[i] == null)
      {
        hand[i] = card;
        break;
      }
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
   * @return
   */
  public Map<EnumRegion, RegionHistory> getRegionHistories() {
    return regionHistories;
  }

  /**
   * Returns an array of the region histories as apposed to the default
   * hashmap being stored.
   * @return
   */
  public RegionHistory[] getRegionHistoriesArray() {
    RegionHistory[] regionHistoriesArray = new RegionHistory[EnumRegion.SIZE];
    for(Map.Entry<EnumRegion, RegionHistory> entry : regionHistories.entrySet()) {
      regionHistoriesArray[entry.getKey().ordinal()] = entry.getValue();
    }
    return regionHistoriesArray;
  }

  @Override
  synchronized public void setGameState(EnumGameState gameState) {
    this.gameState = gameState;
  }

  @Override
  synchronized public void updateVoteStatus(VoteStatus voteStatus) {
    this.voteStatus = voteStatus;
  }

  @Override
  synchronized public void receiveChatMessage(ServerChatMessage message)
  {
    chatHistory.addMessage(message);
  }

  @Override
  synchronized public ClientChatMessage sendChatMessage()
  {
    return null;
  }

  @Override
  public ChatHistory getChatHistory()
  {
    return chatHistory;
  }
}
