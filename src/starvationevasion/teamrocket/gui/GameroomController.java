package starvationevasion.teamrocket.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.messages.RegionChoice;
import starvationevasion.teamrocket.main.Main;
import starvationevasion.teamrocket.models.Player;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Handles the game room scene.
 */
public class GameroomController implements javafx.fxml.Initializable
{
  /* USERNAMES AND LABELS FOR GAMEROOM */
  @FXML
  private Button doneGameRoom;

  @FXML
  private Label user1, user2, user3, user4, user5, user6, user7, user1Region, user2Region, user3Region, user4Region,
      user5Region, user6Region, user7Region, countdown;

  @FXML
  private ImageView cali, heartland, mountSt, northSt, nPlains, sPlains, southEast;

  private EnumRegion myRegion;

  private Player player;
  /*************************************************************************/
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    connectUsers();

  }

  public void connectUsers()
  {
    //if(user1 has logged in)
    user1.setText(Main.getGameController().getPlayerUsername());
    user1.setVisible(true);
    //if(user2 has logged in)....

  }

  public void startCountdown()
  {
    countdown.setVisible(true);
    StringProperty count = new SimpleStringProperty("10");
    countdown.textProperty().bind(count);
    for (int i = 10; i >= 0; i--)
    {
      count.setValue(i+"");
    }
  }

  public void showUsersRegion()
  {
    user1Region.setText("" + myRegion);
    user1Region.setVisible(true);
  }

  @FXML
  public void buttonPressed(ActionEvent event)
  {
    //For testing purposes
    if(event.getSource() == doneGameRoom)
  {
    try{
      this.player = Main.getGameController().startNewGame(myRegion);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  }

  /**
   * Method specifically for the smaller map inside of gameRoom.fxml.
   *
   * @param event Mouse clicked event
   */
  @FXML
  public void chooseRegion(MouseEvent event)
  {
    double x = event.getX();
    double y = event.getY();
    makeAllInvisible();
    if (ImageRegion.CALIFORNIA.contains(x, y))
    {
      cali.setVisible(true);
      myRegion = EnumRegion.CALIFORNIA;
    }
    else if (ImageRegion.HEARTLAND.contains(x, y))
    {
      heartland.setVisible(true);
      myRegion = EnumRegion.HEARTLAND;
    }
    else if (ImageRegion.MOUNTAINST.contains(x, y))
    {
      mountSt.setVisible(true);
      myRegion = EnumRegion.MOUNTAIN;
    }
    else if (ImageRegion.NORTHPLAINS.contains(x, y))
    {
      nPlains.setVisible(true);
      myRegion = EnumRegion.NORTHERN_PLAINS;
    }
    else if (ImageRegion.NORTHEAST.contains(x, y))
    {
      northSt.setVisible(true);
      myRegion = EnumRegion.NORTHERN_CRESCENT;
    }
    else if (ImageRegion.SOUTHEAST.contains(x, y))
    {
      southEast.setVisible(true);
      myRegion = EnumRegion.SOUTHEAST;
    }
    else if (ImageRegion.SOUTHPLAINS.contains(x, y))
    {
      sPlains.setVisible(true);
      myRegion = EnumRegion.SOUTHERN_PLAINS;
    }
    Main.getGameController().setSelectRegion(new RegionChoice(myRegion));
    showUsersRegion();
  }

  private void makeAllInvisible()
  {
    cali.setVisible(false);
    mountSt.setVisible(false);
    nPlains.setVisible(false);
    sPlains.setVisible(false);
    heartland.setVisible(false);
    southEast.setVisible(false);
    northSt.setVisible(false);
  }
}