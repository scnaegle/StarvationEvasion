package starvationevasion.common;

import java.io.Serializable;

/**
 * This structure contains all data of a particular region that the simulator shares with
 * each client via the Server.
 */

public class RegionData implements Serializable
{
  public final EnumRegion region;

  /**
   * This field is zero for non-player regions.
   * Total player revenue in millions of dollars for the current simulation year.
   * This is the past year's revenue balance plus new taxes earned during the
   * current turn of three years, minus expenses during the current turn of three years.
   */
  public int revenueBalance;


  /**
   * This region's population (in thousands of people) during the current year.
   */
  public int population;


  /**
   * This region's percent of undernourished people at start of the current year.
   */
  public double undernourished;


  /**
   * This region's Human Development Index at the start of the current year.
   */
  public double humanDevelopmentIndex;

  /**
   * This region's production (in metric tons) of each foodType during the past turn (3 years).<br><br>
   * Index by EnumFood.ordinal()
   */
  public int[] foodProduced = new int[EnumFood.SIZE];


  /**
   * This is the farm income in millions of dollars from to the region's
   * production of each foodType during the past turn (3 years).<br><br>
   * This includes farm income for food consumed as well as income from exported
   * foods.<br><br>
   * Note: foodProduced*foodPrice for a given food is the gross income. Gross income will
   * always be greater than this field with is the profet (gross less expenses). Note also
   * that different regions have different efficiency levels depending on the crop, climate,
   * infrastructure, tax breaks, and other factors.<br><br>
   * <p>
   * <p>
   * Index by EnumFood.ordinal()
   */
  public int[] foodIncome = new int[EnumFood.SIZE];


  /**
   * This region's food exported (in metric tons) of each foodType during the past turn
   * (3 years) Index by EnumFood.ordinal(). Positive indicates export, negative indicates
   * import.<br><br>
   * <p>
   * The region's consumption of for each foodType is:<br>
   * {@link #foodProduced}[i]-{@link #foodExported}[i]
   */
  public int[] foodExported = new int[EnumFood.SIZE];


  /**
   * This region's current ethanol producer tax credit as an
   * integer percentage from [0 through 100]. This percentage of all profits earned
   * by farmers in this
   * region for sale of farm products used in ethanol production is tax deductible.
   */
  public int ethanolProducerTaxCredit;


  /**
   * This region's land area (in square kilometers)
   * being used for farm production of each crop type at the end of the current year.
   */
  public int[] farmArea = new int[EnumFood.SIZE];

  public RegionData(EnumRegion region)
  {
    this.region = region;
  }

  /**
   * @return Data stored in this structure as a formatted String.
   */
  public String toString()
  {
    String msg = region.toString();
    if (region.isUS())
    {
      msg += "[$" + revenueBalance + "]";
    }

    msg += String.format(": pop=%d(%.1f), HDI=%.2f [", population, undernourished, humanDevelopmentIndex);

    for (EnumFood food : EnumFood.values())
    {
      msg += String.format("%s:%d+%d",
                           food, foodProduced[food.ordinal()], foodExported[food.ordinal()]);
      if (food != EnumFood.DAIRY)
      {
        msg += ", ";
      }
      else
      {
        msg += "]";
      }
    }

    return msg;
  }
}
