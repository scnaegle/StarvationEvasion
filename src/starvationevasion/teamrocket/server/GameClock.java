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
   * Time between updates in milliseconds 17 ms = 60 hz
   */
  private final long DELAY = 17;

  /**
   * Time remaining milliseconds
   */
  private long timeLeft;
  private Timer timer;

  public GameClock(long timeLeft)
  {
    this.timeLeft = timeLeft;

    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask()
    {
      public void run()
      {
        updateInterval();
      }
    }, DELAY, DELAY);
  }

  public void setServer()
  {
    //Set the link to the server.
  }

  public long getTimeLeft()
  {
    return timeLeft;
  }

  /**
   * Sets the remaining milliseconds to be displayed.
   *
   * @param milliLeft milliseconds
   */
  public void setTimeLeft(long milliLeft)
  {
    this.timeLeft = milliLeft;
  }

  public void stop()
  {
    timer.cancel();
  }

  private long updateInterval()
  {
    //Make sure gameController was initialized
    if(Main.getGameController() != null)
    {
      Main.getGameController().timerUpdate(timeLeft);
    }
    //Make sure guiController was initialized
    if(Main.getGuiController() != null)
    {
      Main.getGuiController().timerUpdate(timeLeft);
    }

    timeLeft = timeLeft - DELAY;
    return timeLeft;
  }

}
