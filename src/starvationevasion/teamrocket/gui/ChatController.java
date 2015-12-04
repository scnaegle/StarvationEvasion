package starvationevasion.teamrocket.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import starvationevasion.teamrocket.main.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Handles the chat scene and gui.
 */
public class ChatController implements javafx.fxml.Initializable
{
  /* CHAT STUFF */
  @FXML
  private Button send;
  @FXML
  private TextField typeText;
  @FXML
  private TextArea convo;

  private CharSequence text;
  private boolean hasText = false;
  /**************************/

  @Override
  public void initialize(URL location, ResourceBundle resources)
  {

  }

  @FXML
  public void sendMessage()
  {
    String username = Main.getGameController().getPlayerUsername();

    if(hasText)
    {
      text = text.toString()+"\n" + username+": " + typeText.getCharacters().toString();
    }
    else text = username+": " + typeText.getCharacters().toString();

    convo.setText(text.toString());
    typeText.clear();
    hasText = true;
  }
  @FXML
  public void enterMessage(KeyEvent event)
  {
    if(event.getCode().equals(KeyCode.ENTER))
    {
      sendMessage();
    }

  }

}
