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
  Parent login;
  Parent gameRoom;


  Scene welcomeScene;
  Scene regionScene;
  Scene cardDraftScene;
  Scene votingScene;
  Scene loginScene;
  Scene gameRoomScene;


  @Override
  public void start(Stage primaryStage) throws Exception{
    if(Main.gameController == null)
    {
      gameController = new GameController(this);
    }
    this.primaryStage = primaryStage;
    welcome = FXMLLoader.load(Main.class.getResource("/interface/welcomeScene.fxml"));
    login = FXMLLoader.load(Main.class.getResource("/interface/loginScene.fxml"));
    chooseRegion = FXMLLoader.load(Main.class.getResource("/interface/chooseRegionScene.fxml"));
    cardDraft = FXMLLoader.load(Main.class.getResource("/interface/cardDraft.fxml"));
    voting = FXMLLoader.load(Main.class.getResource("/interface/voting.fxml"));
    gameRoom = FXMLLoader.load(Main.class.getResource("/interface/gameRoom.fxml"));


    welcomeScene = new Scene(welcome);
    loginScene = new Scene(login);
    regionScene = new Scene(chooseRegion);
    cardDraftScene = new Scene(cardDraft);
    votingScene = new Scene(voting);
    gameRoomScene = new Scene(gameRoom);

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
    else if(scene == 5){
      primaryStage.setScene(loginScene);
    }
    else if(scene == 6){
      primaryStage.setScene(gameRoomScene);
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
