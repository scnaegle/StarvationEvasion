package starvationevasion.teamrocket.AI;

import starvationevasion.teamrocket.models.Card;

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
   * Discard will favor voting cards
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
      public void discardCards(int discardXNumCards, LinkedList<Card> hand, Random generator)
      {
        int discardedCardCount = 0;

        for(Card card : hand)
        {
          if(card.needsVotes() && discardedCardCount < discardXNumCards)
          {
            hand.remove(card);
            discardedCardCount++;
          }
        }
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
      public void discardCards(int discardXNumCards, LinkedList<Card> hand, Random generator) {

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
      public void discardCards(int discardXNumCards, LinkedList<Card> hand, Random generator) {

      }
    };


  public abstract int vote(PlayerRecord record, Random generator);
  public abstract void discardCards(int discardXNumCards, LinkedList<Card> hand, Random generator);
  //TODO: how to vote
  //TODO: how to play cards
  //TODO: how to discard cards
}
