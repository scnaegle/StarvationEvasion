package starvationevasion.teamrocket.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception{
    //System.out.println("resouce: " + getClass().getClassLoader().getResource("assets/interface/main1.fxml"));
    Parent main1 = FXMLLoader.load(getClass().getClassLoader().getResource(
        "assets/interface/main1.fxml"));

    Parent welcomeScene = FXMLLoader.load(getClass().getClassLoader().getResource(
        "assets/interface/welcomeScene.fxml"));

    Parent chooseRegion = FXMLLoader.load(
        getClass().getClassLoader().getResource(
            "assets/interface/chooseRegionScene.fxml"));
    primaryStage.setTitle("Starvation Evasion");
    primaryStage.setScene(new Scene(chooseRegion, 1024, 768));




    primaryStage.show();
  }


  public static void main(String[] args) {
        launch(args);
    }
}
