package starvationevasion.teamrocket.models;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.messages.ServerChatMessage;

import java.util.Date;
import java.util.Stack;

/**
 * Created by scnaegl on 11/23/15.
 */
public class ChatHistory {
  private Stack<ChatMessage> messages = new Stack<>();

  public ChatHistory() {
    this.messages = new Stack<>();
    this.messages.setSize(20);
  }

  public void addMessage(EnumRegion sender, String message) {
    messages.add(new ChatMessage(sender, message));
    messages.trimToSize();
  }

  public void addMessage(EnumRegion sender, EnumPolicy card) {
    messages.add(new ChatMessage(sender, card));
    messages.trimToSize();
  }

  public void addMessage(ServerChatMessage message) {
    messages.add(new ChatMessage(message));
    messages.trimToSize();
  }

  public class ChatMessage {
    private EnumRegion sender;
    private String message;
    private EnumPolicy card;
    private Date sent_time;

    public ChatMessage(EnumRegion sender, String message) {
      this.sender = sender;
      this.message = message;
      this.card = null;
      this.sent_time = new Date();
    }

    public ChatMessage(EnumRegion sender, EnumPolicy card) {
      this.sender = sender;
      this.message = null;
      this.card = card;
      this.sent_time = new Date();
    }

    public ChatMessage(ServerChatMessage message) {
      this.sender = message.sender;
      this.message = message.message;
      this.card = message.card;
      this.sent_time = new Date();
    }

    public EnumRegion getSender() {
      return sender;
    }

    public String getMessage() {
      return message;
    }

    public EnumPolicy getCard() {
      return card;
    }

    public Date getTime() {
      return sent_time;
    }
  }
}
