package starvationevasion.teamrocket.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  public static GameController gameController;

  Stage primaryStage;
  Parent welcomeScene;
  Parent chooseRegion;
  Parent cardDraft;
  Parent voting;


  @Override
  public void start(Stage primaryStage) throws Exception{
    if(Main.gameController == null)
    {
      gameController = new GameController(this);
    }
    this.primaryStage = primaryStage;
    welcomeScene = FXMLLoader.load(Main.class.getResource("/interface/welcomeScene.fxml"));

    chooseRegion = FXMLLoader.load(Main.class.getResource("/interface/chooseRegionScene.fxml"));
    cardDraft = FXMLLoader.load(Main.class.getResource("/interface/cardDraft.fxml"));
    voting = FXMLLoader.load(Main.class.getResource("/interface/voting.fxml"));

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
      primaryStage.setScene(new Scene(cardDraft));

    }
    else if(scene == 4){
      primaryStage.setScene(new Scene(voting));
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
