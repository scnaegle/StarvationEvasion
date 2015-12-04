package starvationevasion.teamrocket.main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import starvationevasion.teamrocket.gui.MainGuiController;
import starvationevasion.teamrocket.server.GameClock;

import java.io.IOException;

public class Main extends Application
{
  private static GameController gameController;
  public static final GameClock GAME_CLOCK = new GameClock(300000l);
  /**
   * Refresh rate in milliseconds. 17 ms = 60hz
   */
  public static final short GUI_REFRESH_RATE = 17;
  private static MainGuiController guiController;
  private static boolean sceneChanged = true;

  private Stage primaryStage;
  private Stage votingStage;
  private Parent cardDraft;
  private Parent voting;
  private Parent gameRoom;
  private Parent chat;

  private Scene welcomeScene;
  private Scene regionScene;
  private Scene loginScene;
  Scene cardDraftScene;

  FXMLLoader loader;

  public static GameController getGameController()
  {
    return gameController;
  }

  public static void setGameController(GameController gameController)
  {
    Main.gameController = gameController;
  }

  public static MainGuiController getGuiController()
  {
    return guiController;
  }

  public static void setGuiController(MainGuiController guiController)
  {
    Main.guiController = guiController;
  }


  @Override
  public void start(Stage primaryStage) throws Exception
  {
    if (Main.getGameController() == null)
    {
      setGameController(new GameController(this));
    }

    this.primaryStage = primaryStage;

    Parent welcome = FXMLLoader.load(Main.class.getResource("/interface/welcomeScene.fxml"));
    Parent login = FXMLLoader.load(Main.class.getResource("/interface/loginScene.fxml"));
    Parent chooseRegion = FXMLLoader.load(Main.class.getResource("/interface/chooseRegionScene.fxml"));

//    cardDraft = FXMLLoader.load(Main.class.getResource("/interface/cardDraft.fxml"));

    welcomeScene = new Scene(welcome);
    loginScene = new Scene(login);
    regionScene = new Scene(chooseRegion);


    Group root = new Group();

    Media video = new Media(Main.class.getResource("/images/animation.mp4").toString());
    MediaPlayer videoPlayer = new MediaPlayer(video);
    MediaView viewer = new MediaView(videoPlayer);

    primaryStage.setTitle("Starvation Evasion");


    root.getChildren().add(viewer);
    Scene startAnimation = new Scene(root, 1280, 720, Color.BLACK);
    primaryStage.setScene(startAnimation);
    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
    {
      @Override
      public void handle(KeyEvent key)
      {
        if(key.getCode() == KeyCode.SPACE)
        {
          switchScenes(1);
        }
      }
    });
    primaryStage.show();
    videoPlayer.play();

   // primaryStage.setScene(cardDraftScene);
   // primaryStage.show();
  }

  /**
   * switches between scenes.
   *
   * @param scene the number that the scene is on
   */
  public void switchScenes(int scene)
  {
    if(scene == 1)
    {
      primaryStage.setScene(welcomeScene);
    }
    else if (scene == 2)
    {
      primaryStage.setScene(regionScene);
    }
    else if (scene == 3)
    {
      try
      {
       cardDraft = FXMLLoader.load(Main.class.getResource("/interface/cardDraft.fxml"));
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      cardDraftScene = new Scene(cardDraft);
      primaryStage.setScene(cardDraftScene);
    }
    else if (scene == 4)
    {
      try
      {
        voting = FXMLLoader.load(Main.class.getResource("/interface/voting.fxml"));
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      Scene votingScene = new Scene(voting);
      primaryStage.setScene(votingScene);
    }
    else if (scene == 5)
    {
      primaryStage.setScene(loginScene);
    }
    else if (scene == 6)
    {
      try
      {
        gameRoom = FXMLLoader.load(Main.class.getResource("/interface/gameRoom.fxml"));
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      Scene gameRoomScene = new Scene(gameRoom);
      primaryStage.setScene(gameRoomScene);
      placeWindowonScreen(primaryStage, 1350, 1000);
    }
  }

  /**
   * opens the chat bar for players to use
   */
  public void openChat()
  {
    Stage chatStage = new Stage();
    try
    {
      chat = FXMLLoader.load(Main.class.getResource("/interface/chat.fxml"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    Scene chatScene = new Scene(chat);
    chatStage.setTitle("Chat here!");
    placeWindowonScreen(chatStage, 320, 1000);
    chatStage.setScene(chatScene);
    chatStage.show();
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

  public static void main(String[] args)
  {
    launch(args);
  }

  public static boolean justSwitchedScenes()
  {
    return sceneChanged;
  }
}
