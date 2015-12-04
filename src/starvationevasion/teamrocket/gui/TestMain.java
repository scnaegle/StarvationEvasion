package starvationevasion.teamrocket.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.models.RegionHistory;

public class TestMain extends Application {

  @Override public void start(Stage stage) {
    Scene scene = new Scene(new Group());
    stage.setTitle("Imported Fruits");
    stage.setWidth(500);
    stage.setHeight(500);
   PieChart chart = CropChart.makeRegionFoodPieChart(
       new RegionHistory(EnumRegion.HEARTLAND));

    ((Group) scene.getRoot()).getChildren().add(chart);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}