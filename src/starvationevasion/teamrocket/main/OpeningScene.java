package starvationevasion.teamrocket.main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//import javax.print.attribute.standard.Media;

/**
 * Created by Tyler on 12/2/2015.
 */
public class OpeningScene extends Application
{
  public static void main(String[] args){
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception{

    Group root = new Group();

    Media video = new Media(OpeningScene.class.getResource("/images/Try5.mp4").toString());
    MediaPlayer videoPlayer = new MediaPlayer(video);
    MediaView viewer = new MediaView(videoPlayer);

    root.getChildren().add(viewer);
    Scene startAnimation = new Scene(root, 1280, 720, Color.BLACK);
    stage.setScene(startAnimation);
    stage.show();
    videoPlayer.play();
  }
}
