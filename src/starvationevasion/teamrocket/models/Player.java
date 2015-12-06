package starvationevasion.teamrocket.models;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import starvationevasion.common.*;
import starvationevasion.server.ServerState;
import starvationevasion.teamrocket.AI.EnumAITypes;
import starvationevasion.teamrocket.PlayerInterface;
import starvationevasion.teamrocket.main.GameController;
import starvationevasion.teamrocket.messages.EnumGameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;


public class Player implements PlayerInterface
{
  private ServerState serverState;
  private EnumGameState gameState;
  private WorldData worldData;
  private Map<EnumRegion, RegionHistory> regionHistories;
  private Map<EnumRegion, PolicyVote[]> policyVotes;
  private ChatHistory chatHistory;


  /**
   * Player's selected region.
   */
  public final EnumRegion ENUM_REGION;

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
  private long income;

  /**
   * Creates a new player based on selected region.
   *
   * @param enumRegion the region that the player controls all stats are determined by this.
   */
  public Player(EnumRegion enumRegion, EnumAITypes ai, GameController controller)
  {
    this.ENUM_REGION = enumRegion;
    AI = ai;
    this.controller = controller;
    income = 10000000;
  }

  public EnumRegion getEnumRegion() {
    return ENUM_REGION;
  }

  public EnumAITypes getAIType() {
    return AI;
  }
  /**
   * Gets the hand of the player
   * @return player hand
   */
  public EnumPolicy[] getHand(){return hand;}

  public PolicyCard[] getHandCards() {
    return Stream.of(hand).map(c -> PolicyCard.create(ENUM_REGION, c)).toArray(size -> new PolicyCard[size]);
  }

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
  public long getIncome(){return income;}

  /**
   * Updates the player's income to the new income
   * @param income new income of player
   */
  public void updateIncome(long income){this.income = income;}

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
//    int selectionSize = 2;

//    if(card1 < 0 || card2 < 0) selectionSize = 1;
//    else if(card1 < 0 && card2 < 0) selectionSize = 0;

//    selectedCards = new EnumPolicy[selectionSize];

//    if(selectionSize > 0)
//    {
//      if(card1 < 0) selectedCards[0] = hand[card2];
//      else selectedCards[0] = hand[card1];
//    }
//    if(selectionSize > 1) selectedCards[1] = hand[card2];

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


  synchronized public void setGameState(EnumGameState gameState) {
    this.gameState = gameState;
  }
}
