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
import starvationevasion.teamrocket.PlayerInterface;
import starvationevasion.teamrocket.main.Main;
import starvationevasion.teamrocket.models.ChatHistory;

import java.util.Stack;

/**
 * Handles the chat scene and gui.
 */
public class ChatController
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

  /**
   * Used to make control the chat boxes
   */
  public ChatController()
  {
    Timeline updater = new Timeline(new KeyFrame(Duration.millis(Main.GUI_REFRESH_RATE), new EventHandler<ActionEvent>()
    {
      /**
       * handles an event if a message is inputted
       * @param event message is inputted
       */
      @Override
      public void handle(ActionEvent event)
      {
        PlayerInterface player = Main.getGameController().player;
        if (player != null)
        {
          Stack<ChatHistory.ChatMessage> history = player.getChatHistory().getMessages();

          String conversation = "";
          for (ChatHistory.ChatMessage message : history)
          {
            //Some messages are null because of the set stack size.
            if (message != null)
            {
              conversation = conversation + message.getSender() + ":" + message.getMessage() + "\n";
            }
          }
          convo.setText(conversation);
        }
      }
    }));

    updater.setCycleCount(Timeline.INDEFINITE);
    updater.play();
  }


  /**
   * Sends a message
   */
  @FXML
  public void sendMessage()
  {
    String username = Main.getGameController().getPlayerUsername();

    if (hasText)
    {
      text = "\n" + username + ": " + typeText.getCharacters().toString();
    }
    else
    {
      text = username + ": " + typeText.getCharacters().toString();
    }

    Main.getGameController().sendMessage(text.toString());
    convo.setText(text.toString());
    typeText.clear();
    hasText = true;
  }

  /**
   * Enters a message to send
   *
   * @param event Key event.
   */
  @FXML
  public void enterMessage(KeyEvent event)
  {
    if (event.getCode().equals(KeyCode.ENTER))
    {
      sendMessage();
    }

  }

}
