package starvationevasion.teamrocket.AI;

import starvationevasion.teamrocket.models.Card;

import java.util.Random;

/**
 * Different Level types of AI that
 * the AI class can take in
 */
public enum EnumAITypes
{
  DUMB
    {
      @Override
      public int vote(PlayerRecord record, Random generator)
      {
        return generator.nextInt(3);
      }
    },
  AVERAGE
    {
      @Override
      public int vote(PlayerRecord record, Random generator)
      {
        return 0;
      }
    },
  SMART
    {
      @Override
      public int vote(PlayerRecord record, Random generator)
      {
        return 0;
      }
    };


  public abstract int vote(PlayerRecord record, Random generator);
  //TODO: how to vote
  //TODO: how to play cards
  //TODO: how to discard cards
}
