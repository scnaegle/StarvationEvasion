package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumRegion;

import java.net.PortUnreachableException;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Player {

  private EnumRegion region;

  private Deck deck;
  private Hand playerHand;

  /**
   * Declares a player to a specific language.
   *
   * @param region the region that the player controls
   */
  public Player(EnumRegion region)
  {
    this.region = region;
    deck = new Deck(region);
  }


  /**
   * returns the region that the player is in
   *
   * @return returns the region that the player is in
   */
  public Enum getPlayerRegionEnum()
  {
    return region;
  }
}
