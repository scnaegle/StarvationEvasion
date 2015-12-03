package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumRegion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

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

  public EnumRegion getEnumRegion()
  {
    return ENUM_REGION;
  }

  private Player player;

  private ArrayList<Integer> proteinEnergyMalnourished = new ArrayList<>();
  private ArrayList<Integer> micronutrientMalnourished = new ArrayList<>();
  private ArrayList<Double>  HDI = new ArrayList<>();
  private ArrayList<Integer> totalRevenue = new ArrayList<>(); //totalRevenue of the player and there country (a way of measuring score)
  private ArrayList<Integer> population = new ArrayList<>(); // population of the people in the region (a way of measuring score)

  private HashMap<EnumFood, ArrayList<Integer>> cropRevenue = new HashMap<>();

  /**
   * A arraylist of the farm stats to keep track of them throughout the turns.
   */
  private HashMap<EnumFood, Stack<Integer>>  cropValues = new HashMap<>();

  /**
   * Creates a new Region with defaults based upon an EnumRegion. This will
   * only be called at the start of the game.
   *
   * @param enumRegion Deterines starting totalRevenue and crops.
   */
  public Region(EnumRegion enumRegion)
  {
    this.ENUM_REGION = enumRegion;
    deck = new Deck(this);

    for(EnumFood food : EnumFood.values())
    {
      cropRevenue.put(food, new ArrayList<Integer>());
      cropValues.put(food, new Stack<Integer>());
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
        Random rand = new Random();
        for(EnumFood food : EnumFood.values())
        {
          addCropValue(food, rand.nextInt(100));
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
  public void addCropValue(EnumFood food, int value)
  {
    cropValues.get(food).push(value);
  }

  /**
   * the amount of food a crop is producing
   *
   * @param food the type of food
   * @return the amount produced
   */
  public int getCropProduced(EnumFood food)
  {
    return cropValues.get(food).peek();
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
      latestData.put(food, cropValues.get(food).peek());
    }
    return latestData;
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
  public void addTotalRevenue()
  {
    HashMap<EnumFood, Integer> temp = getLastCropRevenue();
    int total=0;
    for(EnumFood food : EnumFood.values())
    {
      total =+ temp.get(food);
    }
    totalRevenue.add(total);
  }

  /**
   * gets total revenue for last turn
   *
   * @return
   */
  public int getLastTotalRevenue()
  {
    return totalRevenue.lastIndexOf(totalRevenue);
  }

  public HashMap<EnumFood, Stack<Integer>> getCropValues()
  {
    return new HashMap<>(cropValues);
  }

  public void addProteinEnergyMalnourished(int nextMalNut)
  {
    proteinEnergyMalnourished.add(nextMalNut);
  }

  /**
   *
   * @param nextMalNut 
   */
  public void addMicronutrientMalnourished(int nextMalNut)
  {
    micronutrientMalnourished.add(nextMalNut);
  }

  public ArrayList<Integer> getProteinEnergyMalnourished()
  {
    return proteinEnergyMalnourished;
  }

  public ArrayList<Integer> getMicronutrientMalnourished()
  {
    return micronutrientMalnourished;
  }

  //this Must be called after microNut and ProNutrient have been set.
  public void addHDI()
  {
    HDI.add(((double) getLastPopulation() - (getLastMicronutrientMalnourished() + getLastProteinEnergyMalnourished())) /(double) getLastPopulation());
  }

  public Double getLastHDI()
  {
    return  ((double) getLastPopulation() - (getLastMicronutrientMalnourished() + getLastProteinEnergyMalnourished()))
        /(double) getLastPopulation();
  }

  public ArrayList<Double> getHDI()
  {
    return HDI;
  }


  public int getLastProteinEnergyMalnourished()
  {
    return proteinEnergyMalnourished.lastIndexOf(proteinEnergyMalnourished);
  }

  public int getLastMicronutrientMalnourished()
  {
    return micronutrientMalnourished.lastIndexOf(micronutrientMalnourished);
  }

  public ArrayList<Integer> getPopulation()
  {
    return population;
  }

  public ArrayList<Integer> getTotalRevenue()
  {
    return totalRevenue;
  }
}
