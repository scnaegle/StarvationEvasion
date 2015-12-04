package starvationevasion.teamrocket.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
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

  public ChatController()
  {
    Timeline updater = new Timeline(new KeyFrame(Duration.millis(Main.GUI_REFRESH_RATE), new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
        //Get new messages.

        if(Main.GAME_CLOCK.getTimeLeft() <=0)
        {
          //Main.getGameController().finishedCardDraft();
        }
      }
    }));

    updater.setCycleCount(Timeline.INDEFINITE);
    updater.play();
  }

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
