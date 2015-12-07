package starvationevasion.teamrocket.server;

import starvationevasion.common.messages.*;
import starvationevasion.teamrocket.main.GameController;
import starvationevasion.teamrocket.main.Main;
import starvationevasion.teamrocket.messages.EnumGameState;
import starvationevasion.teamrocket.messages.Message;
import starvationevasion.teamrocket.messages.ServerEvent;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Sean Naegle
 */
public class Client
{

  private Socket clientSocket;
  private ObjectOutputStream outputStream;
  private ObjectInputStream inputStream;
  private ClientListener listener;
  private GameController gameController;

  private boolean running = true;

  public Client(String host, int portNumber, GameController gameController)
  {
    this.gameController = gameController;
    running = true;

    while (!openConnection(host, portNumber))
    {
    }

    listener = new ClientListener();
    listener.setDaemon(true);
    System.out.println("Client(): Starting listener = : " + listener);
    listener.start();
  }


  private boolean openConnection(String host, int portNumber)
  {

    try
    {
      clientSocket = new Socket(host, portNumber);
    }
    catch (UnknownHostException e)
    {
      System.err.println("Client Error: Unknown Host " + host);
      e.printStackTrace();
      return false;
    }
    catch (IOException e)
    {
      System.err.println("Client Error: Could not open connection to " + host
          + " on port " + portNumber);
      e.printStackTrace();
      return false;
    }

    try
    {
      outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
    }
    catch (IOException e)
    {
      System.err.println("Client Error: Could not open output stream");
      e.printStackTrace();
      return false;
    }
    try
    {
      inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }
    catch (IOException e)
    {
      System.err.println("Client Error: Could not open input stream");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public void closeAll()
  {
    System.out.println("Client.closeAll()");

    running = false;
    if (outputStream != null) {
      send(ServerEvent.QUIT, "quit");
      try {
        outputStream.close();
      } catch (IOException e) {
        System.err.println("Client Error: Could not close");
        e.printStackTrace();
      }
    }
    if (inputStream != null)
    {
      try
      {
        inputStream.close();
        clientSocket.close();
      }
      catch (IOException e)
      {
        System.err.println("Client Error: Could not close");
        e.printStackTrace();
      }
    }

  }


  public static void main(String[] args)
  {

    String host = null;
    int port = 0;

    try
    {
      host = "127.0.0.1";
      port = 27015;
      if (port < 1) throw new Exception();
    }
    catch (Exception e)
    {
      System.out.println("Usage: Client hostname portNumber");
      System.exit(0);
    }
    Client client = new Client(host, port, null);

    //client.send(new Login());
  }

  public synchronized void send(ServerEvent event, Serializable object) {
    Message message = new Message(event, object);
    MessageHandler.send(outputStream, message);
  }

  public synchronized void send(Serializable payload) {
    System.out.println("Sending to server: " + payload);
    try {
      outputStream.writeObject(payload);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  class ClientListener extends Thread {
    public void run() {
      System.out.println("ClientListener.run()");
      while (running) {
        read();
      }

    }

    private void read() {
      Object msg;
      try {
        msg = inputStream.readObject();
        if (msg instanceof Response) {
          handleResponse((Response) msg);
        } else if (msg instanceof LoginResponse) {
          handleLoginResponse((LoginResponse) msg);
        } else if (msg instanceof AvailableRegions) {
          gameController.setAvailableRegions((AvailableRegions)msg);
        } else if (msg instanceof ReadyToBegin) {
          gameController.setStartGame((ReadyToBegin) msg);
        } else if (msg instanceof PhaseStart) {
          gameController.updateTimer((PhaseStart) msg);
        } else if (msg instanceof GameState) {
          gameController.updateWorldData(((GameState) msg).worldData);
          gameController.setHand(((GameState) msg).hand);
        } else if (msg instanceof ServerChatMessage) {
          gameController.setChat((ServerChatMessage) msg);
        } else if (msg instanceof ActionResponse) {
          handleActionResponse((ActionResponse) msg);
        } else if (msg instanceof Hello) {
          handleHelloResponse((Hello) msg);
        } else if (msg instanceof VoteStatus) {
          gameController.setVoteStatus((VoteStatus) msg);
        } else {
          System.out.println("Unrecognized message from Server = " + msg);
        }
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }

    }

    private void handleHelloResponse(Hello response)
    {
      gameController.setSalt(response.loginNonce);
    }

    private void handleResponse(Response response) {
      switch(response) {
        case BAD_MESSAGE:
          gameController.addErrorMessage(("Server received a bad message."));
          break;
        case INAPPROPRIATE:
          gameController.addErrorMessage("Server received an inappropriate message.");
          break;
        case OTHER_ERROR:
          gameController.addErrorMessage("Server got an error");
          break;
        case OK:
          break;
      }
    }

    private void handleLoginResponse(LoginResponse loginResponse) {
      switch (loginResponse.responseType) {
        case ACCESS_DENIED:
          gameController.setSuccessfulLogin(false);
          gameController.addErrorMessage("Access was denied for this server.");
          closeAll();
          break;
        case DUPLICATE:
          gameController.setSuccessfulLogin(false);
          gameController.addErrorMessage("There is already a user with this username connected to this server. Did someone steal your identity?");
          closeAll();
          break;
        case ASSIGNED_REGION:
          gameController.setSuccessfulLogin(true);
          break;
        case REJOIN:
          gameController.setSuccessfulLogin(false);
          gameController.addErrorMessage("There was an error with your request. Please try to rejoin this server.");
          closeAll();
          break;
        case CHOOSE_REGION:
          System.out.println("Choose regions");
          gameController.setSuccessfulLogin(true);
          break;
      }
    }

    private void handleActionResponse(ActionResponse actionResponse) {
      switch(actionResponse.responseType) {
        case OK:
          gameController.player.setHand(actionResponse.playerHand);
          break;
        case TOO_MANY_ACTIONS:
          gameController.addErrorMessage("This has been sent too many times: " + actionResponse.responseMessage);
          break;
        case TOO_MANY_VOTING_CARDS:
          gameController.addErrorMessage("You cannot submit more than 1 voting card: " + actionResponse.responseMessage);
          break;
        case NONEXISTENT_CARD:
          gameController.addErrorMessage("You do not have that card: " + actionResponse.responseMessage);
          break;
        case INVALID:
          gameController.addErrorMessage(actionResponse.responseMessage);
          break;
      }
    }
  }
}
