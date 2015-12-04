package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;
import starvationevasion.teamrocket.main.GameController;
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
   */
  public AI(EnumRegion controlledRegion, EnumAITypes aiLevel, GameController gameController)
  {
    super(controlledRegion,aiLevel, gameController);
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

  /**
   * Removes all cards that the AI has been selected for discard
   * from the hand of the player
   * @param discardedCards the list of cards being removed
   */
  private void removeDiscardedCards(LinkedList<PolicyCard> discardedCards)
  {
    if(discardedCards.size() != 0)
    {
      getHand().remove(discardedCards.removeFirst());
      removeDiscardedCards(discardedCards);
    }
  }

  /**
   * AI has a 1 in 5 chance to discard
   * AI picks random choice from 0 to 3 cards to discard
   * @return True if AI chooses to discard cards, False if it doesn't
   */
  public boolean discardCards()
  {
    if(generator.nextInt(1) == 0)
    {
      int numCards = generator.nextInt(4);
      if(numCards == 0) numCards++;
      LinkedList<PolicyCard> discardedCards = AI.discardCards(numCards,getHand(),generator);
      removeDiscardedCards(discardedCards);
      System.out.println("AI hand size: " + getHand().size());
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
    AI.setCardTargets(cards, generator);
  }

  @Override
  public PolicyCard[] getDraftedCards()
  {
    PolicyCard[] cards = AI.selectCards(getHand(),generator);
    setCardTargets(cards);
    return cards;
  }

  @Override
  public int vote(PolicyCard card, EnumRegion cardPlayedRegion) {
    return AI.vote(records[cardPlayedRegion.ordinal()], generator);
  }

}
