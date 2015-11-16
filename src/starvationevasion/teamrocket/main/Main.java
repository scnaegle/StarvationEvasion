package starvationevasion.teamrocket.main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import starvationevasion.teamrocket.gui.GuiController;

public class Main extends Application {

  Stage primaryStage;
  Parent welcomeScene;
  Parent chooseRegion;
  Parent main1;
  Parent main2;

  @Override
  public void start(Stage primaryStage) throws Exception{
    this.primaryStage = primaryStage;
    welcomeScene = FXMLLoader.load(Main.class.getResource("/interface/welcomeScene.fxml"));

    primaryStage.setTitle("Starvation Evasion");
    primaryStage.setScene(new Scene(welcomeScene));
    primaryStage.show();

  }

  public void switchScenes(int scene) throws Exception{
    Stage nextStage = new Stage();
    chooseRegion = FXMLLoader.load(Main.class.getResource("/interface/chooseRegionScene.fxml"));
    main1 = FXMLLoader.load(Main.class.getResource("/interface/main1.fxml"));
    main2 = FXMLLoader.load(Main.class.getResource("/interface/main2.fxml"));

    nextStage.setTitle("Starvasion Evasion");

    if(scene == 2)
    {
      nextStage.setScene(new Scene(chooseRegion));
    }
    else if(scene == 3){
      nextStage.setScene(new Scene(main1));
    }
    else if(scene == 4){
      nextStage.setScene(new Scene(main2));
    }
    nextStage.show();

  }

  public static void main(String[] args) {
        launch(args);
    }
}
