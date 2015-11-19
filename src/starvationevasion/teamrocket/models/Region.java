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

  private double wealth;
      //wealth of the player and there country (a way of measuring score)
  private double happiness;
      // happiness of the people in the region (a way of measuring score)

  /**
   * A arraylist of the farm stats to keep track of them throughout the turns.
   */
  private  ArrayList<ArrayList<Double>> farmStats = new ArrayList<>();

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

    for(int i = 0; i < EnumFood.SIZE; i++) {
      farmStats.add(new ArrayList<Double>());
    }
    // This is where a regions starting crops are determined.
    // This might be replaced with something from the simulator or possibly
    // server. But I figure a switch statement is
    // fine for now.
    // It defaults to 0s until we have actual starting values.
    switch (enumRegion)
    {
      case CALIFORNIA:

        break;

      case HEARTLAND:
      case NORTHERN_PLAINS:
      case SOUTHEAST:
      case NORTHERN_CRESCENT:
      case SOUTHERN_PLAINS:
      case MOUNTAIN:
      default:
        setCitrusYield(10);
        setFruitYield(10);
        setFishYield(10);
        setDairyYield(10);
        setMeatYield(10);
        setPoultryYield(10);
        setSpecialYield(10);
        setOilYield(10);
        setVeggieYield(10);
        setNutYield(5);
        setFeedYield(5);
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

  /**
   * sets the fruit produced
   * @param fruitYield the amount of fruit produced
   */
  public void setFruitYield(double fruitYield)
  {
    farmStats.get(EnumFood.FRUIT.ordinal()).add(fruitYield);
  }


  /**
   * sets the fruit produced.
   * @param citrusYield the amount of fruit produced
   */
  public void setCitrusYield(double citrusYield)
  {
    farmStats.get(EnumFood.CITRUS.ordinal()).add(citrusYield);
  }

  /**
   * sets the nut produced.
   * @param nutYield the amount of nut produced
   */
  public void setNutYield(double nutYield)
  {
    farmStats.get(EnumFood.NUT.ordinal()).add(nutYield);}

  /**
   * sets the oil produced.
   * @param oilYield the amount of oil produced
   */
  public void setOilYield(double oilYield)
  {
    farmStats.get(EnumFood.OIL.ordinal()).add(oilYield);}

  /**
   * sets the veggie produced.
   * @param veggieYield the amount of veggie produced
   */
  public void setVeggieYield(double veggieYield)
  {
    farmStats.get(EnumFood.VEGGIES.ordinal()).add(veggieYield);}

  /**
   * sets the special produced.
   * @param specialYield the amount of special produced
   */
  public void setSpecialYield(double specialYield)
  {
    farmStats.get(EnumFood.SPECIAL.ordinal()).add(specialYield);
  }

  /**
   * sets the feed produced.
   * @param feedYield the amount of feed produced
   */
  public void setFeedYield(double feedYield)
  {
    farmStats.get(EnumFood.FEED.ordinal()).add(feedYield);
  }

  /**
   * sets the fish produced.
   * @param fishYield the amount of fish produced
   */
  public void setFishYield(double fishYield)
  {
    farmStats.get(EnumFood.FISH.ordinal()).add(fishYield);
  }

  /**
   * sets the meat produced.
   * @param meatYield the amount of meat produced
   */
  public void setMeatYield(double meatYield)
  {
    farmStats.get(EnumFood.MEAT.ordinal()).add(meatYield);
  }

  /**
   * sets the poultry produced.
   * @param poultryYield the amount of poultry produced
   */
  public void setPoultryYield(double poultryYield)
  {
    farmStats.get(EnumFood.POULTRY.ordinal()).add(poultryYield);
  }

  /**
   * sets the dairy produced.
   * @param dairyYield the amount of dairy produced
   */
  public void setDairyYield(double dairyYield)
  {
    farmStats.get(EnumFood.DAIRY.ordinal()).add(dairyYield);
  }

  /**
   * sets the dairy produced.
   * @param grainYield The amount of Grain produced
   */
  public void setGrainYield(double grainYield)
  {
    farmStats.get(EnumFood.GRAIN.ordinal()).add(grainYield);
  }


  /**
   * Gets the citrus produced for the last turn.
   * @return citrus produced for the last turn.
   */
  public double getCitrusYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.CITRUS.ordinal()));
  }

  /**
   * Gets the fruit produced for last turn.
   * @return fruit produced for last turn.
   */
  public double getFruitYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.FRUIT.ordinal()));
  }

  /**
   * Gets the nuts produced for last turn
   * @return Nuts produced for last turn
   */
  public double getNutYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.NUT.ordinal()));
  }

  /**
   * Gets the feed produced for the last turn
   * @return Feed produced for the last turn
   */
  public double getFeedYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.FEED.ordinal()));
  }

  /**
   * Gets the oil produced for the last turn
   * @return Oil produced for the last turn
   */
  public double getOilYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.OIL.ordinal()));
  }

  /**
   * Gets the veggies produced for the last turn
   * @return Veggies produced for the last turn
   */
  public double getVeggieYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.VEGGIES.ordinal()));
  }

  /**
   * Gets the special produced for the last turn
   * @return Special produced for the last turn
   */
  public double getSpecialYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.SPECIAL.ordinal()));
  }

  /**
   * Gets the fish produced for the last turn
   * @return Fish produced for the last turn
   */
  public double getFishYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.FISH.ordinal()));
  }

  /**
   * Gets the meat produced for the last turn
   * @return Meat produced for the last turn
   */
  public double getMeatYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.MEAT.ordinal()));
  }

  /**
   * Gets the poultry produced for the last turn
   * @return Poultry produced for the last turn
   */
  public double getPoultryYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.POULTRY.ordinal()));
  }

  /**
   * Gets the dairy produced for the last turn
   * @return Dairy produced for the last turn
   */
  public double getDairyYield()
  {
    return Iterables.getLast(farmStats.get(EnumFood.DAIRY.ordinal()));
  }


  /**
   * Gets the grain produced for the last turn
   * @return Grain produced for the last turn
   */
  public double getGrainYeild()
  {
    return Iterables.getLast(farmStats.get(EnumFood.GRAIN.ordinal()));
  }

}
