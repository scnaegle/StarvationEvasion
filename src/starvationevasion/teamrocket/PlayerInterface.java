package starvationevasion.teamrocket;

import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;

/**
 * Created by zfalgout on 11/14/15.
 */
public interface PlayerInterface
{

  /**
   * Needs to get the log in information
   * for the Player
   * @return log in info
   */
  public String getLogIn();

  /**
   * Gets an array of all the cards that
   * are being played.
   * @return array of Cards that are selected to play
   */
  public PolicyCard[] playSelectedCards();


  /**
   * Player votes on the given card that
   * was played by that region
   * @param card that needs voting on
   * @param cardPlayedRegion region that the card was played from
   * @return 1 for vote for, -1 for vote against, 0 for abstain
   */
  public int vote(PolicyCard card, EnumRegion cardPlayedRegion);

  /**
   * Player discards the card associated with
   * the given card number
   * @param cardPosition position in hand of card
   */
  public void discardCard(int cardPosition);

  /**
   * Adds a single card to the player's hand
   */
  public void addCard(PolicyCard card);

}
