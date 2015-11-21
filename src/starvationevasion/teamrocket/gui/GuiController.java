package starvationevasion.teamrocket.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.main.Main;
import starvationevasion.teamrocket.messages.GameState;
import starvationevasion.teamrocket.models.Player;

import java.util.ArrayList;


public class GuiController
{
  /* PRODUCE AND DRAWING A CARD BUTTONS */
  @FXML
  private Button drawCardButton;
  @FXML
  private Button appleButton, grainsButton, citrusButton, feedButton, dairyButton, 
                 fishButton, meatButton, nutButton, oilButton, poultryButton, veggieButton, specialButton;

  /* ALL PLAYING CARDS. 2 FOR EACH REGION */
  @FXML
  private Button caliCard1, caliCard2, mountCard1, mountCard2, nPlainCard1, nPlainCard2, sPlainCard1, 
                 sPlainCard2, heartCard1, heartCard2, neCard1, neCard2, seCard1, seCard2;

  /* CARD PANE WHEN CARD IS CLICKED ON. ALLOWS FOR VOTING */
  @FXML
  private Button submitVote;
  @FXML
  private Pane CardPane;

  @FXML
  private Button support, oppose, abstain;

  @FXML
  private Label sVotes, oVotes, aVotes;

  @FXML
  private Label supportLabel, opposeLabel, abstainLabel;

  /* VARIABLES TO KEEP TRACK OF USER'S VOTE FOR EACH CARD */
  private int caliSupportVotes1, caliOpposeVotes1, caliAbstainVotes1 = 0;

  private int caliSupportVotes2, caliOpposeVotes2, caliAbstainVotes2 = 0;

  private int mountSupportVotes1, mountOpposeVotes1, mountAbstainVotes1 = 0;

  private int mountSupportVotes2, mountOpposeVotes2, mountAbstainVotes2 = 0;

  private int nPlainSupportVotes1, nPlainOpposeVotes1, nPlainAbstainVotes1 = 0;

  private int nPlainSupportVotes2, nPlainOpposeVotes2, nPlainAbstainVotes2 = 0;

  private int sPlainSupportVotes1, sPlainOpposeVotes1, sPlainAbstainVotes1 = 0;

  private int sPlainSupportVotes2, sPlainOpposeVotes2, sPlainAbstainVotes2 = 0;

  private int neSupportVotes1, neOpposeVotes1, neAbstainVotes1 = 0;

  private int neSupportVotes2, neOpposeVotes2, neAbstainVotes2 = 0;

  private int seSupportVotes1, seOpposeVotes1, seAbstainVotes1 = 0;

  private int seSupportVotes2, seOpposeVotes2, seAbstainVotes2 = 0;

  private int heartSupportVotes1, heartOpposeVotes1, heartAbstainVotes1 = 0;

  private int heartSupportVotes2, heartOpposeVotes2, heartAbstainVotes2 = 0;

  /* VOTING LABELS THAT SHOW HOW MANY VOTES EVERYONE GAVE */
  @FXML
  private HBox c1Votes, c2Votes, m1Votes, m2Votes, np1Votes, np2Votes, ne1Votes, ne2Votes, s1Votes, s2Votes, 
               sp1Votes, sp2Votes, h1Votes, h2Votes;

  @FXML
  private Rectangle c1rect, c2rect, m1rect, m2rect, np1rect, np2rect, ne1rect, ne2rect, sp1rect, sp2rect, se1rect,
                    se2rect, h1rect, h2rect;

  //Actual labels displaying total votes
  @FXML
  private Label c1support, c1oppose, c1abstain;
  @FXML
  private Label c2abstain, c2support, c2oppose;
  @FXML
  private Label m1support, m1oppose, m1abstain;
  @FXML
  private Label m2support, m2oppose, m2abstain;
  @FXML
  private Label ne1support, ne1oppose, ne1abstain;
  @FXML
  private Label ne2support, ne2oppose, ne2abstain;
  @FXML
  private Label np1support, np1oppose, np1abstain;
  @FXML
  private Label np2support, np2oppose, np2abstain;
  @FXML
  private Label se1abstain, se1support, se1oppose;
  @FXML
  private Label se2abstain, se2support, se2oppose;
  @FXML
  private Label sp1abstain, sp1support, sp1oppose;
  @FXML
  private Label sp2abstain, sp2support, sp2oppose;
  @FXML
  private Label h1support, h1oppose, h1abstain;
  @FXML
  private Label h2support,h2oppose,h2abstain;
  
  private int supc1votes,oppc1votes,abstc1votes = 0;
  private int supc2votes,oppc2votes,abstc2votes = 0;
  private int supm1votes,oppm1votes,abstm1votes = 0;
  private int supm2votes,oppm2votes,abstm2votes = 0;
  private int supnp1votes,oppnp1votes,abstnp1votes = 0;
  private int supnp2votes,oppnp2votes,abstnp2votes = 0;
  private int supne1votes,oppne1votes,abstne1votes = 0;
  private int supne2votes,oppne2votes,abstne2votes = 0;
  private int supsp1votes,oppsp1votes,abstsp1votes = 0;
  private int supsp2votes,oppsp2votes,abstsp2votes = 0;
  private int supse1votes,oppse1votes,abstse1votes = 0;
  private int supse2votes,oppse2votes,abstse2votes = 0;
  private int suph1votes,opph1votes,absth1votes = 0;
  private int suph2votes,opph2votes,absth2votes = 0;
  


  /* PRODUCE INFORMATION WINDOWS */
  @FXML
  private Pane appleWindow, grainWindow, citrusWindow, feedWindow, meatWindow, dairyWindow, nutWindow, oilWindow,
               poultryWindow, veggieWindow, specialWindow, fishWindow;
  @FXML
  private Button closeWindow, closeWindow2, closeWindow3, closeWindow4, closeWindow5, closeWindow6, closeWindow7,
                 closeWindow8, closeWindow9, closeWindow10, closeWindow11, closeWindow12;
  @FXML
  private Label close, close2, close3, close4, close5, close6, close7, close8, close9, close10, close11, close12;

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
  private Button login;

/* HIGHLIGHTED IMAGES OF THE REGIONS */
  @FXML
  private ImageView cali, heartland, mountSt, northSt, nPlains, sPlains, southEast;

  @FXML
  private Label mainLabel;
  @FXML
  private Label time;

  /* REGION LABELS */
  @FXML
  private Label caliLabel, seLabel, neLabel, mountLabel, heartLabel, sPlainLabel, nPlainLabel;
  @FXML
  private Label nothingSelected;
  @FXML
  private Label currentRegion;

  /* CROP LABELS */
  @FXML
  private Label appleLabel, grainLabel, citrusLabel, feedLabel, dairyLabel, fishLabel, meatLabel, nutLabel,
                oilLabel, poultryLabel, veggieLabel, specialLabel;

  @FXML
  private Label playerRegion;

  private EnumRegion myRegion;

  @FXML
  private BorderPane statisticsPane;

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

  private boolean votable = false;

  @FXML
  public void highlightCards()
  {
    if(Main.gameController.getMyRegion() == EnumRegion.CALIFORNIA)
    {
      Color purple = new Color(.69,.59,.72,1);
      c1rect.setFill(purple);
      c2rect.setFill(purple);
      m1rect.setFill(purple);
      m2rect.setFill(purple);
      np1rect.setFill(purple);
      np2rect.setFill(purple);
      ne1rect.setFill(purple);
      ne2rect.setFill(purple);
      sp1rect.setFill(purple);
      sp2rect.setFill(purple);
      se1rect.setFill(purple);
      se2rect.setFill(purple);
      h1rect.setFill(purple);
      h2rect.setFill(purple);
    }
    else if(Main.gameController.getMyRegion() == EnumRegion.MOUNTAIN)
    {
      Color brown = new Color(.68,.54,.43,1);
      c1rect.setFill(brown);
      c2rect.setFill(brown);
      m1rect.setFill(brown);
      m2rect.setFill(brown);
      np1rect.setFill(brown);
      np2rect.setFill(brown);
      ne1rect.setFill(brown);
      ne2rect.setFill(brown);
      sp1rect.setFill(brown);
      sp2rect.setFill(brown);
      se1rect.setFill(brown);
      se2rect.setFill(brown);
      h1rect.setFill(brown);
      h2rect.setFill(brown);
    }
    else if(Main.gameController.getMyRegion() == EnumRegion.NORTHERN_PLAINS)
    {
      Color blue = new Color(.7,.81,.89,1);
      c1rect.setFill(blue);
      c2rect.setFill(blue);
      m1rect.setFill(blue);
      m2rect.setFill(blue);
      np1rect.setFill(blue);
      np2rect.setFill(blue);
      ne1rect.setFill(blue);
      ne2rect.setFill(blue);
      sp1rect.setFill(blue);
      sp2rect.setFill(blue);
      se1rect.setFill(blue);
      se2rect.setFill(blue);
      h1rect.setFill(blue);
      h2rect.setFill(blue);
    }
    else if(Main.gameController.getMyRegion() == EnumRegion.SOUTHERN_PLAINS)
    {
      Color red = new Color(.91,.6,.6,1);
      c1rect.setFill(red);
      c2rect.setFill(red);
      m1rect.setFill(red);
      m2rect.setFill(red);
      np1rect.setFill(red);
      np2rect.setFill(red);
      ne1rect.setFill(red);
      ne2rect.setFill(red);
      sp1rect.setFill(red);
      sp2rect.setFill(red);
      se1rect.setFill(red);
      se2rect.setFill(red);
      h1rect.setFill(red);
      h2rect.setFill(red);
    }
    else if(Main.gameController.getMyRegion() == EnumRegion.HEARTLAND)
    {
      Color yellow = new Color(.99,.92,.62,1);
      c1rect.setFill(yellow);
      c2rect.setFill(yellow);
      m1rect.setFill(yellow);
      m2rect.setFill(yellow);
      np1rect.setFill(yellow);
      np2rect.setFill(yellow);
      ne1rect.setFill(yellow);
      ne2rect.setFill(yellow);
      sp1rect.setFill(yellow);
      sp2rect.setFill(yellow);
      se1rect.setFill(yellow);
      se2rect.setFill(yellow);
      h1rect.setFill(yellow);
      h2rect.setFill(yellow);
    }
    else if(Main.gameController.getMyRegion() == EnumRegion.NORTHERN_CRESCENT)
    {
      Color green = new Color(.75,.8,.62,1);
      c1rect.setFill(green);
      c2rect.setFill(green);
      m1rect.setFill(green);
      m2rect.setFill(green);
      np1rect.setFill(green);
      np2rect.setFill(green);
      ne1rect.setFill(green);
      ne2rect.setFill(green);
      sp1rect.setFill(green);
      sp2rect.setFill(green);
      se1rect.setFill(green);
      se2rect.setFill(green);
      h1rect.setFill(green);
      h2rect.setFill(green);
    }
    else if(Main.gameController.getMyRegion() == EnumRegion.SOUTHEAST)
    {
      Color orange = new Color(.97,.66,.37,1);
      c1rect.setFill(orange);
      c2rect.setFill(orange);
      m1rect.setFill(orange);
      m2rect.setFill(orange);
      np1rect.setFill(orange);
      np2rect.setFill(orange);
      ne1rect.setFill(orange);
      ne2rect.setFill(orange);
      sp1rect.setFill(orange);
      sp2rect.setFill(orange);
      se1rect.setFill(orange);
      se2rect.setFill(orange);
      h1rect.setFill(orange);
      h2rect.setFill(orange);
    }
    disableCards();
    if(isVotable(caliCard1))
    {
      caliCard1.setDisable(false);
      c1rect.setVisible(true);
    }
    if(isVotable(caliCard2))
    {
      caliCard2.setDisable(false);
      c2rect.setVisible(true);
    }
    if(isVotable(mountCard1))
    {
      mountCard1.setDisable(false);
      m1rect.setVisible(true);
    }
    if(isVotable(mountCard2))
    {
      mountCard2.setDisable(false);
      m2rect.setVisible(true);
    }
    if(isVotable(nPlainCard1))
    {
      nPlainCard1.setDisable(false);
      np1rect.setVisible(true);
    }
    if(isVotable(nPlainCard2))
    {
      nPlainCard2.setDisable(false);
      np2rect.setVisible(true);
    }
    if(isVotable(neCard1))
    {
      neCard1.setDisable(false);
      ne1rect.setVisible(true);
    }
    if(isVotable(neCard2))
    {
      neCard2.setDisable(false);
      ne2rect.setVisible(true);
    }
    if(isVotable(sPlainCard1))
    {
      sPlainCard1.setDisable(false);
      sp1rect.setVisible(true);
    }
    if(isVotable(sPlainCard2))
    {
      sPlainCard2.setDisable(false);
      sp2rect.setVisible(true);
    }
    if(isVotable(seCard1))
    {
      seCard1.setDisable(false);
      se1rect.setVisible(true);
    }
    if(isVotable(seCard2))
    {
      seCard2.setDisable(false);
      se2rect.setVisible(true);
    }
    if(isVotable(heartCard1))
    {
      heartCard1.setDisable(false);
      h1rect.setVisible(true);
    }
    if(isVotable(heartCard2))
    {
      heartCard2.setDisable(false);
      h2rect.setVisible(true);
    }
  }


  private void disableCards()
  {
    caliCard1.setDisable(true);
    c1rect.setVisible(false);

    caliCard2.setDisable(true);
    c2rect.setVisible(false);

    mountCard1.setDisable(true);
    m1rect.setVisible(false);

    mountCard2.setDisable(true);
    m2rect.setVisible(false);

    nPlainCard1.setDisable(true);
    np1rect.setVisible(false);

    nPlainCard2.setDisable(true);
    np2rect.setVisible(false);

    neCard1.setDisable(true);
    ne1rect.setVisible(false);

    neCard2.setDisable(true);
    ne2rect.setVisible(false);

    seCard1.setDisable(true);
    se1rect.setVisible(false);

    seCard2.setDisable(true);
    se1rect.setVisible(false);

    sPlainCard1.setDisable(true);
    sp1rect.setVisible(false);

    sPlainCard2.setDisable(true);
    sp1rect.setVisible(false);

    heartCard1.setDisable(true);
    h1rect.setVisible(false);

    heartCard2.setDisable(true);
    h1rect.setVisible(false);

  }

  private boolean isVotable(Button card)
  {
    if(card == caliCard1 ) return true;
    if(card == caliCard2) return false;
    if(card == mountCard1) return false;
    if(card == mountCard2) return true;
    if(card == nPlainCard1) return false;
    if(card == nPlainCard2) return false;
    if(card == neCard1) return true;
    if(card == neCard2) return false;
    if(card == sPlainCard1) return true;
    if(card == sPlainCard2) return false;
    if(card == seCard1) return false;
    if(card == seCard2) return true;
    if(card == heartCard1) return true;
    if(card == heartCard2) return false;

    else return false;
  }

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
      //switch to login scene
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
    else if(button == login)
    {
      //save input
      //go to waiting scene
    }
    else if(button == pickedRegion)
    {
      myRegion = saveRegion();
      System.out.println("my region is now: " + myRegion);
      if(myRegion == null)
      {
        return;
      }

      //Go to next scene
      try
      {
        this.player = Main.gameController.startNewGame(this.myRegion);

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
      hideAllVoteLabels();
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
      c1Votes.setVisible(true);
    }
    else if(button == caliCard2)
    {
      caliCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(caliSupportVotes2, caliOpposeVotes2, caliAbstainVotes2);
      c2Votes.setVisible(true);
    }
    else if(button == mountCard1)
    {
      mountCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(mountSupportVotes1, mountOpposeVotes1, mountAbstainVotes1);
      m1Votes.setVisible(true);
    }
    else if(button == mountCard2)
    {
      mountCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(mountSupportVotes2, mountOpposeVotes2, mountAbstainVotes2);
      m2Votes.setVisible(true);
    }
    else if(button == nPlainCard1)
    {
      nPlainCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(nPlainSupportVotes1, nPlainOpposeVotes1, nPlainAbstainVotes1);
      np1Votes.setVisible(true);
    }
    else if(button == nPlainCard2)
    {
      nPlainCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(nPlainSupportVotes2, nPlainOpposeVotes2, nPlainAbstainVotes2);
      np2Votes.setVisible(true);
    }
    else if(button == sPlainCard1)
    {
      sPlainCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(sPlainSupportVotes1, sPlainOpposeVotes1, sPlainAbstainVotes1);
      sp1Votes.setVisible(true);
    }
    else if(button == sPlainCard2)
    {
      sPlainCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(sPlainSupportVotes2, sPlainOpposeVotes2, sPlainAbstainVotes2);
      sp2Votes.setVisible(true);
    }
    else if(button == neCard1)
    {
      neCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(neSupportVotes1, neOpposeVotes1, neAbstainVotes1);
      ne1Votes.setVisible(true);
    }
    else if(button == neCard2)
    {
      neCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(neSupportVotes2, neOpposeVotes2, neAbstainVotes2);
      ne2Votes.setVisible(true);
    }
    else if(button == seCard1)
    {
      seCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(seSupportVotes1, seOpposeVotes1, seAbstainVotes1);
      s1Votes.setVisible(true);
    }
    else if(button == seCard2)
    {
      seCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(seSupportVotes2, seOpposeVotes2, seAbstainVotes2);
      s2Votes.setVisible(true);
    }
    else if(button == heartCard1)
    {
      heartCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(heartSupportVotes1, heartOpposeVotes1, heartAbstainVotes1);
      h1Votes.setVisible(true);
    }
    else if(button == heartCard2)
    {
      heartCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(heartSupportVotes2, heartOpposeVotes2, heartAbstainVotes2);
      h2Votes.setVisible(true);
    }

    else if(button == submitVote)
    {
      CardPane.setVisible(false);
      if(caliCard1Selected)
      {
        supc1votes = caliSupportVotes1;
        oppc1votes = caliOpposeVotes1;
        abstc1votes = caliAbstainVotes1;

        c1support.setText(""+supc1votes);
        c1oppose.setText(""+oppc1votes);
        c1abstain.setText(""+abstc1votes);
      }
      else if(caliCard2Selected)
      {
        supc2votes = caliSupportVotes2;
        oppc2votes = caliOpposeVotes2;
        abstc2votes = caliAbstainVotes2;

        c2support.setText(""+supc2votes);
        c2oppose.setText(""+oppc2votes);
        c2abstain.setText(""+abstc2votes);
      }
      else if(mountCard1Selected)
      {
        supm1votes = mountSupportVotes1;
        oppm1votes = mountOpposeVotes1;
        abstm1votes = mountAbstainVotes1;

        m1support.setText(""+supm1votes);
        m1oppose.setText(""+oppm1votes);
        m1abstain.setText(""+abstm1votes);
      }
      else if(mountCard2Selected)
      {
        supm2votes = mountSupportVotes2;
        oppm2votes = mountOpposeVotes2;
        abstm2votes = mountAbstainVotes2;

        m2support.setText(""+supm2votes);
        m2oppose.setText(""+oppm2votes);
        m2abstain.setText(""+abstm2votes);
      }
      else if(nPlainCard1Selected)
      {
        supnp1votes = nPlainSupportVotes1;
        oppnp1votes = nPlainOpposeVotes1;
        abstnp1votes = nPlainAbstainVotes1;

        np1support.setText(""+supnp1votes);
        np1oppose.setText(""+oppnp1votes);
        np1abstain.setText(""+abstnp1votes);
      }
      else if(nPlainCard2Selected)
      {
        supnp2votes = nPlainSupportVotes2;
        oppnp2votes = nPlainOpposeVotes2;
        abstnp2votes = nPlainAbstainVotes2;

        np2support.setText(""+supnp2votes);
        np2oppose.setText(""+oppnp2votes);
        np2abstain.setText(""+abstnp2votes);
      }
      else if(neCard1Selected)
      {
        supne1votes = neSupportVotes1;
        oppne1votes = neOpposeVotes1;
        abstne1votes = neAbstainVotes1;

        ne1support.setText(""+supne1votes);
        ne1oppose.setText(""+oppne1votes);
        ne1abstain.setText(""+abstne1votes);
      }
      else if(neCard2Selected)
      {
        supne2votes = neSupportVotes2;
        oppne2votes = neOpposeVotes2;
        abstne2votes = neAbstainVotes2;

        ne2support.setText(""+supne2votes);
        ne2oppose.setText(""+oppne2votes);
        ne2abstain.setText(""+abstne2votes);
      }
      else if(sPlainCard1Selected)
      {
        supsp1votes = sPlainSupportVotes1;
        oppsp1votes = sPlainOpposeVotes1;
        abstsp1votes = sPlainAbstainVotes1;

        sp1support.setText(""+supsp1votes);
        sp1oppose.setText(""+oppsp1votes);
        sp1abstain.setText(""+abstsp1votes);
      }
      else if(sPlainCard2Selected)
      {
        supsp2votes = sPlainSupportVotes2;
        oppsp2votes = sPlainOpposeVotes2;
        abstsp2votes = sPlainAbstainVotes2;

        sp2support.setText(""+supsp2votes);
        sp2oppose.setText(""+oppsp2votes);
        sp2abstain.setText(""+abstsp2votes);
      }
      else if(seCard1Selected)
      {
        supse1votes = seSupportVotes1;
        oppse1votes = seOpposeVotes1;
        abstse1votes = seAbstainVotes1;

        se1support.setText(""+supse1votes);
        se1oppose.setText(""+oppse1votes);
        se1abstain.setText(""+abstse1votes);
      }
      else if(seCard2Selected)
      {
        supse2votes = seSupportVotes2;
        oppse2votes = seOpposeVotes2;
        abstse2votes = seAbstainVotes2;

        se2support.setText(""+supse2votes);
        se2oppose.setText(""+oppse2votes);
        se2abstain.setText(""+abstse2votes);
      }
      else if(heartCard1Selected)
      {
        suph1votes = heartSupportVotes1;
        opph1votes = heartOpposeVotes1;
        absth1votes = heartAbstainVotes1;

        h1support.setText(""+suph1votes);
        h1oppose.setText(""+opph1votes);
        h1abstain.setText(""+absth1votes);
      }
      else if(heartCard2Selected)
      {
        suph2votes = heartSupportVotes2;
        opph2votes = heartOpposeVotes2;
        absth2votes = heartAbstainVotes2;

        h2support.setText(""+suph2votes);
        h2oppose.setText(""+opph2votes);
        h2abstain.setText(""+absth2votes);
      }

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

  private void hideAllVoteLabels()
  {
    c1Votes.setVisible(false);
    c2Votes.setVisible(false);
    m1Votes.setVisible(false);
    m2Votes.setVisible(false);
    np1Votes.setVisible(false);
    np2Votes.setVisible(false);
    ne1Votes.setVisible(false);
    ne2Votes.setVisible(false);
    s1Votes.setVisible(false);
    s2Votes.setVisible(false);
    sp1Votes.setVisible(false);
    sp2Votes.setVisible(false);
    h1Votes.setVisible(false);
    h2Votes.setVisible(false);
  }

  private void updateLabels(int supportVotes, int opposeVotes, int abstainVotes)
  {
    sVotes.setText("" + supportVotes);
    oVotes.setText("" + opposeVotes);
    aVotes.setText("" + abstainVotes);

  }


  /**
   * Allows regions to be highlighted when drafting scene begins.
   */
  @FXML
  public void showMyRegion()
  {
    highlightMyRegion(Main.gameController.getMyRegion());
    playerRegion.setText("My Region: " + Main.gameController.getMyRegion());
    currentRegion.setText("Current Region: " + Main.gameController.getMyRegion());
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
    if(myRegion == EnumRegion.CALIFORNIA){
      cali.setVisible(true);
      statisticsPane.setCenter(testPieChart());
    }
    else if(myRegion == EnumRegion.MOUNTAIN){
      mountSt.setVisible(true);
      statisticsPane.setCenter(testPieChart());
    }
    else if(myRegion == EnumRegion.NORTHERN_CRESCENT) {
      northSt.setVisible(true);
      statisticsPane.setCenter(testPieChart());
    }
    else if(myRegion == EnumRegion.NORTHERN_PLAINS){
      nPlains.setVisible(true);
      statisticsPane.setCenter(testPieChart());
    }
    else if(myRegion == EnumRegion.SOUTHEAST){
      southEast.setVisible(true);
      statisticsPane.setCenter(testPieChart());
    }
    else if(myRegion == EnumRegion.SOUTHERN_PLAINS){
      sPlains.setVisible(true);
      statisticsPane.setCenter(testPieChart());
    }
    else if(myRegion == EnumRegion.HEARTLAND){
      heartland.setVisible(true);
      statisticsPane.setCenter(testPieChart());
    }
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

    System.out.println("myRegion: " + Main.gameController.getMyRegion());

    makeAllInvisible();

    if (ImageRegion.CALIFORNIA.contains(x, y))
    {
      cali.setVisible(true);
      caliLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.CALIFORNIA);

     // statisticsPane.setCenter(CropChart.makePieChart(Main.gameController.getRegion(EnumRegion.CALIFORNIA)));
      statisticsPane.setCenter(testPieChart());

      System.out.println("Selected cali");
    }
    else if (ImageRegion.HEARTLAND.contains(x, y))
    {
      heartland.setVisible(true);
      heartLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.HEARTLAND);
      //statisticsPane.setCenter(CropChart.makePieChart(Main.gameController.getRegion(EnumRegion.HEARTLAND)));

      System.out.println("Selected heartland");
    }
    else if (ImageRegion.MOUNTAINST.contains(x, y))
    {
      mountSt.setVisible(true);
      mountLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.MOUNTAIN);
      //statisticsPane.setCenter(CropChart.makePieChart(Main.gameController.getRegion(EnumRegion.MOUNTAIN)));

      System.out.println("Selected Mountain States");
    }
    else if (ImageRegion.NORTHPLAINS.contains(x, y))
    {
      nPlains.setVisible(true);
      nPlainLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.NORTHERN_PLAINS);
      //statisticsPane.setCenter(CropChart.makePieChart(Main.gameController.getRegion(EnumRegion.NORTHERN_PLAINS)));

      System.out.println("Selected North Plains");
    }
    else if (ImageRegion.NORTHEAST.contains(x, y))
    {
      northSt.setVisible(true);
      neLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.NORTHERN_CRESCENT);
      //statisticsPane.setCenter(CropChart.makePieChart(Main.gameController.getRegion(EnumRegion.NORTHERN_CRESCENT)));

      System.out.println("Selected Northeast");
    }
    else if (ImageRegion.SOUTHEAST.contains(x, y))
    {
      southEast.setVisible(true);
      seLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.SOUTHEAST);
      //statisticsPane.setCenter(CropChart.makePieChart(Main.gameController.getRegion(EnumRegion.SOUTHEAST)));

      System.out.println("Selected Southeast");

    }
    else if (ImageRegion.SOUTHPLAINS.contains(x, y))
    {
      sPlains.setVisible(true);
      sPlainLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.SOUTHERN_PLAINS);
      //statisticsPane.setCenter(CropChart.makePieChart(Main.gameController.getRegion(EnumRegion.SOUTHERN_PLAINS)));

      System.out.println("Selected South Plains");
    }

  }

  private PieChart testPieChart()
  {

    //PieChart p = new PieChart();
    ArrayList<PieChart.Data> dataList = new ArrayList<>();

    for(int i=0; i<5;i++)
    {
      dataList.add(new PieChart.Data(i+"", i*5));
    }

    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(dataList);
    PieChart p = new PieChart(pieChartData);
//    chart.
    p.setTitle("Dummy graph");
    p.setLegendSide(Side.BOTTOM);
    p.setLabelsVisible(true);
    p.setVisible(true);
    return p;
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

}

