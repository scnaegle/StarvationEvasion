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
   * BASIC level of AI
   * If player has been cooperative, AI will always vote for that players card
   * if the target region isn't the AI's, but if the region is the AI's then
   * there is a 1/4 chance it will vote for, other wise it will have a 1/2
   * chance to vote against or abstain. If player wasn't cooperative
   * then there is a 1/2 chance to vote against or abstain
   * Discard will favor voting cards, 1/3 chance of discarding non vote card
   * Will try to avoid playing voting cards if possible, 1/5 chance of playing a voting card
   */
  BASIC
    {
      @Override
      public int vote(PlayerRecord record, Random generator, EnumRegion region)
      {
        if(record.isPlayerCooperative())
        {
          if(record.getPendingVoteCard().getValidTargetRegions().equals(region))
          {
            if(generator.nextInt(4) == 0) return 1;
            return generator.nextInt(2) - 1;
          }
          return 1;
        }
        return generator.nextInt(2) - 1;
      }

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

                if(card.votesRequired() > 0) votingCardPicked = true;
              }
            }
          }

          if(votingCardPicked) cardsSelected = numberCardsToPlay;
        }

        return playedCards;
      }

      @Override
      public void setCardTargets(Random generator, PolicyCard card)
      {
        EnumFood[] targetFoods = card.getValidTargetFoods();
        EnumRegion targetRegion = card.getTargetRegion();
        PolicyCard.EnumVariableUnit X = card.getRequiredVariables(PolicyCard.EnumVariable.X);
        PolicyCard.EnumVariableUnit Y = card.getRequiredVariables(PolicyCard.EnumVariable.Y);
        PolicyCard.EnumVariableUnit Z = card.getRequiredVariables(PolicyCard.EnumVariable.Z);

        if(targetFoods != null)
          card.setTargetFood(targetFoods[generator.nextInt(targetFoods.length)]);

        if(targetRegion != null) card.setTargetRegion(targetRegion);

        if(X != null)
        {
          if(X.compareTo(PolicyCard.EnumVariableUnit.MILLION_DOLLAR) == 0
                  || X.compareTo(PolicyCard.EnumVariableUnit.PERCENT) == 0)
            card.setX(generator.nextInt(10) + 1);

          else card.setX(2);
        }
        if(Y != null)
        {
          if(Y.compareTo(PolicyCard.EnumVariableUnit.MILLION_DOLLAR) == 0
                  || Y.compareTo(PolicyCard.EnumVariableUnit.PERCENT) == 0)
            card.setY(generator.nextInt(10) + 1);

          else card.setY(2);
        }
        if(Z != null)
        {
          if(Z.compareTo(PolicyCard.EnumVariableUnit.MILLION_DOLLAR) == 0
                  || Z.compareTo(PolicyCard.EnumVariableUnit.PERCENT) == 0)
            card.setZ(generator.nextInt(10) + 1);

          else card.setZ(2);
        }
      }
    };

  /**
   * How to vote for each level of the AI
   * @param record of the player to see how cooperative players are
   * @param generator random generator to choose random options
   * @param region of the AI
   * @return -1 for vote against, 0 for abstain, +1 for vote for
   */
  public abstract int vote(PlayerRecord record, Random generator, EnumRegion region);

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
   * @param card that needs targets
   */
  public abstract void setCardTargets(Random generator, PolicyCard card);
}
