package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;

import java.util.Random;

/**
 * Different Level types of AI that
 * the AI class can take in
 */
public enum EnumAITypes
{
  /**
   * DUMB level of AI
   * Randomly chooses a vote
   * Discard will favor voting cards, 1/3 chance of discarding non vote card
   * Will try to avoid playing voting cards if possible, 1/5 chance of playing a voting card
   */
  DUMB
    {
      @Override
      public int vote(PlayerRecord record, Random generator)
      {
        return generator.nextInt(3) - 1;
      } //Java Optionals

      @Override
      public int[] discardCards(int discardXNumCards, PolicyCard[] hand, Random generator)
      {
        int discardedCardCount = 0;
        int[] discardCardPosition = new int[discardXNumCards];
        boolean[] cardDiscarded = new boolean[hand.length];

        for(int i = 0; i < discardCardPosition.length; i++)
        {
          discardCardPosition[i] = -1;
        }

       while(discardedCardCount < discardXNumCards)
       {
         int index = 0;

         for(PolicyCard card : hand)
         {
           if(!(cardDiscarded[index]) && discardedCardCount < discardXNumCards)
           {
             if(card.votesRequired() > 0 || generator.nextInt(3) == 0)
             {
               discardCardPosition[discardedCardCount] = index;
               cardDiscarded[index] = true;
               discardedCardCount++;
             }
           }
           index++;
         }
       }
        return discardCardPosition;
      }

      @Override
      public PolicyCard[] selectCards(PolicyCard[] hand, Random generator, int numberCardsToPlay) {
        int cardsSelected = 0;
        boolean votingCardPicked = false;
        PolicyCard[] playedCards = new PolicyCard[numberCardsToPlay];

        while(cardsSelected < numberCardsToPlay)
        {
          for(PolicyCard card : hand)
          {
            if(cardsSelected < numberCardsToPlay)
            {
              if(card.votesRequired() < 1 || (generator.nextInt(5) == 0 && !votingCardPicked))
              {
                playedCards[cardsSelected] = card;
                cardsSelected++;
                System.out.println(card.getPolicyName());

                if(card.votesRequired() > 0) votingCardPicked = true;
              }
            }
          }

          if(votingCardPicked) cardsSelected = numberCardsToPlay;
        }

        return playedCards;
      }

      @Override
      public String[] setCardTargets(Random generator, PolicyCard card)
      {
        String[] targets = new String[5];
        EnumFood[] targetFoods = card.getValidTargetFoods();
        EnumRegion targetRegion = card.getTargetRegion();
        PolicyCard.EnumVariableUnit X = card.getRequiredVariables(PolicyCard.EnumVariable.X);
        PolicyCard.EnumVariableUnit Y = card.getRequiredVariables(PolicyCard.EnumVariable.Y);
        PolicyCard.EnumVariableUnit Z = card.getRequiredVariables(PolicyCard.EnumVariable.Z);

        if(targetFoods != null)
          targets[0] = targetFoods[generator.nextInt(targetFoods.length)].toString();

        if(targetRegion != null) targets[1] = targetRegion.toString();

        if(X != null) targets[2] = X.toString();

        if(Y != null) targets[3] = Y.toString();

        if(Z != null) targets[4] = Z.toString();

        return targets;
      }
    },

  /**
   * Average level of AI
   * If player is cooperative, then 2/3 chance of AI to cooperate
   * or 1/3 chance of AI to abstain, otherwise it will not cooperate
   * Equal chance of playing voting or non voting cards
   * Equal chance of discarding voting or non voting cards
   */
  AVERAGE
    {
      @Override
      public int vote(PlayerRecord record, Random generator)
      {
        if(record.isPlayerCooperative())
        {
          if(generator.nextInt(3) != 2) return 1;
          else return 0;
        }
        return -1;
      }

      @Override//TODO: build up average
      public int[] discardCards(int discardXNumCards, PolicyCard[] hand, Random generator)
      {
        return null;
      }

      @Override
      public PolicyCard[] selectCards(PolicyCard[] hand, Random generator, int numberCardsToPlay) {
        return new PolicyCard[0];
      }

      @Override
      public String[] setCardTargets(Random generator, PolicyCard card) {
        return new String[0];
      }
    },

  /**
   * Smart level of AI
   * If player is cooperative, and pending vote card benefits AI's region
   * then AI will cooperate, else if pending vote card doesn't benefit region
   * then AI has 2/3 chance of cooperating, other wise AI will not cooperate
   * with player that isn't cooperative
   */
  SMART
    {
      @Override
      public int vote(PlayerRecord record, Random generator)
      {
        return -1;
      }

      @Override//TODO: build up smart
      public int[] discardCards(int discardXNumCards, PolicyCard[] hand, Random generator)
      {
        return null;
      }

      @Override
      public PolicyCard[] selectCards(PolicyCard[] hand, Random generator, int numberCardsToPlay) {
        return new PolicyCard[0];
      }

      @Override
      public String[] setCardTargets(Random generator, PolicyCard card) {
        return new String[0];
      }
    };

  /**
   * How to vote for each level of the AI
   * @param record of the player to see how cooperative players are
   * @param generator random generator to choose random options
   * @return -1 for vote against, 0 for abstain, +1 for vote for
   */
  public abstract int vote(PlayerRecord record, Random generator);

  /**
   * Discards X number of cards and gets new hand
   * Corresponds to ai level's decision of discarding
   * @param discardXNumCards discard x number of cards
   * @param hand card hand of the ai
   * @param generator random generator to choose random options
   * @return array containing positions of the cards being discarded
   */
  public abstract int[] discardCards(int discardXNumCards, PolicyCard[] hand, Random generator);

  /**
   * AI selects up to 2 cards and returns them
   * @param hand PolicyCard hand
   * @param generator random generator to choose randomly
   * @param numberCardsToPlay how many cards to select
   * @return card array of selected cards
   */
  public abstract  PolicyCard[] selectCards(PolicyCard[] hand, Random generator, int numberCardsToPlay);

  /**
   * If played cards need a target selected, AI will select
   * what the targets are
   * @param card
   */
  public abstract String[] setCardTargets(Random generator, PolicyCard card);
}
