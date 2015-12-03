package starvationevasion.teamrocket.server;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Constant refresher
 */
public class GameClock implements Serializable {

  /**
   * Time between updates in milliseconds 17 ms = 60 hz
   */
  private final int DELAY = 17;

  /**
   * Time remaining milliseconds
   */
  private int timeLeft;
  private transient Timer timer;

  public GameClock() {
    this(300);
  }

  public GameClock(int timeLeft) {
    this.timeLeft = timeLeft;
    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        updateInterval();
      }
    }, DELAY, DELAY);
  }

  public int getTimeLeft() {
    return timeLeft;
  }

  /**
   * Sets the remaining milliseconds to be displayed.
   * @param milliLeft milliseconds
   */
  public void setTimeLeft(int milliLeft) {
    this.timeLeft = milliLeft;
  }

  public int getMinutes() {
    return timeLeft / 60;
  }

  public int getSeconds() {
    return timeLeft % 60;
  }

  public void stop() {
    timer.cancel();
  }

  private int updateInterval() {
    if (timeLeft == 1)
      timer.cancel();
    return --timeLeft;
  }

}
