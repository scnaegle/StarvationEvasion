package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;

import java.net.PortUnreachableException;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Player
{

  private EnumRegion enumRegion;

  private Region region;
//  private Deck deck;

  /**
   * Creates a new player based on selected region.
   *
   * @param enumRegion the region that the player controls all stats are determined by this.
   */
  public Player(EnumRegion enumRegion)
  {
    this.region = new Region(enumRegion);
//    deck = new Deck(this);
  }

  public Region getRegion()
  {
    return region;
  }


  /**
   * returns the region that the player is in
   * @return returns the region that the player is in
   */
  public EnumRegion getPlayerRegionEnum()
  {
    return region.ENUM_REGION;
  }
}
