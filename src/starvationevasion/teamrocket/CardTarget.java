package starvationevasion.teamrocket;

import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumRegion;

/**
 * CardTarget stores the target information of a single card
 * This class is to make it easier to grab target information
 * for cards that are being played without having to have all
 * this information stored inside the player class.
 *
 * If X, Y, or Z aren't used, -1 will be returned
 * If region or food aren't used, null will be returned
 *
 * @author zfalgout
 */
public class CardTarget
{
  private int X = -1;
  private int Y = -1;
  private int Z = -1;
  private EnumRegion region = null;
  private EnumFood food = null;

  /**
   * Set the X target of the associated card
   * @param x target
   */
  public void setX(int x)
  {
    X = x;
  }

  /**
   * Get the X target of the associated card
   * @return X target, or -1 if not used
   */
  public int getX()
  {
    return X;
  }

  /**
   * Set the Y target of the associated card
   * @param y target
   */
  public void setY(int y) {
    Y = y;
  }

  /**
   * Get the Y target of the associated card
   * @return Y target, or -1 if not used
   */
  public int getY()
  {
    return Y;
  }

  /**
   * Set the Z target of the associated card
   * @param z target
   */
  public void setZ(int z)
  {
    Z = z;
  }

  /**
   * Get the Z target of the associated card
   * @return Z target, or -1 if not used
   */
  public int getZ()
  {
    return Z;
  }

  /**
   * Set the region target of the associated card
   * @param region target
   */
  public void setRegion(EnumRegion region)
  {
    this.region = region;
  }

  /**
   * Get the target region of the associated card
   * @return target region, or null if not used
   */
  public EnumRegion getRegion()
  {
    return region;
  }

  /**
   * Set the food target of the associated card
   * @param food target
   */
  public void setFood(EnumFood food)
  {
    this.food = food;
  }

  /**
   * Get the target food of the associated card
   * @return target food, or null if not used
   */
  public EnumFood getFood()
  {
    return food;
  }
}
