package starvationevasion.teamrocket.models;

import com.google.common.collect.Iterables;
import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumRegion;

import java.util.ArrayList;

/**
 * Created by scnaegl on 11/14/15.
 */
public class Region
{
  /**
   * Keeps track of the originally selected Region.
   */
  public final EnumRegion ENUM_REGION;

  private Deck deck;
  /**
   * we need to make the stats for this region.
   */

  private Player player;

  private double citrusYield;
  private double fruitYield;
  private double nutYield;
  private double oilYield;
  private double veggieYield;
  private double specialYield;
  private double feedYield;
  private double fishYield;
  private double meatYield;
  private double poultryYield;
  private double dairyYield;

  private double wealth;
      //wealth of the player and there country (a way of measuring score)
  private double happiness;
      // happiness of the people in the region (a way of measuring score)

  private ArrayList<ArrayList<Double>> farmStats = new ArrayList<>();

  /**
   * Creates a new Region with defaults based upon an EnumRegion. This will
   * only be called at the start of the game.
   *
   * @param enumRegion Deterines starting wealth and crops.
   */
  public Region(EnumRegion enumRegion)
  {
    this.ENUM_REGION = enumRegion;
    deck = new Deck(this);

    farmStats.get(EnumFood.CITRUS.ordinal()).add(3.45);
    //This is where a regions starting crops are determined.
    //This might be replaced with something from the simulator or possibly
    // server. But I figure a switch statement is
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
        this.specialYield = 0;
        this.oilYield = 0;
        this.veggieYield = 0;
        this.nutYield = 0;
        this.feedYield = 0;
    }
  }

  /**
   * Get the player's deck of cards
   *
   * @return Player's deck of cards
   */
  public Deck getDeck()
  {
    return deck;
  }

  private String getRegionStats()
  {
    String regionStats;
    regionStats = "Citrus yield: " + getCitrusYield() + "\n" +
        "Fruit yield" + getFruitYield() + "\n" +
        "Nut yield" + getNutYield() + "\n" +
        "Oil yield" + getOilYield() + "\n" +
        "Veggie yield" + getVeggieYield() + "\n" +
        "Special yield" + getSpecialYield() + "\n" +
        "Feed yield" + getFeedYield() + "\n" +
        "Fish yield" + getFishYield() + "\n" +
        "Meat yield" + getMeatYield() + "\n" +
        "Poultry yield" + getPoultryYield() + "\n" +
        "Dairy yield" + getDairyYield();
    return regionStats;

  }

  public void setFruitYield(double fruitYield)
  {
    farmStats.get(EnumFood.FRUIT.ordinal()).add(fruitYield);
  }

  public void setCitrusYield(double citrusYield)
  {
    farmStats.get(EnumFood.CITRUS.ordinal()).add(citrusYield);
  }

  public void setNutYield(double nutYield)
  {
    farmStats.get(EnumFood.NUT.ordinal()).add(nutYield);}

  public void setOilYield(double oilYield)
  {
    farmStats.get(EnumFood.OIL.ordinal()).add(oilYield);}

  public void setVeggieYield(double veggieYield)
  {
    farmStats.get(EnumFood.VEGGIES.ordinal()).add(veggieYield);}

  public void setSpecialYield(double specialYield)
  {
    farmStats.get(EnumFood.SPECIAL.ordinal()).add(specialYield);
  }

  public void setFeedYield(double feedYield)
  {
    farmStats.get(EnumFood.FEED.ordinal()).add(feedYield);
  }

  public void setFishYield(double fishYield)
  {
    farmStats.get(EnumFood.FISH.ordinal()).add(fishYield);
  }

  public void setMeatYield(double meatYield)
  {
    farmStats.get(EnumFood.MEAT.ordinal()).add(meatYield);
  }

  public void setPoultryYield(double poultryYield)
  {
    farmStats.get(EnumFood.POULTRY.ordinal()).add(poultryYield);
  }

  public void setDairyYield(double dairyYield)
  {
    farmStats.get(EnumFood.DAIRY.ordinal()).add(dairyYield);
  }


  public double getCitrusYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.CITRUS.ordinal()));
  }

  public double getFruitYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.FRUIT.ordinal()));
  }

  public double getNutYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.NUT.ordinal()));
  }

  public double getFeedYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.FEED.ordinal()));
  }

  public double getOilYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.OIL.ordinal()));
  }

  public double getVeggieYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.VEGGIES.ordinal()));
  }

  public double getSpecialYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.SPECIAL.ordinal()));
  }

  public double getFishYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.FISH.ordinal()));
  }

  public double getMeatYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.MEAT.ordinal()));
  }

  public double getPoultryYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.POULTRY.ordinal()));
  }

  public double getDairyYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.DAIRY.ordinal()));
  }
}
