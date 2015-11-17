package starvationevasion.teamrocket.models;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Region
{
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

  public Region(double citrusYield, double fruitYield, double nutYield,
                double oilYield, double veggieYield, double speicalYield,
                double feedYield,
                double fishYield, double meatYield, double poultryYield,
                double dairyYield)
  {
    this.citrusYield = citrusYield;
    this.fruitYield = fruitYield;
    this.fishYield = fishYield;
    this.dairyYield = dairyYield;
    this.meatYield = meatYield;
    this.poultryYield = poultryYield;
    this.speicalYield = speicalYield;
    this.oilYield = oilYield;
    this.veggieYield = veggieYield;
    this.nutYield = nutYield;
    this.feedYield = feedYield;
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
