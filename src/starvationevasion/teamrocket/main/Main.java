package starvationevasion.teamrocket.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
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
  Parent chat;

  Scene welcomeScene;
  Scene regionScene;
  Scene cardDraftScene;
  Scene votingScene;
  Scene loginScene;
  Scene gameRoomScene;
  Scene chatScene;



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
    chat = FXMLLoader.load(Main.class.getResource("/interface/chat.fxml"));


    welcomeScene = new Scene(welcome);
    loginScene = new Scene(login);
    regionScene = new Scene(chooseRegion);
    cardDraftScene = new Scene(cardDraft);
    votingScene = new Scene(voting);
    gameRoomScene = new Scene(gameRoom);
    chatScene = new Scene(chat);



    Group root = new Group();

    Media video = new Media(Main.class.getResource("/images/Try5.mp4").toString());
    MediaPlayer videoPlayer = new MediaPlayer(video);
    MediaView viewer = new MediaView(videoPlayer);

    primaryStage.setTitle("Starvation Evasion");

    root.getChildren().add(viewer);
    Scene startAnimation = new Scene(root, 1280, 720, Color.BLACK);
    primaryStage.setScene(startAnimation);
    primaryStage.show();
    videoPlayer.play();



//    primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, new EventHandler<WindowEvent>()
//    {
//      @Override
//      public void handle(WindowEvent window)
//      {
//        System.out.println("window has been shown");
////        controller.handleWindowShownEvent();
//        System.out.println("controller: " + controller);
//        //controller.showMyRegion();
//        System.out.println("done with event...");
//      }
//    });

    primaryStage.show();
    //if(primaryStage.isShowing()) System.out.println("window is showing");


  }

  /**
   * switches between scenes.
   * @param scene the number that the scene is on
   */
  public void switchScenes(int scene)
  {
    if (scene == 2)
    {
      primaryStage.setScene(regionScene);
//      primaryStage.fireEvent(new WindowEvent(regionScene.getWindow(), WindowEvent.WINDOW_SHOWN));
    }
    else if (scene == 3)
    {
      primaryStage.setScene(cardDraftScene);

//      primaryStage.fireEvent(new WindowEvent(regionScene.getWindow(), WindowEvent.WINDOW_SHOWN));
//      controller.showMyRegion();

    }
    else if (scene == 4)
    {
      primaryStage.setScene(votingScene);

    }
    else if (scene == 5)
    {
      primaryStage.setScene(loginScene);

    }
    else if (scene == 6)
    {
      primaryStage.setScene(gameRoomScene);
      placeWindowonScreen(primaryStage, 1350, 1000);
    }

  }

  /**
   * opens the chat bar for players to use
   */
  public void openChat()
  {
    Stage secondStage = new Stage();
    secondStage.setTitle("Chat here!");
    placeWindowonScreen(secondStage,320,1000);
    secondStage.setScene(chatScene);
    secondStage.show();
  }

  private void placeWindowonScreen(Stage stage, int x, int y)
  {
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    stage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - x);
    stage.setY(primaryScreenBounds.getMinY());
  }
  public Stage getCurrentStage()
  {
    return primaryStage;
  }

  public static void main(String[] args) {

    launch(args);
    }
}
