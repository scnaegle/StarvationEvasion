package starvationevasion.teamrocket.gui;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.main.Main;
import starvationevasion.teamrocket.models.Player;


public class GuiController
{
  @FXML
  private Button drawCardButton;
  @FXML
  private Button appleButton;
  @FXML
  private Button grainsButton;
  @FXML
  private Button citrusButton;
  @FXML
  private Button feedButton;
  @FXML
  private Button dairyButton;
  @FXML
  private Button fishButton;
  @FXML
  private Button meatButton;
  @FXML
  private Button nutButton;
  @FXML
  private Button oilButton;
  @FXML
  private Button poultryButton;
  @FXML
  private Button veggieButton;
  @FXML
  private Button specialButton;

  @FXML
  private Button caliCard1;
  @FXML
  private Button caliCard2;
  @FXML
  private Button mountCard1;
  @FXML
  private Button mountCard2;
  @FXML
  private Button nPlainCard1;
  @FXML
  private Button nPlainCard2;
  @FXML
  private Button sPlainCard1;
  @FXML
  private Button sPlainCard2;
  @FXML
  private Button heartCard1;
  @FXML
  private Button heartCard2;
  @FXML
  private Button neCard1;
  @FXML
  private Button neCard2;
  @FXML
  private Button seCard1;
  @FXML
  private Button seCard2;

  @FXML
  private Button subVote;
  @FXML
  private Pane caliCardPane1;

  @FXML
  private Button support;
  @FXML
  private Button oppose;
  @FXML
  private Button abstain;

  @FXML
  private Label sVotes;
  @FXML
  private Label oVotes;
  @FXML
  private Label aVotes;

  @FXML
  private Label sLabel;
  @FXML
  private Label oLabel;
  @FXML
  private Label aLabel;

  private int caliSupportVotes1;
  private int caliOpposeVotes1;
  private int caliAbstainVotes1;

  @FXML
  private Pane appleWindow;
  @FXML
  private Pane grainWindow;
  @FXML
  private Pane citrusWindow;
  @FXML
  private Pane feedWindow;
  @FXML
  private Pane meatWindow;
  @FXML
  private Pane dairyWindow;
  @FXML
  private Pane nutWindow;
  @FXML
  private Pane oilWindow;
  @FXML
  private Pane poultryWindow;
  @FXML
  private Pane veggieWindow;
  @FXML
  private Pane specialWindow;
  @FXML
  private Pane fishWindow;

  @FXML
  private Button pickedRegion;
  @FXML
  private Button ready;
  @FXML
  private Button undo;
  @FXML
  private Button doneWithCards;
  @FXML
  private Button doneVoting;
  @FXML
  private Button closeWindow;
  @FXML
  private Button closeWindow2;
  @FXML
  private Button closeWindow3;
  @FXML
  private Button closeWindow4;
  @FXML
  private Button closeWindow5;
  @FXML
  private Button closeWindow6;
  @FXML
  private Button closeWindow7;
  @FXML
  private Button closeWindow8;
  @FXML
  private Button closeWindow9;
  @FXML
  private Button closeWindow10;
  @FXML
  private Button closeWindow11;
  @FXML
  private Button closeWindow12;
  @FXML
  private Label close;
  @FXML
  private Label close2;
  @FXML
  private Label close3;
  @FXML
  private Label close4;
  @FXML
  private Label close5;
  @FXML
  private Label close6;
  @FXML
  private Label close7;
  @FXML
  private Label close8;
  @FXML
  private Label close9;
  @FXML
  private Label close10;
  @FXML
  private Label close11;
  @FXML
  private Label close12;

  @FXML
  private RadioButton singlePlayer;
  @FXML
  private RadioButton multiPlayer;
  @FXML
  private Label gamePlayError;


  @FXML
  private ImageView cali;
  @FXML
  private ImageView heartland;
  @FXML
  private ImageView mountSt;
  @FXML
  private ImageView northSt;
  @FXML
  private ImageView nPlains;
  @FXML
  private ImageView sPlains;
  @FXML
  private ImageView southEast;

  @FXML
  private Label mainLabel;
  @FXML
  private Label time;

  /* REGION LABELS */
  @FXML
  private Label caliLabel;
  @FXML
  private Label seLabel;
  @FXML
  private Label neLabel;
  @FXML
  private Label mountLabel;
  @FXML
  private Label heartLabel;
  @FXML
  private Label sPlainLabel;
  @FXML
  private Label nPlainLabel;
  @FXML
  private Label nothingSelected;
  @FXML
  private Label currentRegion;

  /* CROP LABELS */
  @FXML
  private Label appleLabel;
  @FXML
  private Label grainLabel;
  @FXML
  private Label citrusLabel;
  @FXML
  private Label feedLabel;
  @FXML
  private Label dairyLabel;
  @FXML
  private Label fishLabel;
  @FXML
  private Label meatLabel;
  @FXML
  private Label nutLabel;
  @FXML
  private Label oilLabel;
  @FXML
  private Label poultryLabel;
  @FXML
  private Label veggieLabel;
  @FXML
  private Label specialLabel;

  private Stage stage;


  private EnumRegion playerRegion;

  private Player player;


  private boolean caliSelected;
  private boolean heartlandSelected;
  private boolean mountainSelected;
  private boolean nPlainSelected;
  private boolean northeastSelected;
  private boolean southeastSelected;
  private boolean sPlainSelected;

  private boolean[] regionSelected =
      {caliSelected, heartlandSelected, mountainSelected, nPlainSelected, northeastSelected
          , southeastSelected, sPlainSelected};
  private Main main;

  @FXML
  public void chooseGamePlay(ActionEvent event)
  {
    RadioButton gamePlay = (RadioButton)event.getSource();
    if(gamePlay == singlePlayer)
    {
      multiPlayer.setSelected(false);
      //tell game control theres only one player
    }
    else if(gamePlay == multiPlayer)
    {
      singlePlayer.setSelected(false);
      //tell game control there's many players
    }
  }
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
      try
      {
        Main.gameController.switchToSelectRegion();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

    }
    else if(button == pickedRegion)
    {
      EnumRegion myRegion = saveRegion();
      if(myRegion == null)
      {
        return;
      }
      //Go to next scene
      try
      {
        this.player = Main.gameController.startNewGame(this.playerRegion);
        //highlightMyRegion(myRegion);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else if(button == doneWithCards)
    {
      //make sure card has been played, show error label if not
      try
      {
        Main.gameController.finishedCardDraft();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else if(button == undo)
    {
      //undo card action
    }
    else if(button == doneVoting)
    {
      //save choices
      //update variables
      try
      {
        Main.gameController.finishedVoting();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else if(button == closeWindow || button == closeWindow2 || button == closeWindow3 ||
        button == closeWindow4 ||
        button == closeWindow5 ||
        button == closeWindow6 ||
        button == closeWindow7 ||
        button == closeWindow8 ||
        button == closeWindow9 ||
        button == closeWindow10 ||
        button == closeWindow11 ||
        button == closeWindow12 )
    {
      closeProduceWindows();
    }
    else if(button == caliCard1)
    {
      caliCardPane1.setVisible(true);
    }
    else if(button == subVote)
    {
      caliCardPane1.setVisible(false);
      //Do other stuff?
    }
    else if (button == drawCardButton)
    {
      //draw card from deck.
      //start animation to slide them across pane
    }
    else if (button == appleButton)
    {
      //see whether it's for a policy card or for viewing
        //if policy card, use product for card
        //else display facts
      appleWindow.setVisible(true);

    }
    else if (button == grainsButton)
    {
      //see whether it's for a policy card or for viewing
      //if policy card, use product for card
      //else display facts
      grainWindow.setVisible(true);
    }
    else if(button == citrusButton)
    {
      //see whether it's for a policy card or for viewing
      //if policy card, use product for card
      //else display facts
      citrusWindow.setVisible(true);
    }
    else if(button == feedButton)
    {
      //see whether it's for a policy card or for viewing
      //if policy card, use product for card
      //else display facts
      feedWindow.setVisible(true);
    }
    else if(button == dairyButton)
    {
      //see whether it's for a policy card or for viewing
      //if policy card, use product for card
      //else display facts
      dairyWindow.setVisible(true);
    }
    else if(button == meatButton)
    {
      //see whether it's for a policy card or for viewing
      //if policy card, use product for card
      //else display facts
      meatWindow.setVisible(true);
    }
    else if(button == poultryButton)
    {
      //see whether it's for a policy card or for viewing
      //if policy card, use product for card
      //else display facts
      poultryWindow.setVisible(true);
    }
    else if(button == oilButton)
    {
      //see whether it's for a policy card or for viewing
      //if policy card, use product for card
      //else display facts
      oilWindow.setVisible(true);
    }
    else if(button == specialButton)
    {
      //see whether it's for a policy card or for viewing
      //if policy card, use product for card
      //else display facts
      specialWindow.setVisible(true);
    }
    else if(button == veggieButton)
    {
      //see whether it's for a policy card or for viewing
      //if policy card, use product for card
      //else display facts
      veggieWindow.setVisible(true);
    }
    else if(button == nutButton)
    {
      //see whether it's for a policy card or for viewing
      //if policy card, use product for card
      //else display facts
      nutWindow.setVisible(true);
    }
    else if(button == fishButton)
    {
      //see whether it's for a policy card or for viewing
      //if policy card, use product for card
      //else display facts
      fishWindow.setVisible(true);
    }
  }


  private void handCursor()
  {
    main.getCurrentStage().getScene().getRoot().setCursor(Cursor.HAND);
  }

  private void defaultCursor()
  {
    main.getCurrentStage().getScene().getRoot().setCursor(Cursor.DEFAULT);
  }

  private void closeProduceWindows()
  {
    appleWindow.setVisible(false);
    grainWindow.setVisible(false);
    citrusWindow.setVisible(false);
    feedWindow.setVisible(false);
    meatWindow.setVisible(false);
    fishWindow.setVisible(false);
    nutWindow.setVisible(false);
    poultryWindow.setVisible(false);
    oilWindow.setVisible(false);
    veggieWindow.setVisible(false);
    specialWindow.setVisible(false);
    dairyWindow.setVisible(false);

  }

  /**
   * @return true if something is selected
   */
  private boolean verifyGamePlay()
  {
    gamePlayError.setVisible(false);
    if(!singlePlayer.isSelected() && !multiPlayer.isSelected())
    {
      gamePlayError.setVisible(true);
      return false;
    }
    return true;
  }

  private void highlightMyRegion(EnumRegion myRegion)
  {
    if(myRegion == EnumRegion.CALIFORNIA) cali.setVisible(true);
    else if(myRegion == EnumRegion.MOUNTAIN) mountSt.setVisible(true);
  }

  private EnumRegion saveRegion()
  {
    nothingSelected.setVisible(false);

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
      nothingSelected.setVisible(true);
      //don't go to next stage until they select something
    }
    //currentRegion.setText("Current Region: "+playerRegion);
    System.out.println("I have chosen " + playerRegion);


    return playerRegion;
  }

  @FXML
  public void mouseOverProduce(MouseEvent event)
  {
    hideCropLabels();

    if(event.getSource() == appleButton)
    {
      appleLabel.setVisible(true);
    }
    else if(event.getSource() == grainsButton)
    {
      grainLabel.setVisible(true);
    }
    else if(event.getSource() == citrusButton)
    {
      citrusLabel.setVisible(true);
    }
    else if(event.getSource() == feedButton)
    {
      feedLabel.setVisible(true);
    }
    else if(event.getSource() == dairyButton)
    {
      dairyLabel.setVisible(true);
    }
    else if(event.getSource() == fishButton)
    {
      fishLabel.setVisible(true);
    }
    else if(event.getSource() == meatButton)
    {
      meatLabel.setVisible(true);
    }
    else if(event.getSource() == nutButton)
    {
      nutLabel.setVisible(true);
    }
    else if(event.getSource() == oilButton)
    {
      oilLabel.setVisible(true);
    }
    else if(event.getSource() == poultryButton)
    {
      poultryLabel.setVisible(true);
    }
    else if(event.getSource() == veggieButton)
    {
      veggieLabel.setVisible(true);
    }
    else if(event.getSource() == specialButton)
    {
      specialLabel.setVisible(true);
    }
    else if(event.getSource() == closeWindow || event.getSource() == closeWindow2 ||
        event.getSource() == closeWindow3 ||
        event.getSource() == closeWindow4 ||
        event.getSource() == closeWindow5 ||
        event.getSource() == closeWindow6 ||
        event.getSource() == closeWindow7 ||
        event.getSource() == closeWindow8 ||
        event.getSource() == closeWindow9 ||
        event.getSource() == closeWindow10 ||
        event.getSource() == closeWindow11 ||
        event.getSource() == closeWindow12 )
    {
      changeTextColor(Color.BLUE);

    }

  }
  @FXML
  private void vote(ActionEvent event)
  {
    Button button = (Button)event.getSource();

    if(button == support)
    {
      caliSupportVotes1++;
      sVotes.setText("" + caliSupportVotes1);
    }
    else if(button == oppose)
    {
      caliOpposeVotes1++;
      oVotes.setText("" + caliOpposeVotes1);
    }
    else if(button == abstain)
    {
      caliAbstainVotes1++;
      aVotes.setText("" + caliAbstainVotes1);
    }

  }

  @FXML
  public void mouseOverCards(MouseEvent event)
  {
    if(event.getSource() == support)
    {
      sLabel.setVisible(true);
    }
    else if(event.getSource() == oppose)
    {
      oLabel.setVisible(true);
    }
    else if(event.getSource() == abstain)
    {
     aLabel.setVisible(true);
    }
  }

  private void changeTextColor(Color color)
  {
    close.setTextFill(color);
    close2.setTextFill(color);
    close3.setTextFill(color);
    close4.setTextFill(color);
    close5.setTextFill(color);
    close6.setTextFill(color);
    close7.setTextFill(color);
    close8.setTextFill(color);
    close9.setTextFill(color);
    close10.setTextFill(color);
    close11.setTextFill(color);
    close12.setTextFill(color);
  }

  @FXML
  public void mouseExit()
  {
    hideCropLabels();
    changeTextColor(Color.BLACK);
  }

  private void hideVoteLabels()
  {
    sLabel.setVisible(false);
    oLabel.setVisible(false);
    aLabel.setVisible(false);
  }

  @FXML
  public void updateLabel()
  {

  }


  @FXML
  public void mouseMoved(MouseEvent event)
  {
    double x = event.getX();
    double y = event.getY();

    hideRegionLabels();
    showLabel(x, y);

  }

  private void showLabel(double x, double y)
  {
    if (ImageRegion.CALIFORNIA.contains(x, y))
    {
      caliLabel.setVisible(true);
    }
    else if (ImageRegion.MOUNTAINST.contains(x, y))
    {
      mountLabel.setVisible(true);
    }
    else if (ImageRegion.NORTHPLAINS.contains(x, y))
    {
      nPlainLabel.setVisible(true);
    }
    else if (ImageRegion.SOUTHPLAINS.contains(x, y))
    {
      sPlainLabel.setVisible(true);
    }
    else if (ImageRegion.HEARTLAND.contains(x, y))
    {
      heartLabel.setVisible(true);
    }
    else if (ImageRegion.NORTHEAST.contains(x, y))
    {
      neLabel.setVisible(true);
    }
    else if (ImageRegion.SOUTHEAST.contains(x, y))
    {
      seLabel.setVisible(true);
    }
  }

  /**
   * Method specifically for the smaller map inside of main1.fxml.
   *
   * @param event Mouse clicked event
   */
  @FXML
  public void clickedRegion(MouseEvent event)
  {
    double x = event.getX();
    double y = event.getY();

    makeAllInvisible();

    if (ImageRegion.CALIFORNIA.contains(x, y))
    {
      cali.setVisible(true);
      caliLabel.setVisible(true);
      currentRegion.setText("Current Region:  "+EnumRegion.CALIFORNIA);
      System.out.println("Selected cali");
    }
    else if (ImageRegion.HEARTLAND.contains(x, y))
    {
      heartland.setVisible(true);
      heartLabel.setVisible(true);
      currentRegion.setText("Current Region:  "+EnumRegion.HEARTLAND);
      System.out.println("Selected heartland");
    }
    else if (ImageRegion.MOUNTAINST.contains(x, y))
    {
      mountSt.setVisible(true);
      mountLabel.setVisible(true);
      currentRegion.setText("Current Region:  "+EnumRegion.MOUNTAIN);
      System.out.println("Selected Mountain States");
    }
    else if (ImageRegion.NORTHPLAINS.contains(x, y))
    {
      nPlains.setVisible(true);
      nPlainLabel.setVisible(true);
      currentRegion.setText("Current Region:  "+EnumRegion.NORTHERN_PLAINS);
      System.out.println("Selected North Plains");
    }
    else if (ImageRegion.NORTHEAST.contains(x, y))
    {
      northSt.setVisible(true);
      neLabel.setVisible(true);
      currentRegion.setText("Current Region:  "+EnumRegion.NORTHERN_CRESCENT);
      System.out.println("Selected Northeast");
    }
    else if (ImageRegion.SOUTHEAST.contains(x, y))
    {
      southEast.setVisible(true);
      seLabel.setVisible(true);
      currentRegion.setText("Current Region:  "+EnumRegion.SOUTHEAST);
      System.out.println("Selected Southeast");

    }
    else if (ImageRegion.SOUTHPLAINS.contains(x, y))
    {
      sPlains.setVisible(true);
      sPlainLabel.setVisible(true);
      currentRegion.setText("Current Region:  "+EnumRegion.SOUTHERN_PLAINS);
      System.out.println("Selected South Plains");
    }

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

  private void hideRegionLabels()
  {
    caliLabel.setVisible(false);
    mountLabel.setVisible(false);
    nPlainLabel.setVisible(false);
    sPlainLabel.setVisible(false);
    heartLabel.setVisible(false);
    neLabel.setVisible(false);
    seLabel.setVisible(false);
  }

  private void hideCropLabels()
  {
    appleLabel.setVisible(false);
    grainLabel.setVisible(false);
    citrusLabel.setVisible(false);
    dairyLabel.setVisible(false);
    fishLabel.setVisible(false);
    meatLabel.setVisible(false);
    nutLabel.setVisible(false);
    oilLabel.setVisible(false);
    feedLabel.setVisible(false);
    poultryLabel.setVisible(false);
    veggieLabel.setVisible(false);
    specialLabel.setVisible(false);
  }


  @FXML
  public void findPolygon(MouseEvent event)
  {
    double x = event.getX();
    double y = event.getY();

    System.out.println(x + "," + y + ",");
  }

  public EnumRegion getSelectedRegion()
  {
    return playerRegion;
  }
}

