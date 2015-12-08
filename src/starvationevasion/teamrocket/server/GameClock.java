package starvationevasion.teamrocket.server;

import starvationevasion.teamrocket.main.Main;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Constant refresher
 */
public class GameClock
{

  /**
   * Time remaining milliseconds
   */
  private long timeLeft;
  private Timer timer;

  public GameClock(long timeLeft)
  {
    this.timeLeft = timeLeft;

    timer = new Timer(true);
    timer.scheduleAtFixedRate(new TimerTask()
    {
      public void run()
      {
        updateInterval();
      }
    }, Main.GUI_REFRESH_RATE, Main.GUI_REFRESH_RATE);
  }

  public void setServer()
  {
    //Set the link to the server.
  }

  public long getTimeLeft()
  {
    return timeLeft;
  }

  public long getMinutes()
  {
    return timeLeft/60000;
  }

  public long getSeconds()
  {
    return timeLeft%60000;
  }

  /**
   * Sets the remaining milliseconds to be displayed.
   *
   * @param milliLeft milliseconds
   */
  public void setTimeLeft(long milliLeft)
  {
    this.timeLeft = milliLeft - 1000;
  }

  public void stop()
  {
    timer.cancel();
  }

  public String getFormatted()
  {
    long minutes = timeLeft/60000;
    long seconds = timeLeft%60000;
    if(minutes == 0 && seconds < 10000l)
    {
      return String.format("%.2f", (float)seconds/1000);
    }
    return String.format("%d:%02d", minutes, (seconds/1000));

  }



  private long updateInterval()
  {
    timeLeft = timeLeft - Main.GUI_REFRESH_RATE;
    if(timeLeft < 0) timeLeft = 0;
    return timeLeft;
  }

}
