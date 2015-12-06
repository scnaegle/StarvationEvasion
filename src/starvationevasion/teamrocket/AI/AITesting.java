package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by zfalgout on 12/2/15.
 */
public class AITesting {
  static AI ai = new AI(EnumRegion.CALIFORNIA, EnumAITypes.DUMB, null);
  static EnumPolicy[] hand = new EnumPolicy[7];

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
    hand = Arrays.asList(EnumPolicy.Clean_River_Incentive,
            EnumPolicy.Loan,
            EnumPolicy.Efficient_Irrigation_Incentive,
            EnumPolicy.Covert_Intelligence,
            EnumPolicy.Ethanol_Tax_Credit_Change,
            EnumPolicy.Fertilizer_Subsidy,
            EnumPolicy.Foreign_Aid_for_Farm_Infrastructure).toArray(hand);

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
      ai.addCard(EnumPolicy.GMO_Seed_Insect_Resistance_Research);
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

  public static void main(String[] args)
  {
    makeHand();
    discardTest();
    addTest();
    makeHand();
    voteTest();
  }
}
