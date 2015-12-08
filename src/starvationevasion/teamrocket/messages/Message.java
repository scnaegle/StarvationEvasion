package starvationevasion.teamrocket.messages;

import java.io.Serializable;

/**
 * Created by scnaegl on 11/20/15.
 */
public class Message implements Serializable {
  private ServerEvent server_event;
  private Serializable payload;

  public Message(ServerEvent server_event, Serializable payload) {
    this.server_event = server_event;
    this.payload = payload;
  }


  public ServerEvent getServerEvent() {
    return server_event;
  }

  public void setServerEvent(ServerEvent server_event) {
    this.server_event = server_event;
  }

  public Object getPayload() {
    return payload;
  }

  public void setPayload(Serializable payload) {
    this.payload = payload;
  }
}
