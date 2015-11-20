package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.PlayerInterface;
import starvationevasion.teamrocket.main.GameController;
import starvationevasion.teamrocket.models.Card;

import java.util.Random;
//NOTE: AI communication with other players, should AI talk with other players????

public class AI implements PlayerInterface
{
  /*AI info*/
  private EnumRegion region;
  private EnumAITypes level;
  //TODO: need to handle money exchange for ai
//TODO: needs to know the crops
  /*Game info*/
  private final int NUM_US_REGIONS = EnumRegion.US_REGIONS.length;
  private PlayerRecord[] records;
  private Random generator;
  private GameController controller;

  public AI(EnumRegion controlledRegion, EnumAITypes aiLevel, GameController gameController)
  {
    controller = gameController;
    region = controlledRegion;
    level = aiLevel;
    generator = new Random();

    setup();
  }

  /**
   * Sets up the array needed to keep track of records
   * for each player.
   */
  private void setup()
  {
    records = new PlayerRecord[NUM_US_REGIONS];

    for(int i = 0; i < NUM_US_REGIONS; i++)
    {
      PlayerRecord record = new PlayerRecord(EnumRegion.US_REGIONS[i]);
      records[i] = record;
    }
  }

  @Override
  public String getLogIn() {
    return null;
  }
  @Override
  public EnumRegion getRegion(){return region;}

  @Override//TODO: needs to be done in EnumAITypes
  public Card[] playSelectedCards() {
    return new Card[0];
  }

  @Override//TODO: needs to be done in EnumAITypes
  public int vote(Card card, EnumRegion cardPlayedRegion) {
    return level.vote(records[cardPlayedRegion.ordinal()], generator);
  }

  @Override//TODO: needs to be done in EnumAITypes, return card number to discard, will need hand
  public void discardCard(int discardXNumCards) {

  }

  @Override
  public void drawCards(int numOfDraws) {

  }
}
