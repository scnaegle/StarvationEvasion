package starvationevasion.teamrocket.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import starvationevasion.common.EnumFood;
import starvationevasion.teamrocket.models.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * This class is used to make the charts such as the pie chart and
 * line graphs.
 */
public class CropChart
{
  /**
   * Makes a pie chart to add to the GUI of a region and what it is producing for
   * it's crops this turn.
   *
   * @param region the region that we are making the pie chart for.
   * @return Returns a pie chart to add to the gui.
   */
  public static PieChart makeRegionFoodPieChart(Region region)
  {
    HashMap<EnumFood, Integer> data = region.getLastCropProducedData();
    ArrayList<PieChart.Data> dataList = new ArrayList<>();

    for(EnumFood food : EnumFood.values())
    {
      dataList.add(new PieChart.Data(food.name(), data.get(food)));
    }
    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(dataList);
    PieChart chart = new PieChart(pieChartData);

    chart.setTitle("Crops");
    chart.setLegendSide(Side.BOTTOM);
    chart.setVisible(true);
    return chart;
  }



  public static PieChart makeOneRegionRevenuePieChart(Region region)
  {
    HashMap<EnumFood, Integer> data = region.getLastCropRevenue();
    ArrayList<PieChart.Data> dataList = new ArrayList<>();

    for(EnumFood food : EnumFood.values())
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


  public static PieChart makeHDIPieChart(Region region)
  {
    HashMap<EnumFood, Integer> data = region.getLastCropProducedData();

    ArrayList<PieChart.Data> dataList = new ArrayList<>();

    dataList.add(new PieChart.Data("Malnurished", 1-region.getLastHDI()));
    dataList.add(new PieChart.Data("Nurished", region.getLastHDI()));

    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(dataList);
    PieChart chart = new PieChart(pieChartData);

    chart.setTitle("HDI");
    chart.setLegendSide(Side.BOTTOM);
    chart.setVisible(true);
    return chart;
  }



  public static PieChart makeTotalRevenueAllRegionPieChart(Region[] region)
  {
    ArrayList<PieChart.Data> dataList = new ArrayList<>();

    for(Region regions : region)
    {
      dataList.add(new PieChart.Data(regions.getEnumRegion().name(), regions.getLastTotalRevenue()));
    }
    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(dataList);
    PieChart chart = new PieChart(pieChartData);

    chart.setTitle("Revenue");
    chart.setLegendSide(Side.BOTTOM);
    chart.setVisible(true);
    return chart;
  }


  public static PieChart makePopulationPieChart(Region[] region)
  {

    ArrayList<PieChart.Data> dataList = new ArrayList<>();


    for(Region regions : region)
    {
      dataList.add(new PieChart.Data(regions.getEnumRegion().name(), regions.getLastPopulation()));
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
   * Makes a line chart of the data from our crops and regions, which spans the entire
   * turns
   *
   * @param region the region of the food
   * @param food the type of food we are graphinf
   * @return a line chart for being displayed.
   */
  public static LineChart makeLineChartRegionSpecificFood(Region region,
                                                          EnumFood food)
  {
    final NumberAxis xAxis = new NumberAxis(1980,2052,3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number,Number> lineChart =
        new LineChart<Number,Number>(xAxis,yAxis);

    HashMap<EnumFood, Stack<Integer>> data = region.getCropValues();

    ArrayList<XYChart.Series> dataList = new ArrayList<>();

    XYChart.Series series = new XYChart.Series();


    int iterateTurnNumber = 0;
    int actualTurnNumber = 5;
    for(double stackData : data.get(food))
    {
        series.getData().add(new XYChart.Data(1980+(iterateTurnNumber*3), stackData));
        iterateTurnNumber++;
    }

    series.setName("My portfolio");
    lineChart.getData().add(series);
    return lineChart;
  }

  /**
   * Makes a line chart of the data from our crops and regions, which spans the entire
   * turns
   *
   * @param regions the region of the food
   * @param food the type of food we are graphinf
   * @return a line chart for being displayed.
   */
  public static LineChart makeLineChartForSpecificFoodRevenue(Region[] regions,
                                                          EnumFood food)
  {
    final NumberAxis xAxis = new NumberAxis(1980,2052,3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number,Number> lineChart =
        new LineChart<Number,Number>(xAxis,yAxis);
    ArrayList<Integer> tempArrayList = new ArrayList<>();

    int totalRevenue = 0;

    for(Region region: regions)
    {
      int i=0;
      int tempVarible = 0;
      ArrayList<Integer> data = region.getCropRevenue(food);
      for(int values: data)
      {
        if(tempArrayList.get(i)==null)
        {
          tempArrayList.add(values);
          i++;
        }
        else
        {
          tempVarible = tempArrayList.get(i);
          tempArrayList.add(i, values+tempVarible);
          i++;
        }
      }

    }
    ArrayList<XYChart.Series> dataList = new ArrayList<>();

    XYChart.Series series = new XYChart.Series();


    int iterateTurnNumber = 0;
    int actualTurnNumber = 5;
    for(int list : tempArrayList)
    {
      series.getData().add(new XYChart.Data(1980+(iterateTurnNumber*3), list));
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
  public static LineChart makeLineChartRegionPopulation(Region region)
  {
    final NumberAxis xAxis = new NumberAxis(1980,2052,3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number,Number> lineChart =
        new LineChart<Number,Number>(xAxis,yAxis);


    ArrayList<Integer> population = region.getPopulation();

    ArrayList<XYChart.Series> dataList = new ArrayList<>();

    XYChart.Series series = new XYChart.Series();


    int iterateTurnNumber = 0;
    for(Integer populations : population)
    {
      series.getData().add(new XYChart.Data(1980+(iterateTurnNumber*3), populations));
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
  public static LineChart makeLineChartRegionRevenue(Region region)
  {
    final NumberAxis xAxis = new NumberAxis(1980,2052,3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number,Number> lineChart =
        new LineChart<Number,Number>(xAxis,yAxis);


    ArrayList<Integer> revenue = region.getTotalRevenue();

    XYChart.Series series = new XYChart.Series();

    int iterateTurnNumber = 0;
    for(Integer revenues : revenue)
    {
      series.getData().add(new XYChart.Data(1980+(iterateTurnNumber*3), revenues));
      iterateTurnNumber++;
    }

    series.setName("My portfolio");
    lineChart.getData().add(series);
    return lineChart;
  }
}
