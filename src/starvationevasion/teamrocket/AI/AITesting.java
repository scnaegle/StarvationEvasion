package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;

/**
 * Created by zfalgout on 12/2/15.
 */
public class AITesting {
  static AI ai = new AI(EnumRegion.CALIFORNIA, EnumAITypes.DUMB, null);
  static PolicyCard[] hand = new PolicyCard[7];

  /**
   * print error message, exit 1
   * @param message error message
   */
  private static void error(String message)
  {
    System.err.println(message);
    System.exit(1);
  }

  /**
   * Make hand
   */
  private static void makeHand()
  {
    hand[0]= PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Clean_River_Incentive);
    hand[1] = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Loan);
    hand[2] = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Efficient_Irrigation_Incentive);
    hand[3] = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Covert_Intelligence);
    hand[4] = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Ethanol_Tax_Credit_Change);
    hand[5]= PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Fertilizer_Subsidy);
    hand[6] = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Foreign_Aid_for_Farm_Infrastructure);

    ai.setHand(hand);
  }

  /**
   * Discarding cards test
   */
  private static void discardTest()
  {
    int size = ai.getHandSize();
    System.out.println("Discarding All cards");
    for(int i = 0; i < size; i++)
    {
      ai.discardCard(i);
      System.out.println("Hand size: " + ai.getHandSize());
    }
    if(ai.getHandSize() != 0) error("Hand size is not 0");

    makeHand();

    if(ai.getHandSize() != 7) error("Hand size is not 7");

    if(ai.discardCards())
    {
      System.out.println("Discarding AI Choose Cards");
      System.out.println("Hand size: " + ai.getHandSize());
    }

  }

  /**
   * Adding cards test
   */
  private static void addTest()
  {
    System.out.println("Adding cards ********");
    for(int i = ai.getHandSize(); i < 7; i++)
    {
      PolicyCard card = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.GMO_Seed_Insect_Resistance_Research);
      ai.addCard(card);
    }

    System.out.println("Hand size: " + ai.getHandSize());
    if(ai.getHandSize() != 7) error("Hand size is not back at 7");
  }

  /**
   * Test voting
   */
  private static void voteTest()
  {
    System.out.println("Voting *******");

    int vote = ai.vote(hand[6], EnumRegion.HEARTLAND);

    System.out.println(vote);
  }

  /**
   * Test playing cards
   */
  private static void playCardTest()
  {
    System.out.println("Playing Cards *********");
    ai.getDraftedCards();
  }

  public static void main(String[] args)
  {
    makeHand();
    discardTest();
    addTest();
    makeHand();
    voteTest();
    playCardTest();
  }
}
