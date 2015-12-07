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
import starvationevasion.teamrocket.models.RegionHistory;


public class TestMainLineGraph extends Application {


  @Override public void start(Stage stage) {

    RegionHistory regions[] = new RegionHistory[2];


    RegionHistory region = new RegionHistory(EnumRegion.HEARTLAND);
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

    region.addCropProduced(EnumFood.FRUIT,21);
    region.addCropProduced(EnumFood.FRUIT,22);
    region.addCropProduced(EnumFood.FRUIT,19);
    region.addCropProduced(EnumFood.FRUIT, 14);
    region.addCropProduced(EnumFood.FRUIT, 24);
    region.addCropProduced(EnumFood.FRUIT, 26);
    region.addCropProduced(EnumFood.FRUIT, 28);
    region.addCropProduced(EnumFood.FRUIT, 22);
    region.addCropProduced(EnumFood.FRUIT, 21);

    region.addPopulation(111);
    region.addPopulation(100);
    region.addPopulation(110);
    region.addPopulation(112);
    region.addPopulation(113);
    region.addPopulation(114);
    region.addPopulation(115);

    RegionHistory region1 = new RegionHistory(EnumRegion.CALIFORNIA);
    region.addCropRevenue(EnumFood.CITRUS, 22);
    region.addCropRevenue(EnumFood.CITRUS, 25);
    region.addCropRevenue(EnumFood.CITRUS, 23);
    region.addCropRevenue(EnumFood.CITRUS, 22);
    region.addCropRevenue(EnumFood.CITRUS, 25);
    region.addCropRevenue(EnumFood.CITRUS, 26);
    region.addCropRevenue(EnumFood.CITRUS, 27);
    region.addCropRevenue(EnumFood.CITRUS, 28);

    region.addCropProduced(EnumFood.GRAIN, 0);

    region.addCropProduced(EnumFood.FEED,0);

    region.addCropProduced(EnumFood.DAIRY,0);

    region.addCropProduced(EnumFood.FISH,0);

    region.addCropProduced(EnumFood.MEAT,0);
    region.addCropProduced(EnumFood.NUT,0);
    region.addCropProduced(EnumFood.OIL,0);
    region.addCropProduced(EnumFood.POULTRY,0);
    region.addCropProduced(EnumFood.VEGGIES,0);

    region.addCropProduced(EnumFood.SPECIAL,0);

   

    regions[0]=region;
    regions[1]=region;


    boolean foods[] = {true,true,true,true,true,true,true,true,true,true,true,true};

    stage.setTitle("Line Chart Sample");
//    LineChart<Number,Number> lineChart = CropChart.makeLineChartRegionSpecificFood(
//        region, EnumFood.CITRUS);

    LineChart<Number,Number> lineChart = CropChart.makeLineChartRegionFood(
        region, foods);

    Scene scene  = new Scene(lineChart,800,600);

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}