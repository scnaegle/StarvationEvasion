package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;
import starvationevasion.teamrocket.main.GameController;
import starvationevasion.teamrocket.models.Player;

import java.util.Random;

//TODO: AI CHAT!!!!! ASAP
//TODO: Need to update Player Records, need to take in Array of Policy cards and check if regions voted yes
public class AI extends Player
{
  /*Game info*/
  private final int NUM_US_REGIONS = EnumRegion.US_REGIONS.length;
  private PlayerRecord[] records;
  private Random generator;
  private int actionsPerformed = 2; //decrease when actions are done during drafting phase

  /**
   * Makes an AI for a region with a specific level while giving it a hand to use.
   *
   * @param controlledRegion The region the AI is supposed to control
   * @param aiLevel The difficulty level that the AI plays at
   * @param gameController The game controller that talks to the ai through
   */
  public AI(EnumRegion controlledRegion, EnumAITypes aiLevel, GameController gameController)
  {
    super(controlledRegion, aiLevel, gameController);
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
      PlayerRecord record = new PlayerRecord();
      records[i] = record;
    }
  }

  /**
   * Removes all cards that the AI has been selected for discard
   * from the hand of the player
   * @param discardCardsPosition the position of cards being discarded
   */
  private void removeDiscardedCards(int[] discardCardsPosition) {
    EnumPolicy[] hand = getHand();
    for (int i : discardCardsPosition)
    {
      hand[i] = null;
    }
    setHand(hand);

  }

  /**
   * AI has a 1 in 5 chance to discard
   * AI picks random choice from 0 to 3 cards to discard
   * @return True if AI chooses to discard cards, False if it doesn't
   */
  public boolean discardCards()
  {
    if(generator.nextInt(5) == 0 && actionsPerformed > 0)
    {
      actionsPerformed--;
      int numCards = generator.nextInt(4);
      if(numCards == 0) numCards++;
      removeDiscardedCards(AI.discardCards(numCards,getHandCards(),generator));
      return true;
    }
    return false;
  }

  /**
   * Sets the targets of cards that are going to be played
   * if those cards need a target selected
   * @param cards array of cards being played
   */
  private void setCardTargets(PolicyCard[] cards)
  {
    for(PolicyCard card : cards)
    {
      AI.setCardTargets(generator, card);
    }
  }

  @Override
  public PolicyCard[] getDraftedCards()
  {
    if(actionsPerformed > 0)
    {
      PolicyCard[] cards = AI.selectCards(getHandCards(),generator, actionsPerformed);
      setCardTargets(cards);
      actionsPerformed = 2;
      return cards;
    }
    return null;
  }

  @Override
  public int vote(EnumPolicy card, EnumRegion cardPlayedRegion) {
    records[cardPlayedRegion.ordinal()].setPendingVoteCard(PolicyCard.create(cardPlayedRegion,card));
    return AI.vote(records[cardPlayedRegion.ordinal()], generator, ENUM_REGION);
  }
}
