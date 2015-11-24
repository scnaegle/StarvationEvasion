package starvationevasion.teamrocket.server.events;

import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.models.Player;

/**
 * Created by scnaegl on 11/23/15.
 */
public class ServerEvents {
  public static class LoginEvent {
    public final Player player;
    public final String username;
    public final String password;

    public LoginEvent(Player player, String username, String password) {
      this.player = player;
      this.username = username;
      this.password = password;
    }
  }

  public static interface LoginListener {
    void playerLogin(LoginEvent event);
    void notify(LoginEvent event);
  }

  public static class RegionSelectEvent {
    public final Player player;
    public final EnumRegion region;

    public RegionSelectEvent(Player player, EnumRegion region) {
      this.player = player;
      this.region = region;
    }
  }

  public static interface RegionSelectListener {
    void updatePlayerRegion(RegionSelectEvent event);
    void notify(RegionSelectEvent event);
  }
}
