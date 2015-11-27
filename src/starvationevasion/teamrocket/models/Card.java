package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.PolicyCard;

/**
 * Created by Tyler on 11/14/2015.
 */
public class Card
{
  private PolicyCard policyCard;

  public Card(PolicyCard policyCard)
  {
    this.policyCard = policyCard;
  }

  /**
   * Gets if the card needs votes
   * @return true if votes needed is > 0
   */
  public boolean needsVotes()
  {
    return policyCard.votesRequired() > 0;
  }
}
