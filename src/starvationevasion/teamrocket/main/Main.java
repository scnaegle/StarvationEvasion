package starvationevasion.teamrocket.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import starvationevasion.teamrocket.gui.GuiController;

public class Main extends Application {
  public static GameController gameController;
  public GuiController gui;

  Stage primaryStage;
  Parent welcome;
  Parent chooseRegion;
  Parent cardDraft;
  Parent voting;

  Scene welcomeScene;
  Scene regionScene;
  Scene cardDraftScene;
  Scene votingScene;


  @Override
  public void start(Stage primaryStage) throws Exception{
    if(Main.gameController == null)
    {
      gameController = new GameController(this);
    }
    this.primaryStage = primaryStage;
    welcome = FXMLLoader.load(Main.class.getResource("/interface/welcomeScene.fxml"));

    chooseRegion = FXMLLoader.load(Main.class.getResource("/interface/chooseRegionScene.fxml"));
    cardDraft = FXMLLoader.load(Main.class.getResource("/interface/cardDraft.fxml"));
    voting = FXMLLoader.load(Main.class.getResource("/interface/voting.fxml"));

    welcomeScene = new Scene(welcome);
    regionScene = new Scene(chooseRegion);
    cardDraftScene = new Scene(cardDraft);
    votingScene = new Scene(voting);

    primaryStage.setTitle("Starvation Evasion");
    primaryStage.setScene(welcomeScene);
    primaryStage.show();


  }

  void switchScenes(int scene)
  {
    if(scene == 2)
    {
      primaryStage.setScene(regionScene);
    }
    else if(scene == 3){
      primaryStage.setScene(cardDraftScene);
    }
    else if(scene == 4){
      primaryStage.setScene(votingScene);
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
