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
   * @param discardXNumCards discard x number of cards
   */
  public void discardCard(int discardXNumCards);

  /**
   * Adds a single card to the player's hand
   */
  public void addCard(Card card);

}
