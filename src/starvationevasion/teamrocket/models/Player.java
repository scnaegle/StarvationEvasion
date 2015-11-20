package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumRegion;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Player
{

  /**
   * Player's selected region.
   */
  public final EnumRegion ENUM_REGION;

  /**
   * Creates a new player based on selected region.
   *
   * @param enumRegion the region that the player controls all stats are determined by this.
   */
  public Player(EnumRegion enumRegion)
  {
    this.ENUM_REGION = enumRegion;
  }
}
