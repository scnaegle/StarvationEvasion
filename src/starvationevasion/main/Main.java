package starvationevasion.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception{
    //System.out.println("resouce: " + getClass().getClassLoader().getResource("assets/interface/scene1.fxml"));
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("assets/interface/scene1.fxml"));
    primaryStage.setTitle("Starvation Evasion");
    primaryStage.setScene(new Scene(root, 1024, 768));

    primaryStage.show();
  }


  public static void main(String[] args) {
        launch(args);
    }
}
