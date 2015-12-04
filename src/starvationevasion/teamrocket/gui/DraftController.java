package starvationevasion.teamrocket.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.main.Main;
import starvationevasion.teamrocket.models.Player;
import starvationevasion.vis.ClientTest.CustomLayout;
import starvationevasion.vis.visuals.EarthViewer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Handles card drafting scene.
 */
public class DraftController implements javafx.fxml.Initializable
{

  /* HIGHLIGHTED IMAGES OF THE REGIONS */
  @FXML
  private ImageView cali, heartland, mountSt, northSt, nPlains, sPlains, southEast;

  /* PRODUCE AND DRAWING A CARD BUTTONS */
  @FXML
  private Button drawCardButton, discardPile;
  @FXML
  private Button appleButton, grainsButton, citrusButton, feedButton, dairyButton,
      fishButton, meatButton, nutButton, oilButton, poultryButton, veggieButton, specialButton;

  @FXML
  private Button doneWithCards;

  /* CARDS AND THEIR IMAGES IN THE DRAFTING PHASE */
  @FXML
  private ImageView card1Image, card2Image, card3Image, card4Image, card5Image, card6Image, card7Image, lastDiscardImage,
      draft1Image, draft2Image;

  @FXML
  private Button card1, card2, card3, card4, card5, card6, card7, draft1, draft2;

  private int cardsDrafted = 0;

  @FXML
  private Label discardedNum,discard1,discard2,discard3,discard4,discard5,discard6,discard7,discardDraft1,discardDraft2;

  private int disNum=0;


  @FXML
  private Label mainLabel;
  @FXML
  private Label time;

  /* REGION LABELS */
  @FXML
  private Label caliLabel, seLabel, neLabel, mountLabel, heartLabel, sPlainLabel, nPlainLabel;

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
  @FXML
  public GridPane visPane;


  private boolean caliSelected;
  private boolean heartlandSelected;
  private boolean mountainSelected;
  private boolean nPlainSelected;
  private boolean northeastSelected;
  private boolean southeastSelected;
  private boolean sPlainSelected;

  /* VARIABLES FOR USER INPUT NEEDED POPUP */
  @FXML
  private StackPane cardInputs;
  @FXML
  private Button addedInputs;
  @FXML
  private Pane inputPane;
  @FXML
  public GridPane cardPane;

  /******************************************/


  private int numCardsinHand = 7;
  private Player player;


  /* PRODUCE INFORMATION WINDOWS */
  @FXML
  private Pane appleWindow, grainWindow, citrusWindow, feedWindow, meatWindow, dairyWindow, nutWindow, oilWindow,
      poultryWindow, veggieWindow, specialWindow, fishWindow;
  @FXML
  private Button closeWindow, closeWindow2, closeWindow3, closeWindow4, closeWindow5, closeWindow6, closeWindow7,
      closeWindow8, closeWindow9, closeWindow10, closeWindow11, closeWindow12;
  @FXML
  private Label close, close2, close3, close4, close5, close6, close7, close8, close9, close10, close11, close12;

  private CustomLayout layout;
  EarthViewer earthViewer = new EarthViewer(100,400,layout);
  /***************************************************************************************/


  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    //start timer
    showMyRegion();
    displayEarth();
    resetCards();
  }

  @FXML
  public void displayEarth()
  {
    earthViewer.startRotate();
    visPane.add(earthViewer.getMiniEarth(), 1, 1);
    visPane.setVisible(true);

  }

  @FXML
  public void showBigEarth(KeyEvent event)
  {
    if(event.getCode() == KeyCode.TAB)
    {
      visPane.add(earthViewer.getLargeEarth(),1,1);
      earthViewer.startRotate();
    }
  }


  @FXML
  public void buttonPressed(ActionEvent event)
  {
    Button button = (Button)event.getSource();
    if(button == doneWithCards)
    {
    //make sure card has been played, show error label if not
    saveDraftedCards();
    try
    {
      Main.getGameController().finishedCardDraft();
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
  else if(button == addedInputs)
  {
    cardInputs.setVisible(false);

  }
  else if(button == card1)
  {
    cardInputs.setVisible(true);
    tryDraftingCard(card1, card1Image, discard1);
  }
  else if(button == card2)
  {
    cardInputs.setVisible(true);
    tryDraftingCard(card2, card2Image, discard2);
  }
  else if(button == card3)
  {
    cardInputs.setVisible(true);
    tryDraftingCard(card3, card3Image, discard3);
  }
  else if(button == card4)
  {
    cardInputs.setVisible(true);
    tryDraftingCard(card4, card4Image, discard4);
  }
  else if(button == card5)
  {
    cardInputs.setVisible(true);
    tryDraftingCard(card5, card5Image, discard5);
  }
  else if(button == card6)
  {
    cardInputs.setVisible(true);
    tryDraftingCard(card6, card6Image, discard6);
  }
  else if(button == card7)
  {
    cardInputs.setVisible(true);
    tryDraftingCard(card7, card7Image, discard7);
  }

  else if (button == drawCardButton)
  {
    //draw card from deck.
    drawNewCard();
  }
  else if (event.getSource() == appleButton)
  {
    //see whether it's for a policy card or for viewing
    //if policy card, use product for card
    //else display facts
    System.out.println("pressed apple button");
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

  @FXML
  public void makeBigger(MouseEvent event)
  {
    //Button card = (Button)event.getSource();
    if (event.getSource() == card1)
    {
      double width = card1Image.getFitWidth();
      double height = card1Image.getFitHeight();

      card1Image.setTranslateX(width / 16);
      card1Image.setTranslateY(height / 16);

      card1Image.setFitWidth(width * 4);
      card1Image.setFitHeight(height * 4);


      card1.toFront();


    }
    else if (event.getSource() == card2)
    {
      double width = card2Image.getFitWidth();
      double height = card2Image.getFitHeight();

      card2Image.setTranslateX(width / 16);
      card2Image.setTranslateY(height / 16);

      card2Image.setFitWidth(width * 4);
      card2Image.setFitHeight(height * 4);


      card2.toFront();


    }
    else if (event.getSource() == card3)
    {
      double width = card3Image.getFitWidth();
      double height = card3Image.getFitHeight();

      card3Image.setTranslateX(width / 16);
      card3Image.setTranslateY(height / 16);

      card3Image.setFitWidth(width * 4);
      card3Image.setFitHeight(height * 4);


      card3.toFront();
    }
    else if (event.getSource() == card4)
    {
      double width = card4Image.getFitWidth();
      double height = card4Image.getFitHeight();

      card4Image.setTranslateX(width / 16);
      card4Image.setTranslateY(height / 16);

      card4Image.setFitWidth(width * 4);
      card4Image.setFitHeight(height * 4);

      card4.toFront();
    }
    else if (event.getSource() == card5)
    {

      double width = card5Image.getFitWidth();
      double height = card5Image.getFitHeight();

      card5Image.setTranslateX(width / 16);
      card5Image.setTranslateY(height / 16);

      card5Image.setFitWidth(width * 4);
      card5Image.setFitHeight(height * 4);

      card5.toFront();
    }
    else if(event.getSource() == card6)
    {
      double width = card6Image.getFitWidth();
      double height = card6Image.getFitHeight();

      card6Image.setTranslateX(width / 16);
      card6Image.setTranslateY(height / 16);

      card6Image.setFitWidth(width * 4);
      card6Image.setFitHeight(height * 4);

      card6.toFront();
    }
    else if (event.getSource() == card7)
    {
      double width = card7Image.getFitWidth();
      double height = card7Image.getFitHeight();

      card7Image.setTranslateX(width / 16);
      card7Image.setTranslateY(height / 16);

      card7Image.setFitWidth(width * 4);
      card7Image.setFitHeight(height * 4);

      card7.toFront();

    }
  }

  @FXML
  public void returnToNormal(MouseEvent event)
  {
    if(event.getSource() == card1)
    {
      double width = card1Image.getFitWidth();
      double height = card1Image.getFitHeight();

      card1Image.setTranslateX(0);

      card1Image.setFitWidth(width / 4);
      card1Image.setFitHeight(height / 4);

    }
    else if(event.getSource() == card2)
    {

      double width = card2Image.getFitWidth();
      double height = card2Image.getFitHeight();

      card2Image.setTranslateX(0);
      card2Image.setTranslateY(0);

      card2Image.setFitWidth(width / 4);
      card2Image.setFitHeight(height / 4);

    }
    else if(event.getSource() == card3)
    {

      double width = card3Image.getFitWidth();
      double height = card3Image.getFitHeight();

      card3Image.setTranslateX(0);
      card3Image.setTranslateY(0);

      card3Image.setFitWidth(width / 4);
      card3Image.setFitHeight(height / 4);


    }
    else if(event.getSource() == card4)
    {

      double width = card4Image.getFitWidth();
      double height = card4Image.getFitHeight();

      card4Image.setTranslateX(0);
      card4Image.setTranslateY(0);

      card4Image.setFitWidth(width / 4);
      card4Image.setFitHeight(height / 4);

    }
    else if(event.getSource() == card5)
    {

      double width = card5Image.getFitWidth();
      double height = card5Image.getFitHeight();

      card5Image.setTranslateX(0);
      card5Image.setTranslateY(0);

      card5Image.setFitWidth(width / 4);
      card5Image.setFitHeight(height / 4);

    }
    else if(event.getSource() == card6)
    {

      double width = card6Image.getFitWidth();
      double height = card6Image.getFitHeight();

      card6Image.setTranslateX(0);
      card6Image.setTranslateY(0);

      card6Image.setFitWidth(width / 4);
      card6Image.setFitHeight(height / 4);

    }
    else if(event.getSource() == card7)
    {

      double width = card7Image.getFitWidth();
      double height = card7Image.getFitHeight();

      card7Image.setTranslateX(0);
      card7Image.setTranslateY(0);

      card7Image.setFitWidth(width / 4);
      card7Image.setFitHeight(height / 4);

    }

  }

  /**
   * Called after card in hand is clicked on.
   * Will draft card as long as there's not already 2 cards drafted.
   * @param card Card clicked
   * @param discard Discard option 'X'
   */
  private void tryDraftingCard(Button card, ImageView cardImage, Label discard)
  {
//    if(legal)
//    {
    cardsDrafted++;
    if (cardsDrafted == 1)
    {
      card.setDisable(true);
      discard.setDisable(true);
      discardDraft1.setVisible(true);
      draft1Image.setImage(cardImage.getImage());
      draft1.setVisible(true);


    }
    else if (cardsDrafted == 2)
    {
      card.setDisable(true);
      discard.setDisable(true);
      discardDraft2.setVisible(true);
      draft2Image.setImage(cardImage.getImage());
      draft2.setVisible(true);
    }
    else
    {
      //show error window and ask user if they want to swap cards
      //re-enable card put back
      System.out.println("Already two cards drafted.");
      cardsDrafted = 2;
    }
//  else
//  { Display error message
//  }
//
//
  }

  private void resetCards()
  {
    draft1.setVisible(false);
    discardDraft1.setVisible(false);
    draft2.setVisible(false);
    discardDraft2.setVisible(false);

    card1.setVisible(true);
    discard1.setVisible(true);
    card2.setVisible(true);
    discard2.setVisible(true);
    card3.setVisible(true);
    discard3.setVisible(true);
    card4.setVisible(true);
    discard4.setVisible(true);
    card5.setVisible(true);
    discard5.setVisible(true);
    card6.setVisible(true);
    discard6.setVisible(true);
    card7.setVisible(true);
    discard7.setVisible(true);

    disNum = 0;
    discardedNum.setText(""+disNum);
    numCardsinHand = 7;
  }

  private void drawNewCard()
  {
    if(numCardsinHand < 7)
    {
      if(!card1.isVisible())
      {
        card1.setVisible(true);
        discard1.setVisible(true);
        numCardsinHand++;
      }
      else if(!card2.isVisible())
      {
        card2.setVisible(true);
        discard2.setVisible(true);
        numCardsinHand++;
      }
      else if(!card3.isVisible())
      {
        card3.setVisible(true);
        discard3.setVisible(true);
        numCardsinHand++;
      }
      else if(!card4.isVisible())
      {
        card4.setVisible(true);
        discard4.setVisible(true);
        numCardsinHand++;
      }
      else if(!card5.isVisible())
      {
        card5.setVisible(true);
        discard5.setVisible(true);
        numCardsinHand++;
      }
      else if(!card6.isVisible())
      {
        card6.setVisible(true);
        discard6.setVisible(true);
        numCardsinHand++;
      }
      else if(!card7.isVisible())
      {
        card7.setVisible(true);
        discard7.setVisible(true);
        numCardsinHand++;
      }
    }
    else
    {
      //Show error label
      System.out.println("Cannot have more than 7 cards at a time.");
    }
  }

  private void saveDraftedCards()
  {
    int[] draftedCards = new int[2];

    if(draft1Image.getImage()==card1Image.getImage()){ draftedCards[0] = 1;}
    else if(draft1Image.getImage()==card2Image.getImage()){ draftedCards[0] = 2;}
    else if(draft1Image.getImage()==card3Image.getImage()){ draftedCards[0] = 3;}
    else if(draft1Image.getImage()==card4Image.getImage()){ draftedCards[0] = 4;}
    else if(draft1Image.getImage()==card5Image.getImage()){ draftedCards[0] = 5;}
    else if(draft1Image.getImage()==card6Image.getImage()){ draftedCards[0] = 6;}
    else if(draft1Image.getImage()==card7Image.getImage()){ draftedCards[0] = 7;}

    if(draft2Image.getImage()==card1Image.getImage()){ draftedCards[1] = 1;}
    else if(draft2Image.getImage()==card2Image.getImage()){ draftedCards[1] = 2;}
    else if(draft2Image.getImage()==card3Image.getImage()){ draftedCards[1] = 3;}
    else if(draft2Image.getImage()==card4Image.getImage()){ draftedCards[1] = 4;}
    else if(draft2Image.getImage()==card5Image.getImage()){ draftedCards[1] = 5;}
    else if(draft2Image.getImage()==card6Image.getImage()){ draftedCards[1] = 6;}
    else if(draft2Image.getImage()==card7Image.getImage()){ draftedCards[1] = 7;}


    Main.getGameController().playerAction(draftedCards,true);
  }


  @FXML
  public void discard(MouseEvent event)
  {
    Label discard = (Label)event.getSource();
    if(discard == discard1)
    {
      numCardsinHand--;
      card1.setVisible(false);
      discard1.setVisible(false);
      lastDiscardImage.setImage(card1Image.getImage());
      disNum++;
      discardedNum.setText(""+disNum);
    }
    else if(discard == discard2)
    {
      numCardsinHand--;
      card2.setVisible(false);
      discard2.setVisible(false);
      lastDiscardImage.setImage(card2Image.getImage());
      disNum++;
      discardedNum.setText(""+disNum);
    }
    else if(discard == discard3)
    {
      numCardsinHand--;
      card3.setVisible(false);
      discard3.setVisible(false);
      lastDiscardImage.setImage(card3Image.getImage());
      disNum++;
      discardedNum.setText(""+disNum);
    }
    else if(discard == discard4)
    {
      numCardsinHand--;
      card4.setVisible(false);
      discard4.setVisible(false);
      lastDiscardImage.setImage(card4Image.getImage());
      disNum++;
      discardedNum.setText(""+disNum);
    }
    else if(discard == discard5)
    {
      numCardsinHand--;
      card5.setVisible(false);
      discard5.setVisible(false);
      lastDiscardImage.setImage(card5Image.getImage());
      disNum++;
      discardedNum.setText(""+disNum);
    }
    else if(discard == discard6)
    {
      numCardsinHand--;
      card6.setVisible(false);
      discard6.setVisible(false);
      lastDiscardImage.setImage(card6Image.getImage());
      disNum++;
      discardedNum.setText(""+disNum);
    }
    else if(discard == discard7)
    {
      numCardsinHand--;
      card7.setVisible(false);
      discard7.setVisible(false);
      lastDiscardImage.setImage(card7Image.getImage());
      disNum++;
      discardedNum.setText(""+disNum);
    }
    else if(discard == discardDraft1)
    {
      cardsDrafted--;
      if(draft1Image.getImage() == card1Image.getImage()) {
        card1.setDisable(false);
        discard1.setDisable(false);
        card1Image.setDisable(false);
      }
      else if(draft1Image.getImage() == card2Image.getImage()) {
        card2.setDisable(false);
        discard2.setDisable(false);
        card2Image.setDisable(false);
      }
      else if(draft1Image.getImage() == card3Image.getImage()) {
        card3.setDisable(false);
        discard3.setDisable(false);
        card3Image.setDisable(false);
      }
      else if(draft1Image.getImage() == card4Image.getImage()) {
        card4.setDisable(false);
        discard4.setDisable(false);
        card4Image.setDisable(false);
      }
      else if(draft1Image.getImage() == card5Image.getImage()) {
        card5.setDisable(false);
        discard5.setDisable(false);
        card5Image.setDisable(false);
      }
      else if(draft1Image.getImage() == card6Image.getImage()){
        card6.setDisable(false);
        discard6.setDisable(false);
        card6Image.setDisable(false);
      }
      else if(draft1Image.getImage() == card7Image.getImage()) {
        card7.setDisable(false);
        discard7.setDisable(false);
        card7Image.setDisable(false);
      }

      draft1.setVisible(false);
      discardDraft1.setVisible(false);

    }
    else if(discard == discardDraft2)
    {
      cardsDrafted--;
      if(draft2Image.getImage() == card1Image.getImage()) {
        card1.setDisable(false);
        discard1.setDisable(false);
        card1Image.setDisable(false);
      }
      else if(draft2Image.getImage() == card2Image.getImage()) {
        card2.setDisable(false);
        discard2.setDisable(false);
        card2Image.setDisable(false);
      }
      else if(draft2Image.getImage() == card3Image.getImage()) {
        card3.setDisable(false);
        discard3.setDisable(false);
        card3Image.setDisable(false);
      }
      else if(draft2Image.getImage() == card4Image.getImage()) {
        card4.setDisable(false);
        discard4.setDisable(false);
        card4Image.setDisable(false);
      }
      else if(draft2Image.getImage() == card5Image.getImage()) {
        card5.setDisable(false);
        discard5.setDisable(false);
        card5Image.setDisable(false);
      }
      else if(draft2Image.getImage() == card6Image.getImage()){
        card6.setDisable(false);
        discard6.setDisable(false);
        card6Image.setDisable(false);
      }
      else if(draft2Image.getImage() == card7Image.getImage()) {
        card7.setDisable(false);
        card7Image.setDisable(false);
        discard7.setDisable(false);
      }
      draft2.setVisible(false);
      discardDraft2.setVisible(false);

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

    System.out.println("myRegion: " + Main.getGameController().getMyRegion());

    makeAllInvisible();

    if (ImageRegion.CALIFORNIA.contains(x, y))
    {
      cali.setVisible(true);
      caliLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.CALIFORNIA);

      // statisticsPane.setCenter(CropChart.makeRegionFoodPieChart(Main.gameController.getRegion(EnumRegion.CALIFORNIA)));
      statisticsPane.setCenter(testPieChart());

      System.out.println("Selected cali");
    }
    else if (ImageRegion.HEARTLAND.contains(x, y))
    {
      heartland.setVisible(true);
      heartLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.HEARTLAND);
      //statisticsPane.setCenter(CropChart.makeRegionFoodPieChart(Main.gameController.getRegion(EnumRegion.HEARTLAND)));

      System.out.println("Selected heartland");
    }
    else if (ImageRegion.MOUNTAINST.contains(x, y))
    {
      mountSt.setVisible(true);
      mountLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.MOUNTAIN);
      //statisticsPane.setCenter(CropChart.makeRegionFoodPieChart(Main.gameController.getRegion(EnumRegion.MOUNTAIN)));

      System.out.println("Selected Mountain States");
    }
    else if (ImageRegion.NORTHPLAINS.contains(x, y))
    {
      nPlains.setVisible(true);
      nPlainLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.NORTHERN_PLAINS);
      //statisticsPane.setCenter(CropChart.makeRegionFoodPieChart(Main.gameController.getRegion(EnumRegion.NORTHERN_PLAINS)));

      System.out.println("Selected North Plains");
    }
    else if (ImageRegion.NORTHEAST.contains(x, y))
    {
      northSt.setVisible(true);
      neLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.NORTHERN_CRESCENT);
      //statisticsPane.setCenter(CropChart.makeRegionFoodPieChart(Main.gameController.getRegion(EnumRegion.NORTHERN_CRESCENT)));

      System.out.println("Selected Northeast");
    }
    else if (ImageRegion.SOUTHEAST.contains(x, y))
    {

      southEast.setVisible(true);
      seLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.SOUTHEAST);
      //statisticsPane.setCenter(CropChart.makeRegionFoodPieChart(Main.gameController.getRegion(EnumRegion.SOUTHEAST)));

      System.out.println("Selected Southeast");

    }
    else if (ImageRegion.SOUTHPLAINS.contains(x, y))
    {
      sPlains.setVisible(true);
      sPlainLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.SOUTHERN_PLAINS);
      //statisticsPane.setCenter(CropChart.makeRegionFoodPieChart(Main.gameController.getRegion(EnumRegion.SOUTHERN_PLAINS)));

      System.out.println("Selected South Plains");
    }

  }

  /**
   * Allows regions to be highlighted when drafting scene begins.
   */
  @FXML
  public void showMyRegion()
  {
    highlightMyRegion(Main.getGameController().getMyRegion());
    playerRegion.setText("My Region: " + Main.getGameController().getMyRegion());
    currentRegion.setText("Current Region: " + Main.getGameController().getMyRegion());
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


  @FXML
  public void mouseMoved(MouseEvent event)
  {
    double x = event.getX();
    double y = event.getY();

    hideRegionLabels();
    showLabel(x, y);

  }

  @FXML
  public void mouseExit()
  {
    hideCropLabels();
    changeTextColor(Color.BLACK);
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

}
