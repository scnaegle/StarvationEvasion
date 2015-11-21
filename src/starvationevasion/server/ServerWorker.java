package starvationevasion.server;

import starvationevasion.common.messages.ServerEvent;
import starvationevasion.teamrocket.models.Player;

import java.io.*;
import java.net.Socket;

/**
 * Created by Sean Naegle
 */
public class ServerWorker extends Thread
{
  private Socket client;
  private PrintWriter clientWriter;
  private ObjectInputStream clientReader;
  private ServerMaster server_master;
  private Player player;
  private long startNanoSec = 0;

  private boolean running = true;

  public ServerWorker(Socket client, ServerMaster server_master)
  {
    this.client = client;
    this.server_master = server_master;
    this.running = true;

    try
    {
      //          PrintWriter(OutputStream out, boolean autoFlushOutputBuffer)
      clientWriter = new PrintWriter(client.getOutputStream(), true);
    }
    catch (IOException e)
    {
      System.err.println("Server Worker: Could not open output stream");
      e.printStackTrace();
    }
    try
    {
      clientReader = new ObjectInputStream(client.getInputStream());
      
    }
    catch (IOException e)
    {
      System.err.println("Server Worker: Could not open input stream");
      e.printStackTrace();
    }
  }
  
  //Called by ServerMaster
  public void send(String msg)
  {
    System.out.println("ServerWorker.send(" + msg + ")");
    clientWriter.println(msg);
  }

  public void run()
  {
    System.out.println("Inside the Server worker run method");

    while (running)
    {
      read();
    }
    System.out.println("Worker exiting...");
  }

  private void read() {
//    String msg = null;
    MessageHandler.parse(clientReader);
  }

//  private void messageHandler(String msg) {
//    ServerEvent.parse(msg);
//  }

  private void sendBroadcast() {
    server_master.broadcast(storeInfoMessage());
  }

  public String storeInfoMessage() {
//    return timeDiff() + ": inventory=" + server_master.thneed_store.getThneeds() +
//            " : treasury=" + server_master.thneed_store.getBalanceString();
    return "blah";
  }

  private String timeDiff()
  {
    long namoSecDiff = System.nanoTime() - startNanoSec;
    double secDiff = (double) namoSecDiff / 1000000000.0;
    return String.format("%.3f", secDiff);
  }

  private void close() {
    running = false;
    server_master.closeConnection(this);
  }
}
