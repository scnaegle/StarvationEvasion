package starvationevasion.teamrocket.AI;

import starvationevasion.common.messages.ClientChatMessage;
import starvationevasion.common.messages.ServerChatMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by zfalgout on 12/6/15.
 */
public class AIChatResponse
{
  private ArrayList<String> unknownMessageResponses = new ArrayList<String>();
  private String[] coopKeywords = {"coop", "cooperate", "co-operate", "join", "together", "team"};
  private PlayerRecord[] records;
  private ServerChatMessage message;

  public AIChatResponse(PlayerRecord[] records)
  {
    this.records = records;
    loadResponses();
  }

  /**
   * Loads in the ResponseList file
   */
  private void loadResponses()
  {
    String line;
    BufferedReader txtReader = new BufferedReader(
            new InputStreamReader(getClass().getResourceAsStream("ResponseList")));
    try {
      while((line = txtReader.readLine()) != null)
      {
        unknownMessageResponses.add(line);
      }
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Get the message from another player
   * @param message ServerChatMessage from other player
   */
  public void getMessage(ServerChatMessage message)
  {
    this.message = message;
  }

  /**
   * Get the AI's response to the message sent
   * in getMessage
   * @return ClientChatMessage response of AI to previous getMessage
   */
  public ClientChatMessage getResponse()
  {
    ClientChatMessage response = null;
    if(message != null)
    {
      //TODO: make message to send back to person that sent message in getMessage
    }
    else
    {
      //TODO: make message for multiple regions
    }
    message = null;
    return response;
  }
}
