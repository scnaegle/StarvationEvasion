package starvationevasion.teamrocket.server;

import starvationevasion.common.PolicyCard;
import starvationevasion.common.messages.Login;
import starvationevasion.teamrocket.messages.Message;
import starvationevasion.common.messages.RegionChoice;
import starvationevasion.common.messages.Response;
import starvationevasion.teamrocket.server.Stopwatch;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by scnaegl on 11/21/15.
 */
public class MessageHandler {
  public static void send(ObjectOutputStream outputStream, Message message) {
    try {
      outputStream.writeObject(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T parse(ObjectInputStream input) {
    Message message;
    try {
      message = (Message)input.readObject();
      switch(message.getServerEvent()) {
        case LOGIN:
          return (T) (Login)message.getPayload();
        case SELECT_REGION:
          return (T) (RegionChoice)message.getPayload();
        case START_GAME:
          return (T) (String)message.getPayload();
        case TIMER:
          return (T) (Stopwatch)message.getPayload();
        case SIM_STATS:
        case CARDS_CHOSEN:
          return (T) (ArrayList<PolicyCard>)message.getPayload();
        case READY:
        case VOTE:
        case DRAW:
        case CHAT:
        default:
          return (T) Response.BAD_MESSAGE;

      }
    } catch (IOException e) {
      e.printStackTrace();
      return (T) Response.BAD_MESSAGE;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      return (T) Response.BAD_MESSAGE;
    }
  }

  private void parseChatMessage(String message) {

  }
}
