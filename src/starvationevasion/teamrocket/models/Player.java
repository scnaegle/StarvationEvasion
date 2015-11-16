package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumRegion;

import java.net.PortUnreachableException;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Player
{

  private EnumRegion enumRegion;

  private Region region;
  private Deck deck;
  private Hand playerHand;

  /**
   * Declares a player to a specific language.
   *
   * @param enumRegion the region that the player controls
   */
  public Player(EnumRegion enumRegion, Region region)
  {
    this.region = region;
    this.enumRegion = enumRegion;
    deck = new Deck(enumRegion);
  }


  /**
   * returns the region that the player is in
   *
   * @return returns the region that the player is in
   */
  public Enum getPlayerRegionEnum()
  {
    return enumRegion;
  }
}
