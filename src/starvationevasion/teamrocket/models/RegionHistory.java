package starvationevasion.teamrocket.models;

import com.google.common.collect.Iterables;
import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumRegion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by scnaegl on 11/14/15.
 */
public class RegionHistory
{
   /**
   * Keeps track of the originally selected RegionHistory.
   */
  public final EnumRegion ENUM_REGION;

//  private Deck deck;

  /**
   * we need to make the stats for this region.
   */
  public EnumRegion getEnumRegion()
  {
    return ENUM_REGION;
  }

  private Player player;

  private ArrayList<Double> undernourished = new ArrayList<>();
//  private ArrayList<Integer> micronutrientMalnourished = new ArrayList<>();
  private ArrayList<Double>  HDI = new ArrayList<>();
  private ArrayList<Integer> totalRevenue = new ArrayList<>(); //totalRevenue of the player and there country (a way of measuring score)
  private ArrayList<Integer> population = new ArrayList<>(); // population of the people in the region (a way of measuring score)
  private HashMap<EnumFood, ArrayList<Double>> pricePerTon = new HashMap<>();
  private HashMap<EnumFood, ArrayList<Integer>> cropRevenue = new HashMap<>();
  /**
   * A arraylist of the farm stats to keep track of them throughout the turns.
   */
  private HashMap<EnumFood, ArrayList<Integer>> cropProduced = new HashMap<>();

  private HashMap<EnumFood, ArrayList<Integer>> foodExported = new HashMap<>();

  private HashMap<EnumFood, ArrayList<Integer>> farmArea = new HashMap<>();

  /**
   * Creates a new RegionHistory with defaults based upon an EnumRegion. This will
   * only be called at the start of the game.
   *
   * @param enumRegion Deterines starting totalRevenue and crops.
   */
  public RegionHistory(EnumRegion enumRegion)
  {
    this.ENUM_REGION = enumRegion;
//    deck = new Deck(this);

    for(EnumFood food : EnumFood.values())
    {
      cropRevenue.put(food, new ArrayList<Integer>());
      cropProduced.put(food, new ArrayList<Integer>());
      pricePerTon.put(food,new ArrayList<Double>());
    }
    // This is where a regions starting crops are determined.
    // This might be replaced with something from the simulator or possibly
    // server. But I figure a switch statement is
    // fine for now.
    // It defaults to 0s until we have actual starting values.
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
        Random rand = new Random();
        for(EnumFood food : EnumFood.values())
        {
//          addCropProduced(food, rand.nextInt(100));
        }
    }
  }

  /*private String getRegionStats()
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

  }*/

  /**
   * adds the amount of revenue for a specfic crop
   *
   * @param food the type of food revenue
   * @param value the value that the food is being used for
   */
  public void addCropRevenue(EnumFood food, int value)
  {
    cropRevenue.get(food).add(value);
  }



  /**
   * gets a hashmap of the revenue for all of thelast crops.
   *
   * @return the the revenue for the last crops
   */
  public HashMap<EnumFood, Integer> getLastCropRevenue()
  {
    HashMap<EnumFood, Integer> latestData = new HashMap<>();
    for(EnumFood food : EnumFood.values())
    {
      latestData.put(food, cropRevenue.get(food).lastIndexOf(cropRevenue.get(food)));
    }
    return latestData;
  }

  /**
   * gets the crop revenue for all the turns
   *
   * @param food the type of food that we want
   * @return An arraylist of the food
   */
  public ArrayList<Integer> getCropRevenue(EnumFood food)
  {
    return cropRevenue.get(food);
  }


  /**
   * A value of how much of a crop is being produced
   *
   * @param food The type of food of food we want produced
   * @param value The amount of food that we want produced
   */
  public void addCropProduced(EnumFood food, int value)
  {
    cropProduced.get(food).add(value);
  }

  /**
   * the amount of food a crop is producing for the last turn
   *
   * @param food the type of food
   * @return the amount produced
   */
  public int getLastCropProduced(EnumFood food)
  {
    return cropProduced.get(food).lastIndexOf(cropProduced.get(food));
  }

  /**
   * the amount of food a crop is producing for the last turn
   *
   * @param food the type of food
   * @return the amount produced
   */
  public ArrayList<Integer> getCropProduced(EnumFood food)
  {
    return cropProduced.get(food);
  }

  /**
   *  produces a hashmap of how much food was produced for every food on the
   *  last turn
   *
   *  @return a hashmap of all of the crops produced
   */
  public HashMap<EnumFood, Integer> getLastCropProducedData()
  {
    HashMap<EnumFood, Integer> latestData = new HashMap<>();
    for(EnumFood food : EnumFood.values())
    {
      latestData.put(food, cropProduced.get(food).lastIndexOf(cropProduced.get(food)));
    }
    return latestData;
  }

  public void addFoodExported(EnumFood food, int value)
  {
    cropProduced.get(food).add(value);
  }

  public void addFarmArea(EnumFood food, int value) {
    if (farmArea.get(food) == null) {
      farmArea.put(food, new ArrayList<Integer>());
    }
    farmArea.get(food).add(value);
  }

  /**
   * adds a measure of the popualtion for a turn.
   * @param nextPopulation
   */
  public void addPopulation(int nextPopulation)
  {
    population.add(nextPopulation);
  }

  /**
   * get the population for the last turn.
   *
   * @return
   */
  public int getLastPopulation()
  {
    return population.lastIndexOf(population);
  }

  /**
   * adds to the total revenue by taking from all of the induvigual revenues
   */
  public void addTotalRevenue(int revenueBalance)
  {
//    HashMap<EnumFood, Integer> temp = getLastCropRevenue();
//    int total=0;
//    for(EnumFood food : EnumFood.values())
//    {
//      total =+ temp.get(food);
//    }
//    totalRevenue.add(total);
    totalRevenue.add(revenueBalance);
  }

  /**
   * gets total revenue for last turn a region
   *
   * @return the total revenue of a region
   */
  public int getLastTotalRevenue()
  {
    return totalRevenue.lastIndexOf(totalRevenue);
  }


  /**
   *
   * @param nextMalNut
   */
  public void addUndernourished(double nextMalNut)
  {
    undernourished.add(nextMalNut);
  }

//  /**
//   *
//   * @param nextMalNut
//   */
//  public void addMicronutrientMalnourished(int nextMalNut)
//  {
//    micronutrientMalnourished.add(nextMalNut);
//  }

  /**
   *
   * @return
   */
  public ArrayList<Double> getUndernourished()
  {
    return undernourished;
  }

//  /**
//   *
//   * @return
//   */
//  public ArrayList<Integer> getMicronutrientMalnourished()
//  {
//    return micronutrientMalnourished;
//  }

  /**
   * This was supposed to add a HDI, but apperently that will be given to us.
   * I don't want to delete it because it took a while to think up.
   */
  public void addHDI(double humanDevelopmentIndex)
  {
//    HDI.add(((double) getLastPopulation() - (getLastMicronutrientMalnourished() + getLastProteinEnergyMalnourished())) /(double) getLastPopulation());
    HDI.add(humanDevelopmentIndex);
  }
  /**
   *
   * @return
   */
  public Double getLastHDI()
  {
//    return  ((double) getLastPopulation() - (getLastMicronutrientMalnourished() + getLastProteinEnergyMalnourished()))
//        /(double) getLastPopulation();
    if (HDI == null) return 0d;
    if (HDI.isEmpty()) return 0d;
    return Iterables.getLast(HDI);
  }

  /**
   *
   * @return
   */
  public ArrayList<Double> getHDI()
  {
    return HDI;
  }

  /**
   *
   * @return
   */
  public int getLastProteinEnergyMalnourished()
  {
    return undernourished.lastIndexOf(undernourished);
  }

//  /**
//   *
//   * @return
//   */
//  public int getLastMicronutrientMalnourished()
//  {
//    return micronutrientMalnourished.lastIndexOf(micronutrientMalnourished);
//  }

  /**
   * get an array list of the population.
   *
   * @return the population array list
   */
  public ArrayList<Integer> getPopulation()
  {
    return population;
  }

  /**
   * Get's the total revenue that a region produces.
   *
   * @return the total revenue of a population
   */
  public ArrayList<Integer> getTotalRevenue()
  {
    return totalRevenue;
  }


  public void addPricePerTon(EnumFood food, double value)
  {
    pricePerTon.get(food).add(value);
  }


  public ArrayList<Double> getPricePerTon(EnumFood food)
  {
    return pricePerTon.get(food);
  }
}
