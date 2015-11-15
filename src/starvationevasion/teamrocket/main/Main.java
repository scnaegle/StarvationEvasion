package starvationevasion.teamrocket.main;

import javafx.application.Application;
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
    main1 = FXMLLoader.load(Main.class.getResource("/interface/main1.fxml"));
    chooseRegion = FXMLLoader.load(Main.class.getResource("/interface/chooseRegionScene.fxml"));
    nextStage.setTitle("Starvasion Evasion");

    if(scene == 2)
    {
      nextStage.setScene(new Scene(chooseRegion));
    }
    if(scene == 3){
      nextStage.setScene(new Scene(main1));
    }
    nextStage.show();

  }

  public static void main(String[] args) {
        launch(args);
    }
}
