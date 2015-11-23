package starvationevasion.teamrocket.models;

import java.util.Date;
import java.util.Stack;

/**
 * Created by scnaegl on 11/23/15.
 */
public class ChatHistory {
  private Stack<ChatMessage> messages = new Stack<>();

  public ChatHistory() {
    this.messages = new Stack<>();
  }

  public void addMessage(Player player, String message) {
    messages.add(new ChatMessage(player, message));
  }


  public class ChatMessage {
    private Player player;
    private String message;
    private Date sent_time;

    public ChatMessage(Player player, String message) {
      this.player = player;
      this.message = message;
      this.sent_time = new Date();
    }

    public Player getPlayer() {
      return player;
    }

    public String getMessage() {
      return message;
    }

    public Date getTime() {
      return sent_time;
    }
  }
}
