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
 *
 * makeRegionFoodPieChart(EnumRegion region)
 *  - This is used to make a pie chart of the crops produced for the last turn
 *  - This should go in the Drafting scene and be matched up with the regions
 *
 *  makeOneRegionRevenuePieChart(EnumRegion region)
 *  - This is used to make a pie chart of the revenue of the crops for the region
 *    for the last turn
 *  - This should go in the Drafting scene and be matched up with the regions
 *
 * makeHDIPieChart(RegionHistory region)
 *  - This is a pie chart to represent the percentage of malnushied vs nurished
 *    population for the last turn
 *  - This should go in the voting phase
 *
 *  makeTotalRevenueAllRegionPieChart(RegionHistory[] region)
 *    - This is used to make a pie chart of the total revenue for all the regions
 *      for the last turn
 *    - This should go on the drafting scene
 *
 *  makePopulationPieChart(RegionHistory[] region)
 *    - This makes a population pie chart for all of the regions and how much of
 *      the worlds population has.
 *    - This could go on the drafting scene
 *
 *  makeLineChartRegionSpecificFood(RegionHistory region, EnumFood food)
 *    - This makes a line chart for a specific food and for a specific region
 *    - This can on the voting or drafting scene
 *
 *  makeLineChartRegionPopulation(RegionHistory region)
 *    - This is used to make a line chart of a population
 *    - This can go in the voting scene
 *
 *  makeLineChartRegionRevenue(RegionHistory region)
 *    - This will give a line charge of a of a regions total revenue over time
 *    - This can go to the voting scene
 */


/**
 * This class is used to make the charts such as the pie chart and
 * line graphs.
 */
public class CropChart
{
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

    dataList.add(new PieChart.Data("Malnurished", 1 - region.getLastHDI()));
    dataList.add(new PieChart.Data("Nurished", region.getLastHDI()));

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
  public static PieChart makeTotalRevenueAllRegionPieChart(RegionHistory[] region)
  {
    ArrayList<PieChart.Data> dataList = new ArrayList<>();

    for(RegionHistory regions : region)
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

    for(RegionHistory regions : region)
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
    final NumberAxis xAxis = new NumberAxis(1980, 2052, 3);
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
          new XYChart.Data(1980 + (iterateTurnNumber * 3), dataout));
      iterateTurnNumber++;
    }

    series.setName("My portfolio");
    lineChart.getData().add(series);
    return lineChart;
  }

  /**
   * Makes a line chart of the data from our crops and regions, which spans
   * the entire
   * turns
   *
   * @param regions the region of the food
   * @param food    the type of food we are graphinf
   * @return a line chart for being displayed.
   */
  public static LineChart makeLineChartForSpecificFoodRevenue(RegionHistory[] regions,
                                                              EnumFood food)
  {
    final NumberAxis xAxis = new NumberAxis(1980, 2052, 3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number, Number> lineChart =
        new LineChart<Number, Number>(xAxis, yAxis);
    ArrayList<Integer> tempArrayList = new ArrayList<>();

    for (int i=0;i<26;i++)
    {
        tempArrayList.add(0);
    }

    int totalRevenue = 0;
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
            .add(new XYChart.Data(1980 + (iterateTurnNumber * 3), list));
      iterateTurnNumber++;
    }

    series.setName("My portfolio");
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
    final NumberAxis xAxis = new NumberAxis(1980, 2052, 3);
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
            .add(new XYChart.Data(1980 + (iterateTurnNumber * 3), populations));
      iterateTurnNumber++;
    }

    series.setName("My portfolio");
    lineChart.getData().add(series);
    return lineChart;
  }

  /**
   * Makes a line chart of the population
   *
   * @param region the region that we want the population from
   * @return a line chart for being displayed.
   */
  public static LineChart makeLineChartRegionRevenue(RegionHistory region)
  {
    final NumberAxis xAxis = new NumberAxis(1980, 2052, 3);
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
            .add(new XYChart.Data(1980 + (iterateTurnNumber * 3), revenues));
      iterateTurnNumber++;
    }

    series.setName("My portfolio");
    lineChart.getData().add(series);
    return lineChart;
  }
}
