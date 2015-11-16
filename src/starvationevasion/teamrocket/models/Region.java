package starvationevasion.teamrocket.models;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Region
{
  /**
   * we need to make the stats for this region.
   */

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
