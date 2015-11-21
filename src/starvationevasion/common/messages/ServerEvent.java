package starvationevasion.common.messages;

import starvationevasion.common.PolicyCard;
import starvationevasion.server.Stopwatch;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by scnaegl on 11/19/15.
 */
public enum ServerEvent {
  LOGIN,
  SELECT_REGION,
  START_GAME,
  TIMER,
  SIM_STATS,
  CARDS_CHOSEN,
  READY,
  GAME_STAGE,
  VOTE,
  DRAW,
  CHAT,
  SHOW_CARD;

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

}
