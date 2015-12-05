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
import starvationevasion.teamrocket.gui.EnumScene;
import starvationevasion.teamrocket.server.GameClock;

import java.io.IOException;

public class Main extends Application
{
  private static GameController gameController;
  public static final GameClock GAME_CLOCK = new GameClock(30000);
  /**
   * Refresh rate in milliseconds. 17 ms = 60hz
   */
  public static final short GUI_REFRESH_RATE = 17;
  private static boolean sceneChanged = true;

  private Stage primaryStage;

  public static GameController getGameController()
  {
    return gameController;
  }

  public static void setGameController(GameController gameController)
  {
    Main.gameController = gameController;
  }

  @Override
  public void start(Stage primaryStage) throws Exception
  {
    if (Main.getGameController() == null)
    {
      setGameController(new GameController(this));
    }

    this.primaryStage = primaryStage;
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
          gameController.changeScene(EnumScene.WELCOME);
        }
      }
    });
    primaryStage.show();
    videoPlayer.play();

   // primaryStage.setScene(cardDraftScene);
   // primaryStage.show();
  }

  /**
   * Sets the current scene.
   *
   * @param nextScene the enum that the scene is bound to
   */
  public void setScene(EnumScene nextScene)
  {
    sceneChanged = true;
    if (nextScene == EnumScene.GAME_ROOM)
    {
      placeWindowonScreen(primaryStage, 1350, 1000);
    }
    primaryStage.setScene(nextScene.getScene());
  }

  /**
   * Opens the chat bar for players to use
   */
  public void openChat()
  {
    Stage chatStage = new Stage();
    chatStage.setTitle("Chat here!");
    placeWindowonScreen(chatStage, 320, 1000);
    chatStage.setScene(EnumScene.CHAT.getScene());
    chatStage.show();
  }

  private void placeWindowonScreen(Stage stage, int x, int y)
  {
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    stage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - x);
    stage.setY(primaryScreenBounds.getMinY());
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
