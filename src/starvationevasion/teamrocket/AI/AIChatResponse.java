package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumRegion;
import starvationevasion.common.messages.ClientChatMessage;
import starvationevasion.common.messages.ServerChatMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zfalgout on 12/6/15.
 */
public class AIChatResponse
{
  private ArrayList<String> unknownMessageResponses = new ArrayList<String>();
  private String[] coopKeywords = {"coop", "cooperate", "co-operate", "join", "together", "team"};
  private final String COOP_RESPONSE = "I will join you";
  private PlayerRecord[] records;
  private ServerChatMessage message;
  private AI linkedAI;
  private Random generator;

  public AIChatResponse(PlayerRecord[] records, AI ai, Random generator)
  {
    this.records = records;
    linkedAI = ai;
    this.generator = generator;
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
   * @return ClientChatMessage response of AI to previous getMessage, or null if no previous message received
   */
  public ClientChatMessage getResponse()
  {
    boolean cooping = false;

    if(message != null)
    {
      if(records[message.sender.ordinal()].isPlayerCooperative())
      {

        if(message.card == null)
        {
          for(String keyword : coopKeywords)
          {

            if(message.message.toLowerCase().contains(keyword)) cooping = true;
          }
        }

        else cooping = true;
      }

      EnumRegion[] regions = {message.sender};

      if(cooping)
      {
        linkedAI.setCooperatingRegion(message.sender);
        message = null;
        return new ClientChatMessage(COOP_RESPONSE, regions);
      }

      message = null;
      return new ClientChatMessage(unknownMessageResponses.get(generator.nextInt(unknownMessageResponses.size())), regions);

    }

    return null;
  }
}
