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
import starvationevasion.teamrocket.models.Region;


public class GuiController
{
  /* PRODUCE AND DRAWING A CARD BUTTONS */
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

  /* ALL PLAYING CARDS. 2 FOR EACH REGION */
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

  /* CARD PANE WHEN CARD IS CLICKED ON. ALLOWS FOR VOTING */
  @FXML
  private Button submitVote;
  @FXML
  private Pane CardPane;

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
  private Label supportLabel;
  @FXML
  private Label opposeLabel;
  @FXML
  private Label abstainLabel;

  /* VARIABLES TO KEEP TRACK OF USER'S VOTE FOR EACH CARD */
  private int caliSupportVotes1 = 0;
  private int caliOpposeVotes1 = 0;
  private int caliAbstainVotes1 = 0;

  private int caliSupportVotes2 = 0;
  private int caliOpposeVotes2 = 0;
  private int caliAbstainVotes2 = 0;

  private int mountSupportVotes1 = 0;
  private int mountOpposeVotes1 = 0;
  private int mountAbstainVotes1 = 0;

  private int mountSupportVotes2 = 0;
  private int mountOpposeVotes2 = 0;
  private int mountAbstainVotes2 = 0;

  private int nPlainSupportVotes1 = 0;
  private int nPlainOpposeVotes1 = 0;
  private int nPlainAbstainVotes1 = 0;

  private int nPlainSupportVotes2 = 0;
  private int nPlainOpposeVotes2 = 0;
  private int nPlainAbstainVotes2 = 0;

  private int sPlainSupportVotes1 = 0;
  private int sPlainOpposeVotes1 = 0;
  private int sPlainAbstainVotes1 = 0;

  private int sPlainSupportVotes2 = 0;
  private int sPlainOpposeVotes2 = 0;
  private int sPlainAbstainVotes2 = 0;

  private int neSupportVotes1 = 0;
  private int neOpposeVotes1 = 0;
  private int neAbstainVotes1 = 0;

  private int neSupportVotes2 = 0;
  private int neOpposeVotes2 = 0;
  private int neAbstainVotes2 = 0;

  private int seSupportVotes1 = 0;
  private int seOpposeVotes1 = 0;
  private int seAbstainVotes1 = 0;

  private int seSupportVotes2 = 0;
  private int seOpposeVotes2 = 0;
  private int seAbstainVotes2 = 0;

  private int heartSupportVotes1 = 0;
  private int heartOpposeVotes1 = 0;
  private int heartAbstainVotes1 = 0;

  private int heartSupportVotes2 = 0;
  private int heartOpposeVotes2 = 0;
  private int heartAbstainVotes2 = 0;

  /* PRODUCE INFORMATION WINDOWS */
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

  /* BEGINNING SCENES AND THEIR BUTTONS */
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



  private EnumRegion playerRegion;
  private Pane statisticsPane;

  private Player player;


  private boolean caliSelected;
  private boolean heartlandSelected;
  private boolean mountainSelected;
  private boolean nPlainSelected;
  private boolean northeastSelected;
  private boolean southeastSelected;
  private boolean sPlainSelected;


  private boolean caliCard1Selected = false;
  private boolean caliCard2Selected = false;
  private boolean mountCard1Selected = false;
  private boolean mountCard2Selected = false;
  private boolean nPlainCard1Selected = false;
  private boolean nPlainCard2Selected = false;
  private boolean sPlainCard1Selected = false;
  private boolean sPlainCard2Selected = false;
  private boolean neCard1Selected = false;
  private boolean neCard2Selected = false;
  private boolean seCard1Selected = false;
  private boolean seCard2Selected = false;
  private boolean heartCard1Selected = false;
  private boolean heartCard2Selected = false;

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
      caliCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(caliSupportVotes1, caliOpposeVotes1, caliAbstainVotes1);
    }
    else if(button == caliCard2)
    {
      caliCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(caliSupportVotes2, caliOpposeVotes2, caliAbstainVotes2);
    }
    else if(button == mountCard1)
    {
      mountCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(mountSupportVotes1, mountOpposeVotes1, mountAbstainVotes1);
    }
    else if(button == mountCard2)
    {
      mountCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(mountSupportVotes2, mountOpposeVotes2, mountAbstainVotes2);
    }
    else if(button == nPlainCard1)
    {
      nPlainCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(nPlainSupportVotes1, nPlainOpposeVotes1, nPlainAbstainVotes1);
    }
    else if(button == nPlainCard2)
    {
      nPlainCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(nPlainSupportVotes2, nPlainOpposeVotes2, nPlainAbstainVotes2);
    }
    else if(button == sPlainCard1)
    {
      sPlainCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(sPlainSupportVotes1, sPlainOpposeVotes1, sPlainAbstainVotes1);
    }
    else if(button == sPlainCard2)
    {
      sPlainCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(sPlainSupportVotes2, sPlainOpposeVotes2, sPlainAbstainVotes2);
    }
    else if(button == neCard1)
    {
      neCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(neSupportVotes1, neOpposeVotes1, neAbstainVotes1);
    }
    else if(button == neCard2)
    {
      neCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(neSupportVotes2, neOpposeVotes2, neAbstainVotes2);
    }
    else if(button == seCard1)
    {
      seCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(seSupportVotes1, seOpposeVotes1, seAbstainVotes1);
    }
    else if(button == seCard2)
    {
      seCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(seSupportVotes2, seOpposeVotes2, seAbstainVotes2);
    }
    else if(button == heartCard1)
    {
      heartCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(heartSupportVotes1, heartOpposeVotes1, heartAbstainVotes1);
    }
    else if(button == heartCard2)
    {
      heartCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(heartSupportVotes2, heartOpposeVotes2, heartAbstainVotes2);
    }

    else if(button == submitVote)
    {
      CardPane.setVisible(false);
      deselectCards();
      //update main labels
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

  private void updateLabels(int supportVotes, int opposeVotes, int abstainVotes)
  {
    sVotes.setText("" + supportVotes);
    oVotes.setText("" + opposeVotes);
    aVotes.setText("" + abstainVotes);

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

  public void highlightMyRegion(EnumRegion myRegion)
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
      if(caliCard1Selected)
      {
        caliSupportVotes1 = 1;
        resetVotesAndLabels(caliAbstainVotes1, caliOpposeVotes1, aVotes, oVotes);
        caliAbstainVotes1 = 0;
        caliOpposeVotes1 = 0;
        sVotes.setText("" + caliSupportVotes1);
      }
      else if(caliCard2Selected)
      {
        caliSupportVotes2 = 1;
        resetVotesAndLabels(caliAbstainVotes2, caliOpposeVotes2, aVotes, oVotes);
        caliAbstainVotes2 = 0;
        caliOpposeVotes2 = 0;
        sVotes.setText("" + caliSupportVotes2);
      }
      else if(mountCard1Selected)
      {
        mountSupportVotes1 = 1;
        resetVotesAndLabels(mountAbstainVotes1, mountOpposeVotes1, aVotes, oVotes);
        mountAbstainVotes1 = 0;
        mountOpposeVotes1 = 0;
        sVotes.setText("" + mountSupportVotes1);
      }
      else if(mountCard2Selected)
      {
        mountSupportVotes2 = 1;
        resetVotesAndLabels(mountAbstainVotes2, mountOpposeVotes2, aVotes, oVotes);
        mountAbstainVotes2 = 0;
        mountOpposeVotes2 = 0;
        sVotes.setText("" + mountSupportVotes2);
      }
      else if(nPlainCard1Selected)
      {
        nPlainSupportVotes1 = 1;
        resetVotesAndLabels(nPlainAbstainVotes1, nPlainOpposeVotes1, aVotes, oVotes);
        nPlainAbstainVotes1 = 0;
        nPlainOpposeVotes1 = 0;
        sVotes.setText("" + nPlainSupportVotes1);
      }
      else if(nPlainCard2Selected)
      {
        nPlainSupportVotes2 = 1;
        resetVotesAndLabels(nPlainAbstainVotes2, nPlainOpposeVotes2, aVotes, oVotes);
        nPlainAbstainVotes2 = 0;
        nPlainOpposeVotes2 = 0;
        sVotes.setText("" + nPlainSupportVotes2);
      }
      else if(sPlainCard1Selected)
      {
        sPlainSupportVotes1 = 1;
        resetVotesAndLabels(sPlainAbstainVotes1, sPlainOpposeVotes1, aVotes, oVotes);
        sPlainAbstainVotes1 = 0;
        sPlainOpposeVotes1 = 0;
        sVotes.setText("" + sPlainSupportVotes1);
      }
      else if(sPlainCard2Selected)
      {
        sPlainSupportVotes2 = 1;
        resetVotesAndLabels(sPlainAbstainVotes2, sPlainOpposeVotes2, aVotes, oVotes);
        sPlainAbstainVotes2 = 0;
        sPlainOpposeVotes2 = 0;
        sVotes.setText("" + sPlainSupportVotes2);
      }
      else if(neCard1Selected)
      {
        neSupportVotes1 = 1;
        resetVotesAndLabels(neAbstainVotes1, neOpposeVotes1, aVotes, oVotes);
        neAbstainVotes1 = 0;
        neOpposeVotes1 = 0;
        sVotes.setText("" + neSupportVotes1);
      }
      else if(neCard2Selected)
      {
        neSupportVotes2 = 1;
        resetVotesAndLabels(neAbstainVotes2, neOpposeVotes2, aVotes, oVotes);
        neAbstainVotes2 = 0;
        neOpposeVotes2 = 0;
        sVotes.setText("" + neSupportVotes2);
      }
      else if(seCard1Selected)
      {
        seSupportVotes1 = 1;
        resetVotesAndLabels(seAbstainVotes1, seOpposeVotes1, aVotes, oVotes);
        seAbstainVotes1 = 0;
        seOpposeVotes1 = 0;
        sVotes.setText("" + seSupportVotes1);
      }
      else if(seCard2Selected)
      {
        seSupportVotes2 = 1;
        resetVotesAndLabels(seAbstainVotes2, seOpposeVotes2, aVotes, oVotes);
        seAbstainVotes2 = 0;
        seOpposeVotes2 = 0;
        sVotes.setText("" + seSupportVotes2);
      }
      else if(heartCard1Selected)
      {
        heartSupportVotes1 = 1;
        resetVotesAndLabels(heartAbstainVotes1, heartOpposeVotes1, aVotes, oVotes);
        heartAbstainVotes1 = 0;
        heartOpposeVotes1 = 0;
        sVotes.setText("" + heartSupportVotes1);}
      else if(heartCard2Selected)
      {
        heartSupportVotes2 = 1;
        resetVotesAndLabels(heartAbstainVotes2, heartOpposeVotes2, aVotes, oVotes);
        heartAbstainVotes2 = 0;
        heartOpposeVotes2 = 0;
        sVotes.setText("" + heartSupportVotes2);
      }
    }
    else if(button == oppose)
    {
      if(caliCard1Selected)
      {
        caliOpposeVotes1 = 1;
        resetVotesAndLabels(caliSupportVotes1, caliAbstainVotes1, sVotes, aVotes);
        caliSupportVotes1 = 0;
        caliAbstainVotes1 = 0;
        oVotes.setText("" + caliOpposeVotes1);
      }
      else if(caliCard2Selected)
      {
        caliOpposeVotes2 = 1;
        resetVotesAndLabels(caliSupportVotes2, caliAbstainVotes2, sVotes, aVotes);
        caliSupportVotes2 = 0;
        caliAbstainVotes2 = 0;
        oVotes.setText("" + caliOpposeVotes2);
      }
      else if(mountCard1Selected)
      {
        mountOpposeVotes1 = 1;
        resetVotesAndLabels(mountSupportVotes1, mountAbstainVotes1, sVotes, aVotes);
        mountSupportVotes1 = 0;
        mountAbstainVotes1 = 0;
        oVotes.setText("" + mountOpposeVotes1);
      }
      else if(mountCard2Selected)
      {
        mountOpposeVotes2 = 1;
        resetVotesAndLabels(mountSupportVotes2, mountAbstainVotes2, sVotes, aVotes);
        mountSupportVotes2 = 0;
        mountAbstainVotes2 = 0;
        oVotes.setText("" + mountOpposeVotes2);
      }
      else if(nPlainCard1Selected)
      {
        nPlainOpposeVotes1 = 1;
        resetVotesAndLabels(nPlainSupportVotes1, nPlainAbstainVotes1, sVotes, aVotes);
        nPlainSupportVotes1 = 0;
        nPlainAbstainVotes1 = 0;
        oVotes.setText("" + nPlainOpposeVotes1);
      }
      else if(nPlainCard2Selected)
      {
        nPlainOpposeVotes2 = 1;
        resetVotesAndLabels(nPlainSupportVotes2, nPlainAbstainVotes2, sVotes, aVotes);
        nPlainSupportVotes2 = 0;
        nPlainAbstainVotes2 = 0;
        oVotes.setText("" + nPlainOpposeVotes2);
      }
      else if(sPlainCard1Selected)
      {
        sPlainOpposeVotes1 = 1;
        resetVotesAndLabels(sPlainSupportVotes1, sPlainAbstainVotes1, sVotes, aVotes);
        sPlainSupportVotes1 = 0;
        sPlainAbstainVotes1 = 0;
        oVotes.setText("" + sPlainOpposeVotes1);
      }
      else if(sPlainCard2Selected)
      {
        sPlainOpposeVotes2 = 1;
        resetVotesAndLabels(sPlainSupportVotes2, sPlainAbstainVotes2, sVotes, aVotes);
        sPlainSupportVotes2 = 0;
        sPlainAbstainVotes2 = 0;
        oVotes.setText("" + sPlainOpposeVotes2);
      }
      else if(neCard1Selected)
      {
        neOpposeVotes1 = 1;
        resetVotesAndLabels(neSupportVotes1, neAbstainVotes1, sVotes, aVotes);
        neSupportVotes1 = 0;
        neAbstainVotes1 = 0;
        oVotes.setText("" + neOpposeVotes1);
      }
      else if(neCard2Selected)
      {
        neOpposeVotes1 = 1;
        resetVotesAndLabels(neSupportVotes1, neAbstainVotes1, sVotes, aVotes);
        neSupportVotes1 = 0;
        neAbstainVotes1 = 0;
        oVotes.setText("" + neOpposeVotes1);
      }
      else if(seCard1Selected)
      {
        seOpposeVotes1 = 1;
        resetVotesAndLabels(seSupportVotes1, seAbstainVotes1, sVotes, aVotes);
        seSupportVotes1 = 0;
        seAbstainVotes1 = 0;
        oVotes.setText("" + seOpposeVotes1);
      }
      else if(seCard2Selected)
      {
        seOpposeVotes2 = 1;
        resetVotesAndLabels(seSupportVotes2, seAbstainVotes2, sVotes, aVotes);
        seSupportVotes2 = 0;
        seAbstainVotes2 = 0;
        oVotes.setText("" + seOpposeVotes2);
      }
      else if(heartCard1Selected)
      {
        heartOpposeVotes1 = 1;
        resetVotesAndLabels(heartSupportVotes1, heartAbstainVotes1, sVotes, aVotes);
        heartSupportVotes1 = 0;
        heartAbstainVotes1 = 0;
        oVotes.setText("" + heartOpposeVotes1);
      }
      else if(heartCard2Selected)
      {
        heartOpposeVotes2 = 1;
        resetVotesAndLabels(heartSupportVotes2, heartAbstainVotes2, sVotes, aVotes);
        heartSupportVotes2 = 0;
        heartAbstainVotes2 = 0;
        oVotes.setText("" + heartOpposeVotes2);
      }
    }
    else if(button == abstain)
    {
      if(caliCard1Selected)
      {
        caliAbstainVotes1 = 1;
        resetVotesAndLabels(caliSupportVotes1, caliOpposeVotes1, sVotes, oVotes);
        caliSupportVotes1 = 0;
        caliOpposeVotes1 = 0;
        aVotes.setText("" + caliAbstainVotes1);
      }
      else if(caliCard2Selected)
      {
        caliAbstainVotes2 = 1;
        resetVotesAndLabels(caliSupportVotes2, caliOpposeVotes2, sVotes, oVotes);
        caliSupportVotes2 = 0;
        caliOpposeVotes2 = 0;
        aVotes.setText("" + caliAbstainVotes2);
      }
      else if(mountCard1Selected)
      {
        mountAbstainVotes1 = 1;
        resetVotesAndLabels(mountSupportVotes1, mountOpposeVotes1, sVotes, oVotes);
        mountSupportVotes1 = 0;
        mountOpposeVotes1 = 0;
        aVotes.setText("" + mountAbstainVotes1);
      }
      else if(mountCard2Selected)
      {
        mountAbstainVotes2 = 1;
        resetVotesAndLabels(mountSupportVotes2, mountOpposeVotes2, sVotes, oVotes);
        mountSupportVotes2 = 0;
        mountOpposeVotes2 = 0;
        aVotes.setText("" + mountAbstainVotes2);
      }
      else if(nPlainCard1Selected)
      {
        nPlainAbstainVotes1 = 1;
        resetVotesAndLabels(nPlainSupportVotes1, nPlainOpposeVotes1, sVotes, oVotes);
        nPlainSupportVotes1 = 0;
        nPlainOpposeVotes1 = 0;
        aVotes.setText("" + nPlainAbstainVotes1);
      }
      else if(nPlainCard2Selected)
      {
        nPlainAbstainVotes2 = 1;
        resetVotesAndLabels(nPlainSupportVotes2, nPlainOpposeVotes2, sVotes, oVotes);
        nPlainSupportVotes2 = 0;
        nPlainOpposeVotes2 = 0;
        aVotes.setText("" + nPlainAbstainVotes2);
      }
      else if(sPlainCard1Selected)
      {
        sPlainAbstainVotes1 = 1;
        resetVotesAndLabels(sPlainSupportVotes1, sPlainOpposeVotes1, sVotes, oVotes);
        sPlainSupportVotes1 = 0;
        sPlainOpposeVotes1 = 0;
        aVotes.setText("" + sPlainAbstainVotes1);
      }
      else if(sPlainCard2Selected)
      {
        sPlainAbstainVotes2 = 1;
        resetVotesAndLabels(sPlainSupportVotes2, sPlainOpposeVotes2, sVotes, oVotes);
        sPlainSupportVotes2 = 0;
        sPlainOpposeVotes2 = 0;
        aVotes.setText("" + sPlainAbstainVotes2);
      }
      else if(neCard1Selected)
      {
        neAbstainVotes1 = 1;
        resetVotesAndLabels(neSupportVotes1, neOpposeVotes1, sVotes, oVotes);
        neSupportVotes1 = 0;
        neOpposeVotes1 = 0;
        aVotes.setText("" + neAbstainVotes1);
      }
      else if(neCard2Selected)
      {
        neAbstainVotes2 = 1;
        resetVotesAndLabels(neSupportVotes2, neOpposeVotes2, sVotes, oVotes);
        neSupportVotes2 = 0;
        neOpposeVotes2 = 0;
        aVotes.setText("" + neAbstainVotes2);
      }
      else if(seCard1Selected)
      {
        seAbstainVotes1 = 1;
        resetVotesAndLabels(seSupportVotes1, seOpposeVotes1, sVotes, oVotes);
        seSupportVotes1 = 0;
        seOpposeVotes1 = 0;
        aVotes.setText("" + seAbstainVotes1);
      }
      else if(seCard2Selected)
      {
        seAbstainVotes2 = 1;
        resetVotesAndLabels(seSupportVotes2, seOpposeVotes2, sVotes, oVotes);
        seSupportVotes2 = 0;
        seOpposeVotes2 = 0;
        aVotes.setText("" + seAbstainVotes2);
      }
      else if(heartCard1Selected)
      {
        heartAbstainVotes1 = 1;
        resetVotesAndLabels(heartSupportVotes1, heartOpposeVotes1, sVotes, oVotes);
        heartSupportVotes1 = 0;
        heartOpposeVotes1 = 0;
        aVotes.setText("" + heartAbstainVotes1);
      }
      else if(heartCard2Selected)
      {
        heartAbstainVotes2 = 1;
        resetVotesAndLabels(heartSupportVotes2, heartOpposeVotes2, sVotes, oVotes);
        heartSupportVotes2 = 0;
        heartOpposeVotes2 = 0;
        aVotes.setText("" + heartAbstainVotes2);
      }
    }
//    System.out.println("support 1 = "+caliSupportVotes1);
//    System.out.println("oppose 1 = "+caliOpposeVotes1);
//    System.out.println("abstain 1 = "+caliAbstainVotes1);
//    System.out.println("*********************************");
//    System.out.println("support 2 = "+caliSupportVotes2);
//    System.out.println("oppose 2 = "+caliOpposeVotes2);
//    System.out.println("abstain 2 = "+caliAbstainVotes2);
//    System.out.println("*********************************");


  }


  private void deselectCards()
  {
    caliCard1Selected = false;
    caliCard2Selected = false;
    mountCard1Selected = false;
    mountCard2Selected = false;
    nPlainCard1Selected = false;
    nPlainCard2Selected = false;
    sPlainCard1Selected = false;
    sPlainCard2Selected = false;
    neCard1Selected = false;
    neCard2Selected = false;
    seCard1Selected = false;
    seCard2Selected = false;
    heartCard1Selected = false;
    heartCard2Selected = false;
  }

  private void resetVotesAndLabels(int reset1, int reset2, Label text1, Label text2)
  {
    reset1 = 0;
    reset2 = 0;

    text1.setText(""+reset1);
    text2.setText(""+reset2);

  }

  @FXML
  public void mouseOverVote(MouseEvent event)
  {
    if(event.getSource() == support)
    {
      supportLabel.setVisible(true);
    }
    else if(event.getSource() == oppose)
    {
      opposeLabel.setVisible(true);
    }
    else if(event.getSource() == abstain)
    {
     abstainLabel.setVisible(true);
    }
  }

  @FXML
  public void mouseExitVote()
  {
    supportLabel.setVisible(false);
    opposeLabel.setVisible(false);
    abstainLabel.setVisible(false);
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
    supportLabel.setVisible(false);
    opposeLabel.setVisible(false);
    abstainLabel.setVisible(false);
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
   * Method specifically for the smaller map inside of cardDraft.fxml.
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
      currentRegion.setText("Current Region:  " + EnumRegion.CALIFORNIA);
      //statisticsPane.getChildren().add(CropChart.makePieChart(new Region(playerRegion)));
      //CropChart.makePieChart();
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

