package starvationevasion.teamrocket.AI;

import starvationevasion.teamrocket.models.Card;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
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
   * Will try to avoid playing voting cards if possible
   */
  DUMB
    {
      @Override
      public int vote(PlayerRecord record, Random generator)
      {
        return generator.nextInt(3) - 1;
      }

      @Override
      public LinkedList<Card> discardCards(int discardXNumCards, LinkedList<Card> hand, Random generator)
      {
        int discardedCardCount = 0;
        LinkedList<Card> modifiedHand = new LinkedList<>();

        for(Card card : hand)
        {
          if(card.needsVotes() && discardedCardCount < discardXNumCards)
          {
            discardedCardCount++;
          }
          else if(generator.nextInt(3) == 0 && discardedCardCount < discardXNumCards) discardedCardCount++;
          else modifiedHand.add(card);
        }
        return modifiedHand;
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

      @Override
      public LinkedList<Card> discardCards(int discardXNumCards, LinkedList<Card> hand, Random generator) {

        return hand;
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

      @Override
      public LinkedList<Card> discardCards(int discardXNumCards, LinkedList<Card> hand, Random generator) {

        return hand;
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
   * @return new modified card hand
   */
  public abstract LinkedList<Card> discardCards(int discardXNumCards, LinkedList<Card> hand, Random generator);
  //TODO: how to play cards
  //TODO: how to discard cards
}
