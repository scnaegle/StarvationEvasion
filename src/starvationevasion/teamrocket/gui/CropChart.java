package starvationevasion.teamrocket.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.models.RegionHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * This class has 9 different types of charts.
 * <p>
 * makeRegionFoodPieChart(EnumRegion region)
 * - This is used to make a pie chart of the crops produced for the last turn
 * - This should go in the Drafting scene and be matched up with the regions
 * <p>
 * makeOneRegionRevenuePieChart(EnumRegion region)
 * - This is used to make a pie chart of the revenue of the crops for the region
 * for the last turn
 * - This should go in the Drafting scene and be matched up with the regions
 * <p>
 * makeHDIPieChart(RegionHistory region)
 * - This is a pie chart to represent the percentage of malnushied vs nurished
 * population for the last turn
 * - This should go in the voting phase
 * <p>
 * makeTotalRevenueAllRegionPieChart(RegionHistory[] region)
 * - This is used to make a pie chart of the total revenue for all the regions
 * for the last turn
 * - This should go on the drafting scene
 * <p>
 * makePopulationPieChart(RegionHistory[] region)
 * - This makes a population pie chart for all of the regions and how much of
 * the worlds population has.
 * - This could go on the drafting scene
 * <p>
 * makeLineChartRegionSpecificFood(RegionHistory region, EnumFood food)
 * - This makes a line chart for a specific food and for a specific region
 * - This can on the voting or drafting scene
 * <p>
 * makeLineChartRegionPopulation(RegionHistory region)
 * - This is used to make a line chart of a population
 * - This can go in the voting scene
 * <p>
 * makeLineChartRegionRevenue(RegionHistory region)
 * - This will give a line charge of a of a regions total revenue over time
 * - This can go to the voting scene
 * <p>
 * makeLineChartRegionFood(RegionHistory region, boolean[] foods)
 * - This will give a line chart of all the crops
 * - The booleans are lined up with crops in order to toggle on or off.
 */


/**
 * This class is used to make the charts such as the pie chart and
 * line graphs.
 */
public class CropChart
{
  public static final int YEAR_START = 1981;
  public static final int YEAR_END = 2050;

  /**
   * Makes a pie chart to add to the GUI of a region and what it is producing
   * for it's crops this turn.
   *
   * @param region the region that we are making the pie chart for.
   * @return Returns a pie chart to add to the gui.
   */
  public static PieChart makeRegionFoodPieChart(RegionHistory region)
  {
    HashMap<EnumFood, Integer> data = region.getLastCropProducedData();
    ArrayList<PieChart.Data> dataList = new ArrayList<>();

    for (EnumFood food : EnumFood.values())
    {
      dataList.add(new PieChart.Data(food.name(), data.get(food)));
    }
    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(dataList);
    PieChart chart = new PieChart(pieChartData);

    chart.setTitle("Crops Produced");
    chart.setLegendSide(Side.BOTTOM);
    chart.setVisible(true);
    return chart;
  }


  /**
   * Makes a pie chart for the revenue of the region
   *
   * @param region the region that we want the pie chart for
   * @return a pie chart of the regions revenue
   */
  public static PieChart makeOneRegionRevenuePieChart(RegionHistory region)
  {
    HashMap<EnumFood, Integer> data = region.getLastCropRevenue();
    ArrayList<PieChart.Data> dataList = new ArrayList<>();

    for (EnumFood food : EnumFood.values())
    {
      dataList.add(new PieChart.Data(food.name(), data.get(food)));
    }
    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(dataList);
    PieChart chart = new PieChart(pieChartData);

    chart.setTitle("Revenue of Crops");
    chart.setLegendSide(Side.BOTTOM);
    chart.setVisible(true);
    return chart;
  }

  /**
   * Makes a pie chart for the revenue of the region
   *
   * @param region the region that we want the pie chart for
   * @return a pie chart of the regions revenue
   */
  public static PieChart makeHDIPieChart(RegionHistory region)
  {
    HashMap<EnumFood, Integer> data = region.getLastCropProducedData();

    ArrayList<PieChart.Data> dataList = new ArrayList<>();

    dataList.add(new PieChart.Data("Malnurished", region.getLastHDI()));
    dataList.add(new PieChart.Data("Nurished", 1 - region.getLastHDI()));

    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(dataList);
    PieChart chart = new PieChart(pieChartData);

    chart.setTitle("HDI");
    chart.setLegendSide(Side.BOTTOM);
    chart.setVisible(true);
    return chart;
  }

  /**
   * Makes a pie chart for the revenue of the region
   *
   * @param region the region that we want the pie chart for
   * @return a pie chart of the regions revenue
   */
  public static PieChart makeTotalRevenueAllRegionPieChart(
      RegionHistory[] region)
  {
    ArrayList<PieChart.Data> dataList = new ArrayList<>();

    for (RegionHistory regions : region)
    {
      dataList.add(new PieChart.Data(regions.getEnumRegion().name(),
          regions.getLastTotalRevenue()));
    }
    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(dataList);
    PieChart chart = new PieChart(pieChartData);

    chart.setTitle("Revenue");
    chart.setLegendSide(Side.BOTTOM);
    chart.setVisible(true);
    return chart;
  }


  /**
   * makes a pie chart for all the regions showing how much of the population
   * is in each region.
   *
   * @param region All of the regions
   * @return a pie chart of the population
   */
  public static PieChart makePopulationPieChart(RegionHistory[] region)
  {

    ArrayList<PieChart.Data> dataList = new ArrayList<>();

    for (RegionHistory regions : region)
    {
      dataList.add(new PieChart.Data(regions.getEnumRegion().name(),
          regions.getLastPopulation()));
    }
    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(dataList);
    PieChart chart = new PieChart(pieChartData);

    chart.setTitle("Population");
    chart.setLegendSide(Side.BOTTOM);
    chart.setVisible(true);
    return chart;
  }


  /**
   * Makes a line chart of the data from our crops and regions, which spans
   * the entire
   * turns
   *
   * @param region the region of the food
   * @param food   the type of food we are graphinf
   * @return a line chart for being displayed.
   */
  public static LineChart makeLineChartRegionSpecificFood(RegionHistory region,
                                                          EnumFood food)
  {
    final NumberAxis xAxis = new NumberAxis(YEAR_START, YEAR_END, 3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number, Number> lineChart =
        new LineChart<Number, Number>(xAxis, yAxis);

    ArrayList<Integer> data = region.getCropProduced(food);

    XYChart.Series series = new XYChart.Series();


    int iterateTurnNumber = 0;
    int actualTurnNumber = 5;
    for (int dataout : data)
    {
      //System.out.println(data);
      series.getData().add(
          new XYChart.Data(YEAR_START + (iterateTurnNumber * 3), dataout));
      iterateTurnNumber++;
    }

    series.setName(food.name());
    lineChart.getData().add(series);
    return lineChart;
  }

  /**
   * Makes a line chart of the data from our crops and regions, which spans
   * the entire
   * turns
   *
   * @param region the region of the food
   * @param food   the type of food we are graphs
   * @return a line chart for being displayed.
   */
  public static LineChart makeLineChartPricePerMetricTonFood(RegionHistory region,
                                                          EnumFood food)
  {
    final NumberAxis xAxis = new NumberAxis(YEAR_START, YEAR_END, 3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number, Number> lineChart =
        new LineChart<Number, Number>(xAxis, yAxis);

    ArrayList<Double> data = region.getPricePerTon(food);

    XYChart.Series series = new XYChart.Series();


    int iterateTurnNumber = 0;
    int actualTurnNumber = 5;
    for (double dataout : data)
    {
      //System.out.println(data);
      series.getData().add(
          new XYChart.Data(YEAR_START + (iterateTurnNumber * 3), dataout));
      iterateTurnNumber++;
    }
    series.setName(food.name());
    lineChart.getData().add(series);
    return lineChart;
  }

  /**
   * Makes a line chart of all the crops of the regions
   *
   * @param region the region that we want
   * @param foods booleans to tell what we want
   * @return a line chart of the foods
   */
  public static LineChart makeLineChartRegionFood(RegionHistory region,
                                                  boolean[] foods)
  {

    final NumberAxis xAxis = new NumberAxis(YEAR_START, YEAR_END, 3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number, Number> lineChart =
        new LineChart<Number, Number>(xAxis, yAxis);

    /**
     * foods[0] - non citrus
     * foods[1] - grains
     * foods[2] - citrus
     * foods[3] - feed crops
     * foods[4] - dairy
     * foods[5] - fish
     * foods[6] - red meats
     * foods[7] - nuts
     * foods[8] - oil plants
     * foods[9] - poultry
     * foods[10] - vegitables
     * foods[11] - specical crops
     */

    if (foods[0] == true)
    {
      ArrayList<Integer> data = region.getCropProduced(EnumFood.FRUIT);
      if (data.lastIndexOf(data) != 0)
      {
        XYChart.Series series = new XYChart.Series();
        int iterateTurnNumber = 0;
        for (int dataout : data)
        {
          //System.out.println(data);
          series.getData().add(
              new XYChart.Data(YEAR_START + (iterateTurnNumber * 3), dataout));
          iterateTurnNumber++;
        }
        series.setName("Fruit");
        lineChart.getData().add(series);
      }
    }

    if (foods[1])
    {
      ArrayList<Integer> data = region.getCropProduced(EnumFood.GRAIN);
      if (data.lastIndexOf(data) != 0)
      {
        XYChart.Series series = new XYChart.Series();
        int iterateTurnNumber = 0;
        for (int dataout : data)
        {
          //System.out.println(data);
          series.getData().add(
              new XYChart.Data(YEAR_START + (iterateTurnNumber * 3), dataout));
          iterateTurnNumber++;
        }
        series.setName("Grains");
        lineChart.getData().add(series);
      }
    }
    if (foods[2])
    {
      ArrayList<Integer> data = region.getCropProduced(EnumFood.CITRUS);
      if (data.lastIndexOf(data) != 0)
      {
        XYChart.Series series = new XYChart.Series();
        int iterateTurnNumber = 0;
        for (int dataout : data)
        {
          //System.out.println(data);
          series.getData().add(
              new XYChart.Data(YEAR_START + (iterateTurnNumber * 3), dataout));
          iterateTurnNumber++;
        }
        series.setName("Citrus");
        lineChart.getData().add(series);
      }
    }

    if (foods[3])
    {
      ArrayList<Integer> data = region.getCropProduced(EnumFood.FEED);
      if (data.lastIndexOf(data) != 0)
      {
        XYChart.Series series = new XYChart.Series();
        int iterateTurnNumber = 0;
        for (int dataout : data)
        {
          //System.out.println(data);
          series.getData().add(
              new XYChart.Data(YEAR_START + (iterateTurnNumber * 3), dataout));
          iterateTurnNumber++;
        }
        series.setName("Feed");
        lineChart.getData().add(series);
      }
    }

    if (foods[4])
    {
      ArrayList<Integer> data = region.getCropProduced(EnumFood.DAIRY);
      if (data.lastIndexOf(data) != 0)
      {
        XYChart.Series series = new XYChart.Series();
        int iterateTurnNumber = 0;
        for (int dataout : data)
        {
          //System.out.println(data);
          series.getData().add(
              new XYChart.Data(YEAR_START + (iterateTurnNumber * 3), dataout));
          iterateTurnNumber++;
        }
        series.setName("Dairy");
        lineChart.getData().add(series);
      }
    }

    if (foods[5])
    {
      ArrayList<Integer> data = region.getCropProduced(EnumFood.FISH);
      if (data.lastIndexOf(data) != 0)
      {
        XYChart.Series series = new XYChart.Series();
        int iterateTurnNumber = 0;
        for (int dataout : data)
        {
          //System.out.println(data);
          series.getData().add(
              new XYChart.Data(YEAR_START + (iterateTurnNumber * 3),
                  dataout));
          iterateTurnNumber++;
        }
        series.setName("Fish");
        lineChart.getData().add(series);
      }
    }

    if (foods[6])
    {
      ArrayList<Integer> data = region.getCropProduced(EnumFood.MEAT);
      if (data.lastIndexOf(data) != 0)
      {
        XYChart.Series series = new XYChart.Series();
        int iterateTurnNumber = 0;
        for (int dataout : data)
        {
          //System.out.println(data);
          series.getData().add(
              new XYChart.Data(YEAR_START + (iterateTurnNumber * 3),
                  dataout));
          iterateTurnNumber++;
        }
        series.setName("Meat");
        lineChart.getData().add(series);
      }
    }

    if (foods[7])
    {
      ArrayList<Integer> data =
          region.getCropProduced(EnumFood.NUT);
      if (data.lastIndexOf(data) != 0)
      {
        XYChart.Series series = new XYChart.Series();
        int iterateTurnNumber = 0;
        for (int dataout : data)
        {
          //System.out.println(data);
          series.getData().add(
              new XYChart.Data(YEAR_START + (iterateTurnNumber * 3),
                  dataout));
          iterateTurnNumber++;
        }
        series.setName("Nut");
        lineChart.getData().add(series);
      }
    }

    if (foods[8])
    {
      ArrayList<Integer> data =
          region.getCropProduced(EnumFood.OIL);
      if (data.lastIndexOf(data) != 0)
      {
        XYChart.Series series = new XYChart.Series();
        int iterateTurnNumber = 0;
        for (int dataout : data)
        {
          //System.out.println(data);
          series.getData().add(
              new XYChart.Data(YEAR_START + (iterateTurnNumber * 3),
                  dataout));
          iterateTurnNumber++;
        }
        series.setName("Oil");
        lineChart.getData().add(series);
      }
    }

    if (foods[9])
    {
      ArrayList<Integer> data =
          region.getCropProduced(EnumFood.POULTRY);
      if (data.lastIndexOf(data) != 0)
      {
        XYChart.Series series = new XYChart.Series();
        int iterateTurnNumber = 0;
        for (int dataout : data)
        {
          //System.out.println(data);
          series.getData().add(
              new XYChart.Data(YEAR_START + (iterateTurnNumber * 3),
                  dataout));
          iterateTurnNumber++;
        }
        series.setName("Poultry");
        lineChart.getData().add(series);
      }
    }

    if (foods[10])
    {
      ArrayList<Integer> data =
          region.getCropProduced(EnumFood.VEGGIES);
      if (data.lastIndexOf(data)!=0)
      {
        XYChart.Series series = new XYChart.Series();
        int iterateTurnNumber = 0;
        for (int dataout : data)
        {
          //System.out.println(data);
          series.getData().add(
              new XYChart.Data(YEAR_START + (iterateTurnNumber * 3),
                  dataout));
          iterateTurnNumber++;
        }
        series.setName("Veggies");
        lineChart.getData().add(series);
      }
    }

    if (foods[11])
    {
      ArrayList<Integer> data =
          region.getCropProduced(EnumFood.SPECIAL);
      if (data.lastIndexOf(data) != 0)
      {
        XYChart.Series series = new XYChart.Series();
        int iterateTurnNumber = 0;
        for (int dataout : data)
        {
          //System.out.println(data);
          series.getData().add(
              new XYChart.Data(YEAR_START + (iterateTurnNumber * 3),
                  dataout));
          iterateTurnNumber++;
        }
        series.setName("Special");
        lineChart.getData().add(series);
      }
    }

    return lineChart;
  }


  /**
   * Makes a line chart of the data from our crops and regions, which spans
   * the game
   *
   * @param regions the region of the food
   * @param food    the type of food we are graphinf
   * @return a line chart for being displayed.
   */
  public static LineChart makeLineChartForSpecificFoodRevenue(
      RegionHistory[] regions,
      EnumFood food)
  {
    final NumberAxis xAxis = new NumberAxis(YEAR_START, YEAR_END, 3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number, Number> lineChart =
        new LineChart<Number, Number>(xAxis, yAxis);
    ArrayList<Integer> tempArrayList = new ArrayList<>();

    for (int i = 0; i < 26; i++)
    {
      tempArrayList.add(0);
    }

    for (RegionHistory region : regions)
    {
      int i = 0;
      int tempVarible = 0;
      ArrayList<Integer> data = region.getCropRevenue(food);
      for (int values : data)
      {
        tempVarible = tempArrayList.get(i);
        tempArrayList.set(i, values + tempVarible);
        i++;
      }

    }
    ArrayList<XYChart.Series> dataList = new ArrayList<>();

    XYChart.Series series = new XYChart.Series();


    int iterateTurnNumber = 0;
    int actualTurnNumber = 5;
    for (int list : tempArrayList)
    {
      System.out.println(list);
      series.getData()
            .add(new XYChart.Data(YEAR_START + (iterateTurnNumber * 3),
                list));
      iterateTurnNumber++;
    }

    series.setName("Revenue");
    lineChart.getData().add(series);
    return lineChart;
  }

  /**
   * Makes a line chart of the population
   *
   * @param region the region that we want the population from
   * @return a line chart for being displayed.
   */
  public static LineChart makeLineChartRegionPopulation(RegionHistory region)
  {
    final NumberAxis xAxis = new NumberAxis(YEAR_START, YEAR_END, 3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number, Number> lineChart =
        new LineChart<Number, Number>(xAxis, yAxis);


    ArrayList<Integer> population = region.getPopulation();

    ArrayList<XYChart.Series> dataList = new ArrayList<>();

    XYChart.Series series = new XYChart.Series();


    int iterateTurnNumber = 0;
    for (Integer populations : population)
    {
      series.getData()
            .add(new XYChart.Data(YEAR_START + (iterateTurnNumber * 3),
                populations));
      iterateTurnNumber++;
    }

    series.setName("Population");
    lineChart.getData().add(series);
    return lineChart;
  }

  /**
   * Makes a line chart of the population
   *
   * @param region the region that we want the population from
   * @return a line chart for being displayed.
   */
  public static LineChart makeLineChartRegionRevenue(RegionHistory
                                                         region)
  {
    final NumberAxis xAxis = new NumberAxis(YEAR_START, YEAR_END, 3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number, Number> lineChart =
        new LineChart<Number, Number>(xAxis, yAxis);


    ArrayList<Integer> revenue = region.getTotalRevenue();

    XYChart.Series series = new XYChart.Series();

    int iterateTurnNumber = 0;
    for (Integer revenues : revenue)
    {
      series.getData()
            .add(new XYChart.Data(YEAR_START + (iterateTurnNumber * 3),
                revenues));
      iterateTurnNumber++;
    }

    series.setName("Revenue");
    lineChart.getData().add(series);
    return lineChart;
  }
}
