package starvationevasion.server;

import starvationevasion.common.messages.ServerEvent;
import starvationevasion.teamrocket.models.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Sean Naegle
 */
public class ServerWorker extends Thread
{
  private Socket client;
  private PrintWriter clientWriter;
  private BufferedReader clientReader;
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
      clientReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      
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
    String msg = null;
    try {
      msg = clientReader.readLine();
      startNanoSec = System.nanoTime();
      messageHandler(msg);

//      if (message.toLowerCase().startsWith("buy:") || message.toLowerCase().startsWith("sell:")) {
//        try {
//          String[] parts = message.split(" ");
//          if (parts.length < 3) {
//            send("Error: Invalid message: " + message);
//            return;
//          }
//          int quantity = Integer.parseInt(parts[1]);
//          double unit_price = Double.parseDouble(parts[2]);
//          // truncate to 2 decimal places.
//          unit_price = (int)(unit_price * 100) / 100.0;
//          if (parts[0].equalsIgnoreCase("buy:")) {
////            server_master.thneed_store.buy(quantity, unit_price);
//            send("Success: You just bought " + quantity + " Thneeds at $" + unit_price + " per Thneed.");
//          } else if (parts[0].equalsIgnoreCase("sell:")) {
////            server_master.thneed_store.sell(quantity, unit_price);
//            send("Success: You just sold " + quantity + " Thneeds at $" + unit_price + " per Thneed.");
//          }
//          sendBroadcast();
//        } catch (Exception e) {
//          send("Error: " + e.getMessage());
//        }
//      } else if (message.toLowerCase().startsWith("inventory")) {
//        send(storeInfoMessage());
//      } else if (message.toLowerCase().startsWith("q")) {
//        close();
//      } else {
//        send("Error: Unrecognized message: " + message);
//      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void messageHandler(String msg) {
    ServerEvent event = ServerEvent.getServerEvent(msg);

    switch(event) {
      case LOGIN:
        break;
      case PLAY:
        break;
      case TIMER:
        break;
      case SIM_STATS:
        break;
      case CARDS_CHOSEN:
        break;
      case READY:
        break;
      case VOTE:
        break;
      case DRAW:
        break;
      case CHAT:
        break;
      default:
        break;
    }
  }

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
