package starvationevasion.teamrocket.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.messages.AvailableRegions;
import starvationevasion.common.messages.RegionChoice;
import starvationevasion.teamrocket.PlayerInterface;
import starvationevasion.teamrocket.main.Main;
import starvationevasion.teamrocket.models.Player;

import java.net.URL;
import java.util.Map;
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
  private Label user1, user2, user3, user4, user5, user6, user7, countdown;

  @FXML
  private ImageView cali, heartland, mountSt, northSt, nPlains, sPlains, southEast;

  private EnumRegion myRegion;

  private PlayerInterface player;


  /*************************************************************************/
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
  }

  public GameroomController()
  {
    Timeline updater = new Timeline(new KeyFrame(Duration.millis(Main.GUI_REFRESH_RATE), new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
        if(Main.getGameController().getCurrentScene() == EnumScene.GAME_ROOM)
        {
          countdown.setText(Main.GAME_CLOCK.getFormatted());
          connectUsers();
          if (Main.getGameController().initGUI())
          {

          }
          if (Main.GAME_CLOCK.getTimeLeft() <= 0)
          {
            //Main.getGameController().finishedCardDraft();
          }
        }
      }
    }));

    updater.setCycleCount(Timeline.INDEFINITE);
    updater.play();
  }
  public void connectUsers()
  {
    AvailableRegions availableRegions = Main.getGameController().getAvailableRegions();
    Map<EnumRegion, String> takenRegions = availableRegions.takenRegions;

    user1.setText((takenRegions.get(EnumRegion.CALIFORNIA) != null) ? takenRegions.get(EnumRegion.CALIFORNIA) : "Free");
    user1.setVisible(true);

    user2.setText((takenRegions.get(EnumRegion.HEARTLAND) != null) ? takenRegions.get(EnumRegion.HEARTLAND) : "Free");
    user2.setVisible(true);

    user3.setText((takenRegions.get(EnumRegion.NORTHERN_PLAINS) != null) ? takenRegions.get(EnumRegion.NORTHERN_PLAINS) : "Free");
    user3.setVisible(true);

    user4.setText((takenRegions.get(EnumRegion.SOUTHEAST) != null) ? takenRegions.get(EnumRegion.SOUTHEAST) : "Free");
    user4.setVisible(true);

    user5.setText((takenRegions.get(EnumRegion.NORTHERN_CRESCENT) != null) ? takenRegions.get(EnumRegion.NORTHERN_CRESCENT) : "Free");
    user5.setVisible(true);

    user6.setText((takenRegions.get(EnumRegion.SOUTHERN_PLAINS) != null) ? takenRegions.get(EnumRegion.SOUTHERN_PLAINS) : "Free");
    user6.setVisible(true);

    user7.setText((takenRegions.get(EnumRegion.MOUNTAIN) != null) ? takenRegions.get(EnumRegion.MOUNTAIN) : "Free");
    user7.setVisible(true);

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


  @FXML
  public void buttonPressed(ActionEvent event)
  {
    //For testing purposes
    if(event.getSource() == doneGameRoom)
  {
    try{
      this.player = Main.getGameController().startSinglePlayerGame(myRegion);
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
