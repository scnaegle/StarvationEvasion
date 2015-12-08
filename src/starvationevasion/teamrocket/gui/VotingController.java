package starvationevasion.teamrocket.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;
import starvationevasion.teamrocket.main.Main;
import starvationevasion.teamrocket.models.RegionHistory;

import javax.smartcardio.Card;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Handles the voting scene.
 */
public class VotingController implements javafx.fxml.Initializable
{
  /*      VOTING PHASE VARIABLES      */

  @FXML
  private Label time;

  @FXML
  private ImageView voteCard; //Large view of card being voted on

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

  //Main labels displaying total votes
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

  /* VOTE LABELS ON POPUP WINDOW */
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

  @FXML
  private Button doneVoting;

  /* CARD PANE WHEN CARD IS CLICKED ON. ALLOWS FOR VOTING */
  @FXML
  private Button submitVote;
  @FXML
  private Pane CardPane;

  /* ALL PLAYING CARDS. 2 FOR EACH REGION */
  @FXML
  private Button caliCard1, caliCard2, mountCard1, mountCard2, nPlainCard1, nPlainCard2, sPlainCard1,
      sPlainCard2, heartCard1, heartCard2, neCard1, neCard2, seCard1, seCard2;

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

  PolicyCard[] myDrafts;
  EnumRegion myRegion;

  /* GRAPHS */
  @FXML
  private BorderPane graph1, graph2, graph3;

  /********************************************************************************/

  /**
   * Constructor that starts the game timer for the specific scene.
   */
  public VotingController()
  {
    Timeline updater = new Timeline(new KeyFrame(Duration.millis(Main.GUI_REFRESH_RATE), new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
        if(Main.getGameController().getCurrentScene() == EnumScene.VOTE_PHASE)
        {

          time.setText(Main.GAME_CLOCK.getFormatted());

          time.setTextFill(Color.FORESTGREEN);
          if (Main.GAME_CLOCK.getMinutes() < 1)
          {
            time.setTextFill(Color.RED);
          }
          else if(Main.GAME_CLOCK.getMinutes()<2)
          {
            time.setTextFill(Color.ORANGE);
          }

          if (Main.getGameController().initGUI())
          {
            highlightCards();
            myDrafts = Main.getGameController().getDraftedCards();
            if(myDrafts[0] != null) System.out.println("card 1: " + myDrafts[0].getCardType());
            if(myDrafts[1] != null) System.out.println("card 2: "+ myDrafts[1].getCardType());
            myRegion = Main.getGameController().getMyRegion();
            showGraphs();
          }



          if (Main.GAME_CLOCK.getTimeLeft() <= 0)
          {
            //Main.getGameController().finishedVoting();
          }

        }

      }
    }));

    updater.setCycleCount(Timeline.INDEFINITE);
    updater.play();
  }


  /**
   * Displays graphs on scene open.
   */
  private void showGraphs()
  {
    graph1.setCenter(CropChart.makeHDIPieChart(new RegionHistory(myRegion)));
    graph2.setCenter(CropChart.makeRegionFoodPieChart(new RegionHistory(myRegion)));
    graph3.setCenter(CropChart.makeLineChartRegionRevenue(new RegionHistory(myRegion)));
  }
  /**
   * When controller is initialized, this method gets called and defaults whatever method is called from inside it.
   * Parameters are not necessary to use.
   * @param location default location of controller.
   * @param resources resources.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {

  }



  /**
   * Highlights cards in the color of user's region if they are votable.
   */
  @FXML
  public void highlightCards()
  {
    if(Main.getGameController().getMyRegion() == EnumRegion.CALIFORNIA)
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
    else if(Main.getGameController().getMyRegion() == EnumRegion.MOUNTAIN)
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
    else if(Main.getGameController().getMyRegion() == EnumRegion.NORTHERN_PLAINS)
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
    else if(Main.getGameController().getMyRegion() == EnumRegion.SOUTHERN_PLAINS)
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
    else if(Main.getGameController().getMyRegion() == EnumRegion.HEARTLAND)
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
    else if(Main.getGameController().getMyRegion() == EnumRegion.NORTHERN_CRESCENT)
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
    else if(Main.getGameController().getMyRegion() == EnumRegion.SOUTHEAST)
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

  /**
   * Initially disables all cards and hides highlighting.
   * Once the game finds out if the card is votable it highlights the necessary cards.
   */
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


  /**
   * If a card is votable this allows for the player to vote on it
   *
   * @param card the card tha the player sets
   * @return button pushed
   */
  private boolean isVotable(Button card)
  {

    if(card == caliCard1){
      if( myRegion == EnumRegion.CALIFORNIA && myDrafts[0].votesRequired() != 0) return true;
    }
    if(card == caliCard2){
      if( myRegion == EnumRegion.CALIFORNIA && myDrafts[1].votesRequired() != 0) return true;
    }
    if(card == mountCard1){
      if( myRegion == EnumRegion.MOUNTAIN && myDrafts[0].votesRequired() != 0)return true;
    }
    if(card == mountCard2){
      if( myRegion == EnumRegion.MOUNTAIN && myDrafts[1].votesRequired() != 0)return true;
    }
    if(card == nPlainCard1){
      if( myRegion == EnumRegion.NORTHERN_PLAINS && myDrafts[0].votesRequired() != 0)return true;
    }
    if(card == nPlainCard2){
      if( myRegion == EnumRegion.NORTHERN_PLAINS && myDrafts[1].votesRequired() != 0)return true;
    }
    if(card == neCard1){
      if( myRegion == EnumRegion.NORTHERN_CRESCENT && myDrafts[0].votesRequired() != 0)return true;
    }
    if(card == neCard2) {
      if( myRegion == EnumRegion.NORTHERN_CRESCENT && myDrafts[1].votesRequired() != 0)return true;
    }
    if(card == sPlainCard1){
      if( myRegion == EnumRegion.SOUTHERN_PLAINS && myDrafts[0].votesRequired() != 0)return true;
    }
    if(card == sPlainCard2){
      if( myRegion == EnumRegion.SOUTHERN_PLAINS && myDrafts[1].votesRequired() != 0)return true;
    }
    if(card == seCard1){
      if( myRegion == EnumRegion.SOUTHEAST && myDrafts[0].votesRequired() != 0)return true;
    }
    if(card == seCard2){
      if( myRegion == EnumRegion.SOUTHEAST && myDrafts[1].votesRequired() != 0)return true;
    }
    if(card == heartCard1){
      if( myRegion == EnumRegion.HEARTLAND && myDrafts[0].votesRequired() != 0)return true;
    }
    if(card == heartCard2) {
      if( myRegion == EnumRegion.HEARTLAND && myDrafts[1].votesRequired() != 0)return true;
    }

    return true;
  }


  /**
   * Checks for all button pressed action events.
   * Asks which button is pressed and does the appropriate related action.
   * @param event Action event.
   */
  @FXML
  public void buttonPressed(ActionEvent event)
  {
    Button button = (Button)event.getSource();
    if(button == doneVoting)
    {
      hideAllVoteLabels();


    //save choices

    try
    {
      Main.getGameController().finishedVoting();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    }
    else if(button == caliCard1)
    {

      if(myRegion == EnumRegion.CALIFORNIA){voteCard.setImage(CardImage.getCardImage(myDrafts[0].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      caliCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(caliSupportVotes1, caliOpposeVotes1, caliAbstainVotes1);
      c1Votes.setVisible(true);
    }
    else if(button == caliCard2)
    {
      if(myRegion == EnumRegion.CALIFORNIA){voteCard.setImage(CardImage.getCardImage(myDrafts[1].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      caliCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(caliSupportVotes2, caliOpposeVotes2, caliAbstainVotes2);
      c2Votes.setVisible(true);
    }
    else if(button == mountCard1)
    {
      if(myRegion == EnumRegion.MOUNTAIN){voteCard.setImage(CardImage.getCardImage(myDrafts[0].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      mountCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(mountSupportVotes1, mountOpposeVotes1, mountAbstainVotes1);
      m1Votes.setVisible(true);
    }
    else if(button == mountCard2)
    {
      if(myRegion == EnumRegion.MOUNTAIN){voteCard.setImage(CardImage.getCardImage(myDrafts[1].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      mountCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(mountSupportVotes2, mountOpposeVotes2, mountAbstainVotes2);
      m2Votes.setVisible(true);
    }
    else if(button == nPlainCard1)
    {
      if(myRegion == EnumRegion.NORTHERN_PLAINS){voteCard.setImage(CardImage.getCardImage(myDrafts[0].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      nPlainCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(nPlainSupportVotes1, nPlainOpposeVotes1, nPlainAbstainVotes1);
      np1Votes.setVisible(true);
    }
    else if(button == nPlainCard2)
    {
      if(myRegion == EnumRegion.NORTHERN_PLAINS){voteCard.setImage(CardImage.getCardImage(myDrafts[1].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      nPlainCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(nPlainSupportVotes2, nPlainOpposeVotes2, nPlainAbstainVotes2);
      np2Votes.setVisible(true);
    }
    else if(button == sPlainCard1)
    {
      if(myRegion == EnumRegion.SOUTHERN_PLAINS){voteCard.setImage(CardImage.getCardImage(myDrafts[0].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      sPlainCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(sPlainSupportVotes1, sPlainOpposeVotes1, sPlainAbstainVotes1);
      sp1Votes.setVisible(true);
    }
    else if(button == sPlainCard2)
    {
      if(myRegion == EnumRegion.SOUTHERN_PLAINS){voteCard.setImage(CardImage.getCardImage(myDrafts[1].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      sPlainCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(sPlainSupportVotes2, sPlainOpposeVotes2, sPlainAbstainVotes2);
      sp2Votes.setVisible(true);
    }
    else if(button == neCard1)
    {
      if(myRegion == EnumRegion.NORTHERN_CRESCENT){voteCard.setImage(CardImage.getCardImage(myDrafts[0].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      neCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(neSupportVotes1, neOpposeVotes1, neAbstainVotes1);
      ne1Votes.setVisible(true);
    }
    else if(button == neCard2)
    {
      if(myRegion == EnumRegion.NORTHERN_CRESCENT){voteCard.setImage(CardImage.getCardImage(myDrafts[1].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      neCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(neSupportVotes2, neOpposeVotes2, neAbstainVotes2);
      ne2Votes.setVisible(true);
    }
    else if(button == seCard1)
    {
      if(myRegion == EnumRegion.SOUTHEAST){voteCard.setImage(CardImage.getCardImage(myDrafts[0].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      seCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(seSupportVotes1, seOpposeVotes1, seAbstainVotes1);
      s1Votes.setVisible(true);
    }
    else if(button == seCard2)
    {
      if(myRegion == EnumRegion.SOUTHEAST){voteCard.setImage(CardImage.getCardImage(myDrafts[1].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      seCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(seSupportVotes2, seOpposeVotes2, seAbstainVotes2);
      s2Votes.setVisible(true);
    }
    else if(button == heartCard1)
    {
      if(myRegion == EnumRegion.HEARTLAND){voteCard.setImage(CardImage.getCardImage(myDrafts[0].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      heartCard1Selected = true;
      CardPane.setVisible(true);
      updateLabels(heartSupportVotes1, heartOpposeVotes1, heartAbstainVotes1);
      h1Votes.setVisible(true);
    }
    else if(button == heartCard2)
    {
      if(myRegion == EnumRegion.HEARTLAND){voteCard.setImage(CardImage.getCardImage(myDrafts[1].getCardType()));}
      else{ /*set other cards to other player's cards*/}
      heartCard2Selected = true;
      CardPane.setVisible(true);
      updateLabels(heartSupportVotes2, heartOpposeVotes2, heartAbstainVotes2);
      h2Votes.setVisible(true);
    }

    else if(button == submitVote) //Button on large view of card
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

    }
  }


  /**
   * Keeps track of the user's vote: Approve, Obstain, or Oppose.
   * Updates labels and integers as necessary.
   * @param event Action event.
   */
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

  }

  /**
   * Resets integers and labels after each vote (click).
   * @param reset1 Integer to reset.
   * @param reset2 Other integer to reset.
   * @param text1 Label to reset.
   * @param text2 Other label to reset.
   */
  private void resetVotesAndLabels(int reset1, int reset2, Label text1, Label text2)
  {
    reset1 = 0;
    reset2 = 0;

    text1.setText(""+reset1);
    text2.setText(""+reset2);

  }

  /**
   * On mouseover, a label shows for the user's convenience.
   * @param event MouseEvent.
   */
  @FXML
  public void mouseOverVote(javafx.scene.input.MouseEvent event)
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

  /**
   * Turns off labels when mouse leaves object.
   */
  @FXML
  public void mouseExitVote()
  {
    supportLabel.setVisible(false);
    opposeLabel.setVisible(false);
    abstainLabel.setVisible(false);
  }

  private void hideVoteLabels()
  {
    supportLabel.setVisible(false);
    opposeLabel.setVisible(false);
    abstainLabel.setVisible(false);
  }

  /**
   * Deselects cards each time the user is done voting on one.
   */
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

  /**
   * Hides labels in voting scene that account for everyone's votes.
   */
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

  /**
   * Updates main scene labels with whatever the user voted.
   * @param supportVotes Support votes.
   * @param opposeVotes Oppose votes.
   * @param abstainVotes Abstain votes.
   */
  private void updateLabels(int supportVotes, int opposeVotes, int abstainVotes)
  {
    //Get other user's votes if needed
    sVotes.setText("" + supportVotes);
    oVotes.setText("" + opposeVotes);
    aVotes.setText("" + abstainVotes);

  }

}
