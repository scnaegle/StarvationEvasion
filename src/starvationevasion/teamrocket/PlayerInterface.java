package starvationevasion.teamrocket;

import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.models.Card;

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
   * Gets the player's region they are controlling
   * @return US region controlled by player
   */
  public EnumRegion getRegion();

  /**
   * Gets an array of all the cards that
   * are being played.
   * @return array of Cards that are selected to play
   */
  public Card[] playSelectedCards();


  /**
   * Player votes on the given card that
   * was played by that region
   * @param card that needs voting on
   * @param cardPlayedRegion region that the card was played from
   * @return 1 for vote for, -1 for vote against, 0 for abstain
   */
  public int vote(Card card, EnumRegion cardPlayedRegion);

  /**
   * Player discards the card associated with
   * the given card number
   * @param cardNum number of the card
   */
  public void discardCard(int cardNum);

  /**
   * Player draws up to the number of draws
   * allowed
   * @param numOfDraws max number of cards player can draw
   */
  public void drawCards(int numOfDraws);

}
