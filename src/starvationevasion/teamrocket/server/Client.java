package starvationevasion.teamrocket.server;

import starvationevasion.teamrocket.messages.Message;
import starvationevasion.teamrocket.messages.ServerEvent;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sean Naegle
 */
public class Client
{
  static final String BROADCAST_REGEX = "^\\d+\\.\\d{3}+:\\s*?inventory=(\\d+)\\s*?:\\s*?treasury=(\\d+\\.\\d+)";

  private Socket clientSocket;
  private ObjectOutputStream write;
  private ObjectInputStream reader;
  private long startNanoSec;
  private Scanner keyboard;
  private ClientListener listener;

  private volatile int thneedsInStore;
  private volatile double storeBalance;

  private boolean running = true;

  public Client(String host, int portNumber)
  {
    startNanoSec = System.nanoTime();
    System.out.println("Starting Client: " + timeDiff());
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
//      System.out.println("Thneeds in Inventory = " + thneedsInStore);
//      System.out.println("TheedStore Ballance = $" + storeBalance);
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

  private String timeDiff()
  {
    long namoSecDiff = System.nanoTime() - startNanoSec;
    double secDiff = (double) namoSecDiff / 1000000000.0;
    return String.format("%.6f", secDiff);

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
    new Client(host, port);

  }

  public void send(ServerEvent event, Serializable object) {
    Message message = new Message(event, object);
    MessageHandler.send(write, message);
  }


  public void printStoreInfo() {
    System.out.format("Current Inventory: %d  :  Current Treasury: $%.2f\n", thneedsInStore, storeBalance);
  }
  
  
  class ClientListener extends Thread
  {
    public void run()
    {
      System.out.println("ClientListener.run()");
      while (running)
      {
        read();
      }

    }

    private void read()
    {
//        System.out.println("Client: listening to socket");
      Object msg = MessageHandler.parse(reader);
//        System.out.println("Client: got message: " + msg);
      if (msg instanceof GameState) {

      }
      else
      {
        System.out.println("Unrecognized message from Server(" + timeDiff()
            + ") = " + msg);
      }

    }

  }

}
