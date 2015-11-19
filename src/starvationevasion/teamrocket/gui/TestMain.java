package starvationevasion.teamrocket.gui;

import com.sun.scenario.effect.Crop;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.models.Region;

public class TestMain extends Application {

  @Override public void start(Stage stage) {
    Scene scene = new Scene(new Group());
    stage.setTitle("Imported Fruits");
    stage.setWidth(500);
    stage.setHeight(500);
   PieChart chart = CropChart.makePieChart(new Region(EnumRegion.HEARTLAND));

    ((Group) scene.getRoot()).getChildren().add(chart);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}