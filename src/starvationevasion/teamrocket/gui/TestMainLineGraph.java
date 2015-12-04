package starvationevasion.teamrocket.gui;

/**
 * Created by Tyler on 11/19/2015.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;
import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.models.Region;


public class TestMainLineGraph extends Application {


  @Override public void start(Stage stage) {

    Region regions[] = new Region[2];


    Region region = new Region(EnumRegion.HEARTLAND);
    region.addCropRevenue(EnumFood.CITRUS,22);
    region.addCropRevenue(EnumFood.CITRUS, 25);
    region.addCropRevenue(EnumFood.CITRUS, 23);
    region.addCropRevenue(EnumFood.CITRUS, 22);
    region.addCropRevenue(EnumFood.CITRUS, 25);
    region.addCropRevenue(EnumFood.CITRUS, 26);
    region.addCropRevenue(EnumFood.CITRUS, 27);
    region.addCropRevenue(EnumFood.CITRUS, 28);

    region.addCropProduced(EnumFood.CITRUS, 22);
    region.addCropProduced(EnumFood.CITRUS, 25);
    region.addCropProduced(EnumFood.CITRUS, 23);
    region.addCropProduced(EnumFood.CITRUS, 22);
    region.addCropProduced(EnumFood.CITRUS, 25);
    region.addCropProduced(EnumFood.CITRUS, 26);
    region.addCropProduced(EnumFood.CITRUS, 27);
    region.addCropProduced(EnumFood.CITRUS, 28);


    region.addPopulation(111);
    region.addPopulation(100);
    region.addPopulation(110);
    region.addPopulation(112);
    region.addPopulation(113);
    region.addPopulation(114);
    region.addPopulation(115);

    Region region1 = new Region(EnumRegion.CALIFORNIA);
    region.addCropRevenue(EnumFood.CITRUS, 22);
    region.addCropRevenue(EnumFood.CITRUS, 25);
    region.addCropRevenue(EnumFood.CITRUS, 23);
    region.addCropRevenue(EnumFood.CITRUS, 22);
    region.addCropRevenue(EnumFood.CITRUS, 25);
    region.addCropRevenue(EnumFood.CITRUS, 26);
    region.addCropRevenue(EnumFood.CITRUS, 27);
    region.addCropRevenue(EnumFood.CITRUS,28);

    regions[0]=region;
    regions[1]=region;


    stage.setTitle("Line Chart Sample");
//    LineChart<Number,Number> lineChart = CropChart.makeLineChartRegionSpecificFood(
//        region, EnumFood.CITRUS);

    LineChart<Number,Number> lineChart = CropChart.makeLineChartRegionSpecificFood(
        region, EnumFood.CITRUS);

    Scene scene  = new Scene(lineChart,800,600);

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}