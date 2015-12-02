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
  private ArrayList<Double> HDI = new ArrayList<>();
  private ArrayList<Integer> revenue = new ArrayList<>(); //revenue of the player and there country (a way of measuring score)
  private ArrayList<Integer> population = new ArrayList<>(); // population of the people in the region (a way of measuring score)

  /**
   * A arraylist of the farm stats to keep track of them throughout the turns.
   */
  private HashMap<EnumFood, Stack<Double>>  cropValues = new HashMap<>();


  /**
   * Creates a new Region with defaults based upon an EnumRegion. This will
   * only be called at the start of the game.
   *
   * @param enumRegion Deterines starting revenue and crops.
   */
  public Region(EnumRegion enumRegion)
  {
    this.ENUM_REGION = enumRegion;
    deck = new Deck(this);

    for(EnumFood food : EnumFood.values())
    {
      cropValues.put(food, new Stack<Double>());
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

  public void addCropValue(EnumFood food, double value)
  {
    cropValues.get(food).push(value);
  }

  public double getCropValue(EnumFood food)
  {
    return cropValues.get(food).peek();
  }

  public HashMap<EnumFood, Double> getLastCropData()
  {
    HashMap<EnumFood, Double> latestData = new HashMap<>();
    for(EnumFood food : EnumFood.values())
    {
      latestData.put(food, cropValues.get(food).peek());
    }
    return latestData;
  }

  public void addPopulation(int nextPopulation)
  {
    population.add(nextPopulation);
  }

  public int getLastPopulation()
  {
    return population.lastIndexOf(population);
  }

  public void addRevenue(int nextRevenue)
  {
    revenue.add(nextRevenue);
  }

  public int getLastRevenue()
  {
    return revenue.lastIndexOf(revenue);
  }


  public HashMap<EnumFood, Stack<Double>> getCropValues()
  {
    return new HashMap<>(cropValues);
  }

  public void addProteinEnergyMalnourished(int nextMalNut)
  {
    proteinEnergyMalnourished.add(nextMalNut);
  }


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

  public ArrayList<Integer> getRevenue()
  {
    return revenue;
  }
}
