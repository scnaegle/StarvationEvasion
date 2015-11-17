package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.models.Card;

/**
 * Keeps track of useful information on a player
 * associated with a single PlayerRecord for
 * the AI to use and make "smart" decisions
 */
public class PlayerRecord
{
  private EnumRegion region;
  private boolean cooperative = true;
  private Card pendingVoteCard;

  /**
   * Set up the record for the player
   * associated with the given region
   * @param controllingRegion of the player associated with this record
   */
  public PlayerRecord(EnumRegion controllingRegion)
  {
    region = controllingRegion;
  }

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
  public void setPendingVoteCard(Card card){pendingVoteCard = card;}

  /**
   * Get the card that is being voted on for the player
   * associated with this record
   * @return card that will be voted on
   */
  public Card getPendingVoteCard(){return pendingVoteCard;}

}
