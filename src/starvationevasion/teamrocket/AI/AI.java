package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.main.GameController;
import starvationevasion.teamrocket.models.Card;
import starvationevasion.teamrocket.models.Player;

import java.util.LinkedList;
import java.util.Random;
//TODO: AI CHAT!!!!! ASAP
//TODO: needs to know the crops, and select crops for cards
//TODO: need to select target region for cards
public class AI extends Player
{
  /*Game info*/
  private final int NUM_US_REGIONS = EnumRegion.US_REGIONS.length;
  private PlayerRecord[] records;
  private Random generator;

  /**
   * Makes an AI for a region with a specific level while giving it a hand to use.
   *
   * @param controlledRegion The region the AI is supposed to control
   * @param aiLevel The difficulty level that the AI plays at
   * @param gameController The game controller that talks to the ai through
   * @param handFromServer The hand that the server deals the AI
   */
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
  public void discardCard(int discardXNumCards) {
    setHand(AI.discardCards(discardXNumCards,getHand(),generator));
  }
}
