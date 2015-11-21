package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.main.GameController;
import starvationevasion.teamrocket.models.Card;
import starvationevasion.teamrocket.models.Player;

import java.util.LinkedList;
import java.util.Random;
//NOTE: AI communication with other players, should AI talk with other players????

public class AI extends Player
{
//TODO: needs to know the crops
  /*Game info*/
  private final int NUM_US_REGIONS = EnumRegion.US_REGIONS.length;
  private PlayerRecord[] records;
  private Random generator;

  public AI(EnumRegion controlledRegion, EnumAITypes aiLevel, GameController gameController,
            LinkedList<Card> handFromServer)
  {
    super(controlledRegion,aiLevel, gameController, handFromServer);

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
  public Card[] playSelectedCards() {
    return AI.selectCards(getHand(),generator);
  }

  @Override
  public int vote(Card card, EnumRegion cardPlayedRegion) {
    return AI.vote(records[cardPlayedRegion.ordinal()], generator);
  }

  @Override
  public void discardCard(int discardXNumCards)
  {
    setHand(AI.discardCards(discardXNumCards,getHand(),generator));
  }
}
