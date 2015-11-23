package starvationevasion.teamrocket.server;

import starvationevasion.teamrocket.messages.EnumGameState;
import starvationevasion.teamrocket.messages.Message;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 * Created by Sean Naegle
 */
public class ServerMaster
{
  private ServerSocket serverSocket;
  private LinkedList<ServerWorker> allConnections = new LinkedList<ServerWorker>();
  public EnumGameState game_state = EnumGameState.WAITING_FOR_CONNECTIONS;

  public ServerMaster(int portNumber)
  {
    game_state = EnumGameState.WAITING_FOR_CONNECTIONS;
    try
    {
      serverSocket = new ServerSocket(portNumber);
    }
    catch (IOException e)
    {
      System.err.println("Server error: Opening socket failed.");
      e.printStackTrace();
      System.exit(-1);
    }

    waitForConnection(portNumber);
  }

  public void waitForConnection(int port)
  {
    String host = "";
    try
    {
      host = InetAddress.getLocalHost().getHostName();
    }
    catch (UnknownHostException e)
    {
      e.printStackTrace();
    }
    while (true)
    {
      System.out.println("ServerMaster("+host+"): waiting for Connection on port: "+port);
      try
      {
        Socket client = serverSocket.accept();
        ServerWorker worker = new ServerWorker(client, this);
        worker.start();
        System.out.println("ServerMaster: *********** new Connection");
        allConnections.add(worker);
//        worker.send("ServerMaster says hello!");
//        worker.send(worker.storeInfoMessage());
      }
      catch (IOException e)
      {
        System.err.println("Server error: Failed to connect to client.");
        e.printStackTrace();
      }

    }
  }

  public void cleanConnectionList()
  {
    for (ServerWorker worker : allConnections) {
      if (!worker.isAlive()) {
        allConnections.remove(worker);
      }
    }
  }

  public void broadcast(Message message)
  {
    for (ServerWorker worker : allConnections)
    {
      worker.send(message);
    }
  }

  public boolean allReady() {
    for(ServerWorker worker : allConnections) {
      if (!worker.ready) {
        return false;
      }
    }
    return true;
  }

  public void closeConnection(ServerWorker worker) {
    allConnections.remove(worker);
    cleanConnectionList();
  }
  
  public static void main(String args[])
  {
    //Valid port numbers are Port numbers are 1024 through 65535.
    //  ports under 1024 are reserved for system services http, ftp, etc.
    int port = 5555; //default
    if (args.length > 0)
    try
    {
      port = Integer.parseInt(args[0]);
      if (port < 1) throw new Exception();
    }
    catch (Exception e)
    {
      System.out.println("Usage: ServerMaster portNumber");
      System.exit(0);
    }
    
    new ServerMaster(port);
  }
}
