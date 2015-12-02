package starvationevasion.teamrocket.server;

import starvationevasion.common.messages.*;
import starvationevasion.teamrocket.main.GameController;
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
  static final String BROADCAST_REGEX = "^\\d+\\.\\d{3}+:\\s*?inventory=(\\d+)\\s*?:\\s*?treasury=(\\d+\\.\\d+)";

  private Socket clientSocket;
  private ObjectOutputStream write;
  private ObjectInputStream reader;
  private Scanner keyboard;
  private ClientListener listener;
  private GameController gameController;

  private boolean running = true;

  public Client(String host, int portNumber, GameController gameController)
  {
    this.gameController = gameController;
    running = true;

    keyboard = new Scanner(System.in);

    while (!openConnection(host, portNumber))
    {
    }

    listener = new ClientListener();
    System.out.println("Client(): Starting listener = : " + listener);
    listener.start();

    listenToUserRequests();

    System.out.println("Closing everything...");
    closeAll();

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
      write = new ObjectOutputStream(clientSocket.getOutputStream());
    }
    catch (IOException e)
    {
      System.err.println("Client Error: Could not open output stream");
      e.printStackTrace();
      return false;
    }
    try
    {
      reader = new ObjectInputStream(clientSocket.getInputStream());
    }
    catch (IOException e)
    {
      System.err.println("Client Error: Could not open input stream");
      e.printStackTrace();
      return false;
    }
    return true;

  }

  private void listenToUserRequests()
  {
    while (true)
    {
      System.out.println("Enter Command (Buy: # # | Sell: # #):");
      String cmd = keyboard.nextLine();
      if (cmd == null) continue;
      if (cmd.length() < 1) continue;

      if (cmd.startsWith("inventory")) {
        printStoreInfo();
        continue;
      }
      char c = cmd.charAt(0);
      if (c == 'q') break;

//      write.println(cmd);
    }
  }

  public void closeAll()
  {
    System.out.println("Client.closeAll()");

    running = false;
    if (write != null) {
      send(ServerEvent.QUIT, "quit");
      try {
        write.close();
      } catch (IOException e) {
        System.err.println("Client Error: Could not close");
        e.printStackTrace();
      }
    }
    if (reader != null)
    {
      try
      {
        reader.close();
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
      host = args[0];
      port = Integer.parseInt(args[1]);
      if (port < 1) throw new Exception();
    }
    catch (Exception e)
    {
      System.out.println("Usage: Client hostname portNumber");
      System.exit(0);
    }
//    new Client(host, port);

  }

  public void send(ServerEvent event, Serializable object) {
    Message message = new Message(event, object);
    MessageHandler.send(write, message);
  }


  public void printStoreInfo() {
  }


  class ClientListener extends Thread {
    public void run() {
      System.out.println("ClientListener.run()");
      while (running) {
        read();
      }

    }

    private void read() {
//        System.out.println("Client: listening to socket");
      Object msg = MessageHandler.parse(reader);
//        System.out.println("Client: got message: " + msg);
      if (msg instanceof Response) {
        handleResponse((Response) msg);
      }
      if (msg instanceof LoginResponse) {
        handleLoginResponse((LoginResponse) msg);
      }
      if (msg instanceof AvailableRegions) {
        handleAvailableRegionsResponse((AvailableRegions) msg);
      }
      if (msg instanceof ReadyToBegin) {
        handleReadyToBeginResponse((ReadyToBegin) msg);
      }
      if (msg instanceof PhaseStart) {
        handlePhaseStartResponse((PhaseStart) msg);
      }
//      if (msg instanceof )
      if (msg instanceof GameState) {

      } else {
        System.out.println("Unrecognized message from Server = " + msg);
      }

    }

    private void handleResponse(Response response) {
      switch(response) {
        case BAD_MESSAGE:
          break;
        case INAPPROPRIATE:
          break;
        case OTHER_ERROR:
          break;
        case OK:
          break;
      }
    }

    private void handleLoginResponse(LoginResponse loginResponse) {
      switch (loginResponse.responseType) {
        case ACCESS_DENIED:
          // TODO let game controller know that
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
          gameController.setSuccessfulLogin(true);
          break;
      }
    }

    private void handleAvailableRegionsResponse(AvailableRegions availableRegions) {
      // TODO Send error message if the region has already been taken.
      gameController.setAvailableRegions(availableRegions);
    }

    private void handleReadyToBeginResponse(ReadyToBegin readyToBegin) {

    }

    private void handlePhaseStartResponse(PhaseStart phaseStart) {

    }

    private void handleChatMessage(ClientChatMessage message) {

    }
  }
}
