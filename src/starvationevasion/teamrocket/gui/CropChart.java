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
  public static LineChart makeLineChart(Region region, EnumFood food)
  {
    final NumberAxis xAxis = new NumberAxis(1980,2055,3);
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Year");
    //creating the chart
    LineChart<Number,Number> lineChart =
        new LineChart<Number,Number>(xAxis,yAxis);

    return null;
  }
}
