package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumPolicy;

/**
 * Created by Tyler on 11/14/2015.
 */
public class Card
{
  private EnumPolicy policy;

  public Card(EnumPolicy policy)
  {
    this.policy = policy;
  }

  /**
   * Gets if the card needs votes
   * @return true if votes needed is > 0
   */
  public boolean needsVotes()
  {
    return policy.votesRequired() > 0;
  }
}
