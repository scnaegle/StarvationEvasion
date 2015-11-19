package starvationevasion.teamrocket.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.models.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 */
public class CropChart
{

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
//    chart.
    chart.setTitle("Crops");
    chart.setLegendSide(Side.RIGHT);
    return chart;
  }


  public static LineChart makeLineChart(Region region, EnumFood food)
  {

    return null;
  }
}
