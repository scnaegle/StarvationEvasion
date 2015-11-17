package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.PlayerInterface;
import starvationevasion.teamrocket.models.Card;

import java.util.Random;
//NOTE: AI communication with other players, should AI talk with other players????

public class AI implements PlayerInterface
{
  private EnumRegion region;
  private EnumAITypes level;
  private final int NUM_US_REGIONS = EnumRegion.US_REGIONS.length;
  private PlayerRecord[] records;
  private Random generator;

  public AI(EnumRegion controlledRegion, EnumAITypes aiLevel)
  {
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

  @Override//TODO: needs to be done in EnumAITypes
  public void discardCard(int cardNum) {

  }

  @Override
  public void drawCards(int numOfDraws) {

  }

  @Override
  public void shuffleDeck() {

  }
}
