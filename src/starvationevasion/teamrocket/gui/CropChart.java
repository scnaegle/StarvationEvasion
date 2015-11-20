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
import starvationevasion.teamrocket.models.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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
  public static PieChart makePieChart(Region region)
  {
    PieChart pieChart;
//    pieChart = getPieChartData(region.getEnumRegion());
    HashMap<EnumFood, Double> data = region.getLastCropData();

    ArrayList<PieChart.Data> dataList = new ArrayList<>();

    for(EnumFood food : EnumFood.values())
    {
      dataList.add(new PieChart.Data(food.name(), data.get(food)));
    }
    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(dataList);
    PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Crops");
    chart.setLegendSide(Side.RIGHT);
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
  public static LineChart makeLineChart(Region region, EnumFood food)
  {
    final NumberAxis xAxis = new NumberAxis(1980,2055,3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number,Number> lineChart =
        new LineChart<Number,Number>(xAxis,yAxis);

    lineChart.setTitle(food.name()+" produced for " +region.ENUM_REGION.name());

    //defining a series
    XYChart.Series series = new XYChart.Series();
    series.setName(food.name()+ " produced");
    //populating the series with data
    series.getData().add(new XYChart.Data(1980, 23));
    series.getData().add(new XYChart.Data(1983, 14));
//    series.getData().add(new XYChart.Data(3, 15));
//    series.getData().add(new XYChart.Data(4, 24));
//    series.getData().add(new XYChart.Data(5, 34));
//    series.getData().add(new XYChart.Data(6, 36));
//    series.getData().add(new XYChart.Data(7, 22));
//    series.getData().add(new XYChart.Data(8, 45));
//    series.getData().add(new XYChart.Data(9, 43));
//    series.getData().add(new XYChart.Data(10, 17));
//    series.getData().add(new XYChart.Data(11, 29));
//    series.getData().add(new XYChart.Data(12, 25));
    lineChart.getData().add(series);
    xAxis.autosize();
    lineChart.autosize();
//    lineChart.

    return lineChart;
  }
}
