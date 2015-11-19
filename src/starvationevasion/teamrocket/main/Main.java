package starvationevasion.teamrocket.main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import starvationevasion.teamrocket.gui.GuiController;

public class Main extends Application {
  public static GameController gameController;

  Stage primaryStage;
  Parent welcomeScene;
  Parent chooseRegion;
  Parent main1;
  Scene cardDraft;
  Parent main2;
  Scene voting;


  @Override
  public void start(Stage primaryStage) throws Exception{
    if(Main.gameController == null)
    {
      gameController = new GameController(this);
    }
    this.primaryStage = primaryStage;
    welcomeScene = FXMLLoader.load(Main.class.getResource("/interface/welcomeScene.fxml"));

    chooseRegion = FXMLLoader.load(Main.class.getResource("/interface/chooseRegionScene.fxml"));
    main1 = FXMLLoader.load(Main.class.getResource("/interface/main1.fxml"));
    main2 = FXMLLoader.load(Main.class.getResource("/interface/main2.fxml"));

    cardDraft = new Scene(main1);
    voting = new Scene(main2);

    primaryStage.setTitle("Starvation Evasion");
    primaryStage.setScene(new Scene(welcomeScene));
    primaryStage.show();
  }

  void switchScenes(int scene)
  {
    if(scene == 2)
    {
      primaryStage.setScene(new Scene(chooseRegion));
    }
    else if(scene == 3){
      primaryStage.setScene(cardDraft);
    }
    else if(scene == 4){
      primaryStage.setScene(voting);
    }
  }
  public Stage getCurrentStage()
  {
    return primaryStage;
  }

  public static void main(String[] args) {
        launch(args);
    }
}
