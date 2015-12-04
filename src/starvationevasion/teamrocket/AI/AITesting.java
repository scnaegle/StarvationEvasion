package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;

import java.util.LinkedList;

/**
 * Created by zfalgout on 12/2/15.
 */
public class AITesting {
  static AI ai = new AI(EnumRegion.CALIFORNIA, EnumAITypes.DUMB, null);
  static LinkedList<PolicyCard> hand = new LinkedList<>();

  private static void error(String message)
  {
    System.err.println(message);
    System.exit(1);
  }

  private static void makeHand()
  {
    PolicyCard card1 = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Clean_River_Incentive);
    PolicyCard card2 = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Loan);
    PolicyCard card3 = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Efficient_Irrigation_Incentive);
    PolicyCard card4 = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Covert_Intelligence);
    PolicyCard card5 = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Ethanol_Tax_Credit_Change);
    PolicyCard card6 = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Fertilizer_Subsidy);
    PolicyCard card7 = PolicyCard.create(ai.ENUM_REGION, EnumPolicy.Foreign_Aid_for_Farm_Infrastructure);

    hand.add(card1);
    hand.add(card2);
    hand.add(card3);
    hand.add(card4);
    hand.add(card5);
    hand.add(card6);
    hand.add(card7);

    ai.setHand(hand);
  }

  private static void discardTest()
  {
    int size = hand.size();
    for(int i = 0; i < size; i++)
    {
      ai.discardCard(0);
      System.out.println("Hand size: " + ai.getHand().size());
    }
    if(hand.size() != 0) error("Hand size is not 0");

    makeHand();

    if(hand.size() != 7) error("Hand size is not 7");

    if(ai.discardCards())
    {
      System.out.println("Discarding Cards");
      System.out.println("Hand size: " + ai.getHand().size());
    }

  }

  public static void main(String[] args)
  {
    makeHand();
    discardTest();
  }
}
