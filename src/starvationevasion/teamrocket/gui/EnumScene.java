package starvationevasion.teamrocket.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * This keeps track of scenes and which scene is expected next.
 */
public enum EnumScene
{

  CHAT("/interface/chat.fxml")
      {
      },
  DRAFT_PHASE("/interface/cardDraft.fxml")
      {
      },
  GAME_ROOM("/interface/gameRoom.fxml")
      {
      },
  LOGIN("/interface/loginScene.fxml")
      {
      },
  REGION_CHOICE("/interface/chooseRegionScene.fxml")
      {
      },
  VOTE_PHASE("/interface/voting.fxml")
      {
      },
  WELCOME("/interface/welcomeScene.fxml")
      {
      };
  private Scene scene;

  EnumScene(String sceneFXML)
  {
    Scene tempScene = null;
    try
    {
      Parent parent = FXMLLoader.load(EnumScene.class.getResource(sceneFXML));
      tempScene = new Scene(parent);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    scene = tempScene;
  }



  public Scene getScene()
  {
    return scene;
  }
}
