package starvationevasion.teamrocket.gui;

/**
 * Created by Tyler on 11/19/2015.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.models.Region;


public class TestMainLineGraph extends Application {

  @Override public void start(Stage stage) {
    stage.setTitle("Line Chart Sample");
    LineChart<Number,Number> lineChart = CropChart.makeLineChart(new Region(EnumRegion.HEARTLAND), EnumFood.CITRUS);
    Scene scene  = new Scene(lineChart,800,600);

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}