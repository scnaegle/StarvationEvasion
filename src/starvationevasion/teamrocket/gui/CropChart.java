package starvationevasion.teamrocket.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.models.Region;

/**
 *
 */
public class CropChart
{

  public static PieChart makePieChart(Region region)
  {
    PieChart pieChart;
//    pieChart = getPieChartData(region.getEnumRegion());
    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(
            new PieChart.Data("Grapefruit", 13),
            new PieChart.Data("Oranges", 25),
            new PieChart.Data("Plums", 10),
            new PieChart.Data("Pears", 22),
            new PieChart.Data("Apples", 30));
    PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Crops");
    chart.setLegendSide(Side.RIGHT);
    return chart;
  }


  public static LineChart makeLineChart(Region region, EnumFood food)
  {

    return null;
  }
}
