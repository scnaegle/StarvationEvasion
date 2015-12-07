package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;

/**
 * Keeps track of useful information on a player
 * associated with a single PlayerRecord for
 * the AI to use and make "smart" decisions
 */
public class PlayerRecord
{
  private boolean cooperative = true;
  private PolicyCard pendingVoteCard;
  
  /**
   * Updates the co-operability from the last turn
   * of the player this record is associated with
   * @param isCoop if this player cooperates
   */
  public void setPlayerCoop(boolean isCoop){cooperative = isCoop;}

  /**
   * Gets if this player has cooperated last turn
   * @return player's cooperativeness
   */
  public boolean isPlayerCooperative(){return cooperative;}

  /**
   * Updates this record to know the card that is needing a vote for
   * @param card that will be voted on
   */
  public void setPendingVoteCard(PolicyCard card){pendingVoteCard = card;}

  /**
   * Get the card that is being voted on for the player
   * associated with this record
   * @return card that will be voted on
   */
  public PolicyCard getPendingVoteCard(){return pendingVoteCard;}

}
