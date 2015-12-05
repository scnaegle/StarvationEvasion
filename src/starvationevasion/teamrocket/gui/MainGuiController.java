package starvationevasion.teamrocket.gui;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.main.Main;
import starvationevasion.teamrocket.models.Player;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Handles the welcome scene, choose region scene, and login scene.
 */
public class MainGuiController implements javafx.fxml.Initializable
{
  /* BEGINNING SCENES AND THEIR BUTTONS */
  @FXML
  private Button pickedRegion;
  @FXML
  private Button ready;
  @FXML
  private Label time;

  @FXML
  private RadioButton singlePlayer, multiPlayer, joinMultiPlayer;

  private boolean singlePlayerMode = true;
  private boolean newMultiPlayerMode = false;
  private boolean joinMultiPlayerMode = false;
  @FXML
  private Label gamePlayError;

  /* LOGIN VARIABLES SUCH AS USERNAME AND PASSWORD */
  @FXML
  private Button login;
  @FXML
  private TextField username, password, ipAddress, port;
  @FXML
  private Label portError, addressError, emptyFieldError;

  String playerUsername, playerPassword, playerIP, playerPort;
  /*********************************************************/

/* HIGHLIGHTED IMAGES OF THE REGIONS */
  @FXML
  private ImageView cali, heartland, mountSt, northSt, nPlains, sPlains, southEast;

  private EnumRegion myRegion;
  @FXML
  private Label nothingSelected;

  private Player player;

  private boolean caliSelected;
  private boolean heartlandSelected;
  private boolean mountainSelected;
  private boolean nPlainSelected;
  private boolean northeastSelected;
  private boolean southeastSelected;
  private boolean sPlainSelected;

  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
  }

  /**
   * Passes login information to GameController.
   * @return true if connection established; false if failed.
   */
  public boolean setLoginInformation(){
    String playerUsername = username.getCharacters().toString();
    String playerPassword = password.getCharacters().toString();
    String playerIPAddress = ipAddress.getCharacters().toString();
    String playerNetworkPort = port.getCharacters().toString();

    return Main.getGameController().tryLogin(playerUsername, playerPassword, playerIPAddress, playerNetworkPort);
  }

  /**
   * Called by welcome scene. Asks user what kind of game they want to play.
   * Sends user input to GameController.
   * @param event Action event.
   */
  @FXML
  public void chooseGamePlay(ActionEvent event)
  {
    RadioButton gamePlay = (RadioButton)event.getSource();

    //need to reset modes if another button is pressed
    singlePlayerMode = false;
    newMultiPlayerMode = false;
    joinMultiPlayerMode = false;

    Main.getGameController().setSinglePlayerMode(false);
    Main.getGameController().setNewMultiPlayerMode(false);
    Main.getGameController().setJoinMultiPlayerMode(false);

    if(gamePlay == singlePlayer)
    {
      singlePlayerMode = true;
      Main.getGameController().setSinglePlayerMode(true);
      multiPlayer.setSelected(false);
      joinMultiPlayer.setSelected(false);
    }
    else if(gamePlay == multiPlayer)
    {
      newMultiPlayerMode = true;
      Main.getGameController().setNewMultiPlayerMode(true);
      singlePlayer.setSelected(false);
      joinMultiPlayer.setSelected(false);
    }
    else if(gamePlay == joinMultiPlayer)
    {
      joinMultiPlayerMode = true;
      Main.getGameController().setJoinMultiPlayerMode(true);
      singlePlayer.setSelected(false);
      multiPlayer.setSelected(false);
    }
  }

  /**
   * Checks for any buttons pressed.
   * Does appropriate actions depending on button.
   * @param event Action event.
   */
  @FXML
  public void buttonPressed(ActionEvent event)
  {
    Button button = (Button) event.getSource();
    if(button == ready)
    {
      //make sure something was selected
      boolean selectedGame = verifyGamePlay();
      if(!selectedGame)
      {
        return;
      }
      //load selected version of game (single or multi-player)
      if(singlePlayerMode)
      {
        try
        {
          Main.getGameController().switchToSelectRegion();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else if(newMultiPlayerMode)
      {
        //go to gameSetting scene, then gameRoom scene, then game
        try{
          Main.getGameController().switchToLoginScene();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else if(joinMultiPlayerMode)
      {
        //go to login scene, then gameRoom, then game
        try{
          Main.getGameController().switchToLoginScene();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    else if(button == login)
    {
      portError.setVisible(false);
      addressError.setVisible(false);
      //verify and save input
      boolean input = verifyLoginInput();
      String portNum = Main.getGameController().checkPort(
          port.getCharacters().toString());
      String IPAddress = Main.getGameController().checkAddress(
          ipAddress.getCharacters().toString());
      if (portNum.equals("bad"))
      {
        portError.setVisible(true);
      }
      if(IPAddress.equals("bad") )
      {
        addressError.setVisible(true);
      }
      if(!input || portNum.equals("bad") || IPAddress.equals("bad"))
      {
        return;
      }
      if( setLoginInformation())
      {
        //go to gameRoom
        try
        {
          Main.getGameController().switchToGameScene();
          Main.getGameController().openChat();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
      {
        //Set general server error.
        addressError.setVisible(true);
      }
    }

    else if(button == pickedRegion)
    {
      myRegion = saveRegion();

      if(myRegion == null)
      {
        return;
      }

      //Go to next scene
      try
      {
        this.player = Main.getGameController().startSinglePlayerGame(this.myRegion);

      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

    }

  }


  private boolean verifyLoginInput()
  {
    emptyFieldError.setVisible(false);
    if(username.getCharacters().toString().isEmpty() || password.getCharacters().toString().isEmpty()
        ||ipAddress.getCharacters().toString().isEmpty() || port.getCharacters().toString().isEmpty())
    {
      emptyFieldError.setVisible(true);
      return false;
    }
    return true;
  }



  /**
   * @return true if something is selected
   */
  private boolean verifyGamePlay()
  {
    gamePlayError.setVisible(false);
    if(!singlePlayer.isSelected() && !multiPlayer.isSelected() && !joinMultiPlayer.isSelected())
    {
      gamePlayError.setVisible(true);
      return false;
    }
    return true;
  }



  private EnumRegion saveRegion()
  {
    nothingSelected.setVisible(false);
    EnumRegion playerRegion;

    if(caliSelected)
    {
      playerRegion = EnumRegion.CALIFORNIA;
    }
    else if(mountainSelected)
    {
      playerRegion = EnumRegion.MOUNTAIN;
    }
    else if(heartlandSelected)
    {
      playerRegion = EnumRegion.HEARTLAND;
    }
    else if(northeastSelected)
    {
      playerRegion = EnumRegion.NORTHERN_CRESCENT;
    }
    else if(southeastSelected)
    {
      playerRegion = EnumRegion.SOUTHEAST;
    }
    else if(nPlainSelected)
    {
      playerRegion = EnumRegion.NORTHERN_PLAINS;
    }
    else if(sPlainSelected)
    {
      playerRegion = EnumRegion.SOUTHERN_PLAINS;
    }
    else
    {
      //show error label
      playerRegion = null;
      nothingSelected.setVisible(true);
      //don't go to next stage until they select something
    }
    System.out.println("I have chosen " + playerRegion);


    return playerRegion;
  }


  /**
   * Method specifically for the larger map where the user chooses their region.
   *
   * @param event Mouse clicked event.
   */
  @FXML
  public void selectInitialRegion(MouseEvent event)
  {
    double x = event.getX();
    double y = event.getY();

    makeAllInvisible();
    deselectAllRegions();

    if (ImageRegion.CALIFORNIA1.contains(x, y))
    {
      cali.setVisible(true);
      caliSelected = true;
      //initialRegionSelected = true;
      System.out.println("Selected cali");
    }
    else if (ImageRegion.HEARTLAND1.contains(x, y))
    {
      heartland.setVisible(true);
      heartlandSelected = true;
      //initialRegionSelected = true;
      System.out.println("Selected heartland");
    }
    else if (ImageRegion.MOUNTAINST1.contains(x, y))
    {
      mountSt.setVisible(true);
      mountainSelected = true;
      //initialRegionSelected = true;
      System.out.println("Selected Mountain States");
    }
    else if (ImageRegion.NORTHPLAINS1.contains(x, y))
    {
      nPlains.setVisible(true);
      nPlainSelected = true;
      //initialRegionSelected = true;
      System.out.println("Selected North Plains");
    }
    else if (ImageRegion.NORTHEAST1.contains(x, y))
    {
      northSt.setVisible(true);
      northeastSelected = true;
      //initialRegionSelected = true;
      System.out.println("Selected Northeast");
    }
    else if (ImageRegion.SOUTHEAST1.contains(x, y))
    {
      southEast.setVisible(true);
      southeastSelected = true;
      //initialRegionSelected = true;
      System.out.println("Selected Southeast");

    }
    else if (ImageRegion.SOUTHPLAINS1.contains(x, y))
    {
      sPlains.setVisible(true);
      sPlainSelected = true;
      //initialRegionSelected = true;
      System.out.println("Selected South Plains");
    }
  }

  private void deselectAllRegions()
  {
    caliSelected = false;
    mountainSelected = false;
    nPlainSelected = false;
    sPlainSelected = false;
    heartlandSelected = false;
    northeastSelected = false;
    southeastSelected = false;
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



  @FXML
  public void findPolygon(MouseEvent event)
  {
    double x = event.getX();
    double y = event.getY();

    System.out.println(x + "," + y + ",");
  }

  /**
   * Determines what needs to happen on a given scene.
   * @param timeLeft
   */
  public void timerUpdate(long timeLeft)
  {
    if(time != null)
    {
      String stringTime = (timeLeft/60000) + ":" + (timeLeft%60000/1000);
      //time.setText(stringTime);
    }

  }
}

