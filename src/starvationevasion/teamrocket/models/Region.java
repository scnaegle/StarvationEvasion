package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumRegion;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Region
{
  /**
   * Keeps track of the originally selected Region.
   */
  private final EnumRegion ENUM_REGION;
  /**
   * we need to make the stats for this region.
   */

  private Player player;

  private double citrusYield;
  private double fruitYield;
  private double nutYield;
  private double oilYield;
  private double veggieYield;
  private double speicalYield;
  private double feedYield;
  private double fishYield;
  private double meatYield;
  private double poultryYield;
  private double dairyYield;

  /**
   * Creates a new Region with defaults based upon an EnumRegion. This will only be called at the start of the game.
   *
   * @param enumRegion Deterines starting wealth and crops.
   */
  public Region(EnumRegion enumRegion)
  {
    this.ENUM_REGION = enumRegion;

    //This is where a regions starting crops are determined.
    //This might be replaced with something from the simulator or possibly server. But I figure a switch statement is
    // fine for now.
    //It defaults to 0s until we have actual starting values.
    switch (enumRegion)
    {
      case CALIFORNIA:
      case HEARTLAND:
      case NORTHERN_PLAINS:
      case SOUTHEAST:
      case NORTHERN_CRESCENT:
      case SOUTHERN_PLAINS:
      case MOUNTAIN:
      default:
        this.citrusYield = 0;
        this.fruitYield = 0;
        this.fishYield = 0;
        this.dairyYield = 0;
        this.meatYield = 0;
        this.poultryYield = 0;
        this.speicalYield = 0;
        this.oilYield = 0;
        this.veggieYield = 0;
        this.nutYield = 0;
        this.feedYield = 0;
    }
  }

  private String getRegionStats()
  {
    String regionStats;
    regionStats = "Citrus yield: " + getCitrusYield() + "\n" +
        "Fruit yield" + getFruitYield() + "\n" +
        "Nut yield" + getNutYield() + "\n" +
        "Oil yield" + getOilYield() + "\n" +
        "Veggie yield" + getVeggieYield() + "\n" +
        "Speical yield" + getSpeicalYield() + "\n" +
        "Feed yield" + getFeedYield() + "\n" +
        "Fish yield" + getFishYield() + "\n" +
        "Meat yield" + getMeatYield() + "\n" +
        "Poultry yield" + getPoultryYield() + "\n" +
        "Dairy yield" + getDairyYield();
    return regionStats;

  }

  public void setFruitYield(double fruitYield)
  {
    this.fruitYield = fruitYield;
  }

  public void setCitrusYield(double citrusYield)
  {
    this.citrusYield = citrusYield;
  }

  public void setNutYield(double nutYield)
  {
    this.nutYield = nutYield;
  }

  public void setOilYield(double oilYield)
  {
    this.oilYield = oilYield;
  }

  public void setVeggieYield(double veggieYield)
  {
    this.veggieYield = veggieYield;
  }

  public void setSpeicalYield(double speicalYield)
  {
    this.speicalYield = speicalYield;
  }

  public void setFeedYield(double feedYield)
  {
    this.feedYield = feedYield;
  }

  public void setFishYield(double fishYield)
  {
    this.fishYield = fishYield;
  }

  public void setMeatYield(double meatYield)
  {
    this.meatYield = meatYield;
  }

  public void setPoultryYield(double poultryYield)
  {
    this.poultryYield = poultryYield;
  }

  public void setDairyYield(double dairyYield)
  {
    this.dairyYield = dairyYield;
  }


  public double getCitrusYield()
  {
    return citrusYield;
  }

  public double getFruitYield()
  {
    return fruitYield;
  }

  public double getNutYield()
  {
    return nutYield;
  }

  public double getFeedYield()
  {
    return feedYield;
  }

  public double getOilYield()
  {
    return oilYield;
  }

  public double getVeggieYield()
  {
    return veggieYield;
  }

  public double getSpeicalYield()
  {
    return speicalYield;
  }

  public double getFishYield()
  {
    return fishYield;
  }

  public double getMeatYield()
  {
    return meatYield;
  }

  public double getPoultryYield()
  {
    return poultryYield;
  }

  public double getDairyYield()
  {
    return dairyYield;
  }
}
