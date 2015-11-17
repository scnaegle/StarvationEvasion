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
  private Deck deck;
  private Hand playerHand;
  private double wealth;
  private double happiness;

  /**
   * Declares a player to a specific language.
   *
   * @param enumRegion the region that the player controls
   */
  public Player(EnumRegion enumRegion, double wealth,
                double happiness)
  {
    this.happiness = happiness;
    this.wealth = wealth;
//    this.region = region;
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

  private void usePolicy(EnumPolicy policy)
  {
    if(policy.equals(EnumPolicy.Clean_River_Incentive))
    {
      //do something. I am not sure if we are supposed to write the methods for
      //the policies.
      //I am thinking we are.
    }
    else if(policy.equals(EnumPolicy.Covert_Intelligence))
    {

    }
    //else if() ect.,
  }



  /**
   * Buys or sells crops of a certain food type
   * @param foodType the food type being bought
   * @param buy If they are buying food
   * @param sell If they are selling food
   * @param amount The amount of items being bought or sold
   * @param cost the cost of the items being bought and sold
   */
  public void buyOrSellCropsCrops(EnumFood foodType, boolean buy, boolean sell, int amount,
                       int cost)
  {
    if (buy && wealth > 0)
    {
      if(foodType.equals(EnumFood.CITRUS))
      {

      }
      wealth -= (amount * cost);
    }
    if (sell)
    {
      wealth += (amount * cost);
    }
  }
}
