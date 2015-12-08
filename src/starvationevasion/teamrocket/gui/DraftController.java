package starvationevasion.teamrocket.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;
import starvationevasion.teamrocket.main.Main;
import starvationevasion.teamrocket.models.Player;
import starvationevasion.teamrocket.models.RegionHistory;
import starvationevasion.vis.visuals.Earth;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static starvationevasion.teamrocket.main.Main.GAME_CLOCK;
import static starvationevasion.teamrocket.main.Main.getGameController;

/**
 * Handles card drafting scene.
 */
public class DraftController implements javafx.fxml.Initializable
{

  /* HIGHLIGHTED IMAGES OF THE REGIONS */
  @FXML
  private ImageView cali, heartland, mountSt, northSt, nPlains, sPlains,
      southEast;

  /* PRODUCE AND DRAWING A CARD BUTTONS */
  @FXML
  private Button drawCardButton, discardPile;
  @FXML
  private Button appleButton, grainsButton, citrusButton, feedButton,
      dairyButton,
      fishButton, meatButton, nutButton, oilButton, poultryButton, veggieButton,
      specialButton;

  @FXML
  private Button doneWithCards;

  /* CARDS AND THEIR IMAGES IN THE DRAFTING PHASE */
  @FXML
  private ImageView card1Image, card2Image, card3Image, card4Image, card5Image,
      card6Image, card7Image, lastDiscardImage,
      draft1Image, draft2Image;

  @FXML
  private Button card1, card2, card3, card4, card5, card6, card7, draft1,
      draft2;

  private int cardsDrafted = 0;

  @FXML
  private Label discardedNum, discard1, discard2, discard3, discard4, discard5,
      discard6, discard7, discardDraft1, discardDraft2;

  private int disNum = 0;


  @FXML
  private Label mainLabel;
  @FXML
  private Label time;

  /* REGION LABELS */
  @FXML
  private Label caliLabel, seLabel, neLabel, mountLabel, heartLabel,
      sPlainLabel, nPlainLabel;

  @FXML
  private Label currentRegion;

  /* CROP LABELS */
  @FXML
  private Label appleLabel, grainLabel, citrusLabel, feedLabel, dairyLabel,
      fishLabel, meatLabel, nutLabel,
      oilLabel, poultryLabel, veggieLabel, specialLabel;


  @FXML
  private Label playerRegion;



  /*          GRAPHS FOR EACH REGION           */
  @FXML
  private BorderPane hdiGraph, popGraph, revGraph, cropGraph;
  /**************************************************************/


  /*          VISUALIZER CODE         */
  Earth earthViewer = new Earth(100, 200);
  @FXML
  public GridPane visPane;
  @FXML
  private GridPane largeEarthPane;
  @FXML
  private Pane worldPane;
  /*************************************/

  /* VARIABLES FOR USER INPUT NEEDED POPUP */
  @FXML
  private StackPane cardInputs;
  @FXML
  private Button addedInputs, cancelInputs;
  @FXML
  private Pane inputPane;
  @FXML
  public GridPane cardPane;

  /******************************************/


  private int numCardsinHand = 7;
  private Player player;


  /* PRODUCE INFORMATION WINDOWS */
  @FXML
  private Pane appleWindow, grainWindow, citrusWindow, feedWindow, meatWindow,
      dairyWindow, nutWindow, oilWindow,
      poultryWindow, veggieWindow, specialWindow, fishWindow;
  @FXML
  private Button closeWindow, closeWindow2, closeWindow3, closeWindow4,
      closeWindow5, closeWindow6, closeWindow7,
      closeWindow8, closeWindow9, closeWindow10, closeWindow11, closeWindow12,closeEarth;
  @FXML
  private Label close, close2, close3, close4, close5, close6, close7, close8,
      close9, close10, close11, close12, closeEarthLabel;

  @FXML
  private ImageView rightArrow, leftArrow;

  @FXML
  private BorderPane PPT; //graph for produce

  @FXML
  private TextArea nonCitrusText, grainsText, citrusText, feedText, oilText, meatText, dairyText, poultryText,
  nutText, specialText, fishText, veggieText;


  @FXML
  private Label worldTitle;

  private boolean[] selectedCard = new boolean[7];
  private Spinner<Integer> draftXControl;
  private Spinner<Integer> draftYControl;
  private Spinner<Integer> draftZControl;
  private ComboBox<EnumFood> draftTargetFood;
  private ComboBox<EnumRegion> draftTargetRegion;

  private PolicyCard draft1Card;
  private PolicyCard draft2Card;
  private boolean[] produce = new boolean[12];

  /***************************************************************************************/

  /**
   * controls the draft scene and how we are running it/
   */
  public DraftController()
  {
    Timeline updater = new Timeline(new KeyFrame(Duration.millis(Main.GUI_REFRESH_RATE), new EventHandler<ActionEvent>()
    {
      /**
       * Listens to the timer in order to change the color of the clock and tell
       * the client when the turn is over
       * @param event a certain value on the timer is updated
       */
      @Override
      public void handle(ActionEvent event)
      {
        if (getGameController().getCurrentScene() == EnumScene.DRAFT_PHASE)
        {
          displayHand();

          time.setText(GAME_CLOCK.getFormatted());
          time.setTextFill(Color.FORESTGREEN);
          if (Main.GAME_CLOCK.getMinutes() < 1)
          {
            time.setTextFill(Color.RED);
          }
          else if(Main.GAME_CLOCK.getMinutes()<2)
          {
            time.setTextFill(Color.ORANGE);
          }

          if (getGameController().initGUI())
          {
            Main.getGameController().openChat();
            setMainLabel();
            showMyRegion();
            setWindowTexts();
            resetCards();
          }


          if (Main.GAME_CLOCK.getTimeLeft() <= 0)
          {
            getGameController().playDrafts(draft1Card, draft2Card);
          }
        }
      }
    }));

    updater.setCycleCount(Timeline.INDEFINITE);
    updater.play();
  }



  /**
   * When scene first opens, visualizer is loaded and displayed.
   * @param location Location of controller.
   * @param resources resources.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    displayEarth();

  }

  private void setMainLabel()
  {
    mainLabel.setText(getGameController().currentYear+", "+getGameController().population+", "+getGameController().HDI);
  }
  /**
   * Determines what each card needs as an input and shows specific boxes and drop downs
   * depending on what the cards need.
   * @param cardIndex Card in the hand.
   */
  private void setupAddBox(int cardIndex)
  {
    cardInputs.setVisible(true);
    PolicyCard card = getGameController().player.getCard(cardIndex);
    //Clear old buttons
    ObservableList<Node> children = inputPane.getChildren();
    children.clear();

    GridPane textGrid = new GridPane();
    textGrid.setMaxWidth(inputPane.getWidth());
    textGrid.setMaxHeight(inputPane.getHeight());
    children.add(textGrid);
    int row = 0; //Track what row in the grid we are at.

    Text text = new Text(card.getGameText());
    text.setWrappingWidth(400);

    textGrid.add(text, 0, row++);

    PolicyCard.EnumVariableUnit type = card.getRequiredVariables(PolicyCard.EnumVariable.X);
    if(type != null)
    {
      draftXControl = getControlForVariable(type);
      Label label = new Label("X value", draftXControl);
      textGrid.add(draftXControl, 1, row);
      textGrid.add(label, 0, row++);
      label.setVisible(true);
      draftXControl.setVisible(true);
    }
    type = card.getRequiredVariables(PolicyCard.EnumVariable.Y);
    if(type != null)
    {
      draftYControl = getControlForVariable(type);
      Label label = new Label("Y value", draftYControl);
      textGrid.add(draftYControl, 1, row);
      textGrid.add(label, 0, row++);
      label.setVisible(true);
      draftYControl.setVisible(true);
    }
    type = card.getRequiredVariables(PolicyCard.EnumVariable.Z);
    if(type != null)
    {
      draftZControl = getControlForVariable(type);
      Label label = new Label("Z value", draftZControl);
      textGrid.add(draftZControl, 1, row);
      textGrid.add(label, 0, row++);
      label.setVisible(true);
      draftZControl.setVisible(true);
    }

    if(card.getValidTargetFoods() != null)
    {
      draftTargetFood = new ComboBox<>(FXCollections.observableArrayList(Arrays.asList(card.getValidTargetFoods())));
      Label label = new Label("Target Food", draftTargetFood);
      textGrid.add(draftTargetFood, 1, row);
      textGrid.add(label, 0, row++);
      label.setVisible(true);
      draftTargetFood.setVisible(true);
    }
    if(card.getValidTargetRegions() != null)
    {
      draftTargetRegion = new ComboBox<>(FXCollections.observableArrayList(Arrays.asList(card.getValidTargetRegions()
      )));
      Label label = new Label("Target Region", draftTargetRegion);
      textGrid.add(draftTargetRegion, 1, row);
      textGrid.add(label, 0, row++);
      label.setVisible(true);
      draftTargetRegion.setVisible(true);
    }


  }

  private Spinner<Integer> getControlForVariable(PolicyCard.EnumVariableUnit type)
  {
    Spinner<Integer> control = null;
    switch(type)
    {
      case MILLION_DOLLAR:
        control = new Spinner<>(PolicyCard.MIN_MILLION_DOLLARS, PolicyCard.MAX_MILLION_DOLLARS, 10, 1);
        break;
      case PERCENT:
        control = new Spinner<>(PolicyCard.MIN_PERCENT,PolicyCard.MAX_PERCENT, 50, 1);
        break;
      case UNIT:
        control = new Spinner<>(0, Integer.MAX_VALUE, 7, 1);
        control.setEditable(false);
        break;
    }
    return control;
  }


  /**
   * On scene open, the hand gets displayed.
   */
  public void displayHand()
  {
    for(int i = 0; i < 7; i++)
    {
      if(getGameController().getCard(i) == null) return;
    }
    card1Image.setImage(CardImage.getCardImage(getGameController().getCard(0).getCardType()));
    card2Image.setImage(CardImage.getCardImage(getGameController().getCard(1).getCardType()));
    card3Image.setImage(CardImage.getCardImage(getGameController().getCard(2).getCardType()));
    card4Image.setImage(CardImage.getCardImage(getGameController().getCard(3).getCardType()));
    card5Image.setImage(CardImage.getCardImage(getGameController().getCard(4).getCardType()));
    card6Image.setImage(CardImage.getCardImage(getGameController().getCard(5).getCardType()));
    card7Image.setImage(CardImage.getCardImage(getGameController().getCard(6).getCardType()));
  }


  /**
   * Displays small earth on a gridpane.
   */
  @FXML
  public void displayEarth()
  {
    earthViewer.startRotate();
    visPane.add(earthViewer.getSmallEarth(), 1, 1);
    //visPane.add(earthViewer.getEarth(), 1, 1);
    visPane.setVisible(true);

  }

  /**
   * Makes smaller earth bigger when clicked.
   * @param event Mouse event.
   */
  @FXML
  public void showBigEarth(MouseEvent event)
  {
    worldTitle.setVisible(false);
    if(event.getSource() == visPane)
    {
      worldPane.setVisible(true);
      largeEarthPane.add(earthViewer.getEarth(), 1, 1);
      earthViewer.startRotate();

    }

  }

  /**
   * Updates label when large earth is clicked.
   * @param event Mouse event.
   */
  @FXML
  public void showRegionClicked(MouseEvent event)
  {
//    worldTitle.setVisible(true);
//    worldTitle.setText(earthViewer.getRegionTitle());
  }


  /**
   * Allows user to switch between produce windows when clicking arrows.
   * @param event Mouse event.
   */
  @FXML
  public void switchProduce(MouseEvent event)
  {
    if(event.getSource() == leftArrow)
    {
      prevProduce();
    }
    else if(event.getSource() == rightArrow)
    {
      nextProduce();
    }
  }

  /**
   * Allows produce windows to be switched.
   * @param initial Initial index of open window.
   * @param next Next window to open.
   * @param window1 Current window pane.
   * @param window2 Next window pane to be open.
   */
  private void switchWindow(int initial, int next, Pane window1, Pane window2)
  {
    produce[initial] = false;
    produce[next] = true;
    window1.setVisible(false);
    window2.setVisible(true);
  }


  /**
   * Opens window panes to the right of the current pane.
   */
  private void nextProduce()
  {
    if(produce[0]){ //to grains
      switchWindow(0, 1, appleWindow, grainWindow);
      leftArrow.setVisible(true);
    }
    else if(produce[1]) //to citrus
    {
      switchWindow(1, 2, grainWindow, citrusWindow);
    }
    else if(produce[2]) //to feed
    {
      switchWindow(2, 3, citrusWindow, feedWindow);
    }
    else if(produce[3])
    {
      switchWindow(3, 4, feedWindow, dairyWindow);
    }
    else if(produce[4])
    {
      switchWindow(4,5,dairyWindow,fishWindow);
    }
    else if(produce[5])
    {
      switchWindow(5,6,fishWindow,meatWindow);
    }
    else if(produce[6])
    {
      switchWindow(6,7,meatWindow,nutWindow);
    }
    else if(produce[7])
    {
      switchWindow(7,8,nutWindow,oilWindow);
    }
    else if(produce[8])
    {
      switchWindow(8,9,oilWindow,poultryWindow);
    }
    else if(produce[9])
    {
      switchWindow(9,10,poultryWindow,veggieWindow);
    }
    else if(produce[10])
    {
      switchWindow(10,11,veggieWindow,specialWindow);
      rightArrow.setVisible(false);
    }
  }


  /**
   * Opens window panes to the left of the current pane.
   */
  private void prevProduce()
  {
    if(produce[11]) // to veggies
    {
      switchWindow(11,10,specialWindow,veggieWindow);
      rightArrow.setVisible(false);
    }
    else if(produce[10]) // to poultry
    {
      switchWindow(10, 9, veggieWindow, poultryWindow);
    }
    else if(produce[9])
    {
      switchWindow(9, 8, poultryWindow,oilWindow);
    }
    else if(produce[8])
    {
      switchWindow(8, 7, oilWindow,nutWindow);
    }
    else if(produce[7])
    {
      switchWindow(7, 6, nutWindow, meatWindow );
    }
    else if(produce[6])
    {
      switchWindow(6, 5, meatWindow,fishWindow);
    }
    else if(produce[5])
    {
      switchWindow(5, 4, fishWindow,dairyWindow);
    }
    else if(produce[4])
    {
      switchWindow(4,3, dairyWindow,feedWindow );
    }
    else if(produce[3]) //to citrus
    {
      switchWindow(3,2,  feedWindow,citrusWindow);
    }
    else if(produce[2]) //to grains
    {
      switchWindow(2,1, citrusWindow,grainWindow);
    }
    else if(produce[1]){ //to apple
      switchWindow(1,0, grainWindow,appleWindow);
      leftArrow.setVisible(false);
    }
  }

  /**
   * On scene open, the produce windows get their texts set.
   */
  private void setWindowTexts()
  {
    setText(nonCitrusText,EnumFood.FRUIT);
    setText(grainsText,EnumFood.GRAIN);
    setText(citrusText,EnumFood.CITRUS);
    setText(dairyText, EnumFood.DAIRY);
    setText(meatText, EnumFood.MEAT);
    setText(feedText, EnumFood.FEED);
    setText(poultryText, EnumFood.POULTRY);
    setText(oilText, EnumFood.OIL);
    setText(specialText, EnumFood.SPECIAL);
    setText(nutText, EnumFood.NUT);
    setText(fishText, EnumFood.FISH);
    setText(veggieText, EnumFood.VEGGIES);

  }

  /**
   * Sets text for each produce window textarea with specific information.
   * @param text Text area.
   * @param food Specific food.
   */
  private void setText(TextArea text, EnumFood food)
  {
    text.setText("Products: \n" + food.toLongString() + "\n\n" + "Fun fact: \n");
  }

  /**
   * Listens for button presses and acts appropriately.
   * @param event Action event.
   */
  @FXML
  public void buttonPressed(ActionEvent event)
  {
    PPT.setVisible(false);
    Button button = (Button) event.getSource();
    if (button == doneWithCards)
    {
      //make sure card has been played, show error label if not
      saveDraftedCards();
    }
    else if (button == closeWindow || button == closeWindow2 ||
        button == closeWindow3 ||
        button == closeWindow4 ||
        button == closeWindow5 ||
        button == closeWindow6 ||
        button == closeWindow7 ||
        button == closeWindow8 ||
        button == closeWindow9 ||
        button == closeWindow10 ||
        button == closeWindow11 ||
        button == closeWindow12)
    {
      closeProduceWindows();
      rightArrow.setVisible(false);
      leftArrow.setVisible(false);
    }
    //This is the button inside the cardInputs pane.
    else if (button == addedInputs)
    {
      Button cardButton = null;
      ImageView cardImage = null;
      Label discard = null;
      int cardIndex = -1;

      if (selectedCard[0])
      {
        cardButton = card1;
        cardImage = card1Image;
        discard = discard1;
        cardIndex = 0;
      }
      else if (selectedCard[1])
      {
        cardButton = card2;
        cardImage = card2Image;
        discard = discard2;
        cardIndex = 1;
      }
      else if (selectedCard[2])
      {
        cardButton = card3;
        cardImage = card3Image;
        discard = discard3;
        cardIndex = 2;
      }
      else if (selectedCard[3])
      {
        cardButton = card4;
        cardImage = card4Image;
        discard = discard4;
        cardIndex = 3;
      }
      else if (selectedCard[4])
      {
        cardButton = card5;
        cardImage = card5Image;
        discard = discard5;
        cardIndex = 4;
      }
      else if (selectedCard[5])
      {
        cardButton = card6;
        cardImage = card6Image;
        discard = discard6;
        cardIndex = 5;
      }
      else if (selectedCard[6])
      {
        cardButton = card7;
        cardImage = card7Image;
        discard = discard7;
        cardIndex = 6;
      }

      if(tryDraftingCard(cardButton, cardImage, discard, cardIndex))
      {
        cardInputs.setVisible(false);

        for (int i = 0; i < selectedCard.length; i++)
        {
          selectedCard[i] = false;
        }
      }

    }
    else if(button == cancelInputs)
    {
      for(int i=0;i<selectedCard.length;i++)
      {
        selectedCard[i] = false;
      }
      cardInputs.setVisible(false);
    }
    else if (button == card1)
    {
      selectedCard[0] = true;
      setupAddBox(0);
    }
    else if (button == card2)
    {
      selectedCard[1] = true;
      setupAddBox(1);

    }
    else if (button == card3)
    {
      selectedCard[2] = true;
      setupAddBox(2);
    }
    else if (button == card4)
    {
      selectedCard[3] = true;
      setupAddBox(3);
    }
    else if (button == card5)
    {
      selectedCard[4] = true;
      setupAddBox(4);
    }
    else if (button == card6)
    {
      selectedCard[5]=true;
      setupAddBox(5);
    }
    else if (button == card7)
    {
      selectedCard[6] = true;
      setupAddBox(6);
    }

    else if (button == drawCardButton)
    {
      //draw card from deck.
      drawNewCard();
    }
    else if (button == appleButton)
    {
      produce[0] = true;
      rightArrow.setVisible(true);
      leftArrow.setVisible(false);
      PPT.setVisible(true);
      PPT.setCenter(CropChart.makeLineChartPricePerMetricTonFood(new RegionHistory(EnumRegion.CALIFORNIA),EnumFood.FRUIT));
      appleWindow.setVisible(true);
    }
    else if (button == grainsButton)
    {
      produce[1] = true;
      rightArrow.setVisible(true);
      leftArrow.setVisible(true);
      PPT.setVisible(true);
      PPT.setCenter(
          CropChart.makeLineChartPricePerMetricTonFood(new RegionHistory(EnumRegion.CALIFORNIA), EnumFood.GRAIN));

      grainWindow.setVisible(true);
    }
    else if (button == citrusButton)
    {
      produce[2] = true;
      rightArrow.setVisible(true);
      leftArrow.setVisible(true);
      PPT.setVisible(true);
      PPT.setCenter(
          CropChart.makeLineChartPricePerMetricTonFood(new RegionHistory(EnumRegion.CALIFORNIA), EnumFood.CITRUS));

      citrusWindow.setVisible(true);
    }
    else if (button == feedButton)
    {
      produce[3] = true;
      rightArrow.setVisible(true);
      leftArrow.setVisible(true);
      PPT.setVisible(true);
      PPT.setCenter(
          CropChart.makeLineChartPricePerMetricTonFood(new RegionHistory(EnumRegion.CALIFORNIA), EnumFood.FEED));

      feedWindow.setVisible(true);
    }
    else if (button == dairyButton)
    {
      produce[4] = true;
      rightArrow.setVisible(true);
      leftArrow.setVisible(true);
      PPT.setVisible(true);
      PPT.setCenter(
          CropChart.makeLineChartPricePerMetricTonFood(new RegionHistory(EnumRegion.CALIFORNIA), EnumFood.DAIRY));

      dairyWindow.setVisible(true);
    }
    else if (button == meatButton)
    {
      produce[5] = true;
      rightArrow.setVisible(true);
      leftArrow.setVisible(true);
      PPT.setVisible(true);
      PPT.setCenter(
          CropChart.makeLineChartPricePerMetricTonFood(new RegionHistory(EnumRegion.CALIFORNIA), EnumFood.MEAT));

      meatWindow.setVisible(true);
    }
    else if (button == poultryButton)
    {
      produce[6] = true;
      rightArrow.setVisible(true);
      leftArrow.setVisible(true);
      PPT.setVisible(true);
      PPT.setCenter(
          CropChart.makeLineChartPricePerMetricTonFood(new RegionHistory(EnumRegion.CALIFORNIA), EnumFood.POULTRY));

      poultryWindow.setVisible(true);
    }
    else if (button == oilButton)
    {
      //see whether it's for a policy card or for viewing
      //if policy card, use product for card
      //else display facts
      produce[7] = true;
      rightArrow.setVisible(true);
      leftArrow.setVisible(true);
      PPT.setVisible(true);
      PPT.setCenter(
          CropChart.makeLineChartPricePerMetricTonFood(new RegionHistory(EnumRegion.CALIFORNIA), EnumFood.OIL));

      oilWindow.setVisible(true);
    }
    else if (button == specialButton)
    {
      produce[8] = true;
      rightArrow.setVisible(false);
      leftArrow.setVisible(true);
      PPT.setVisible(true);
      PPT.setCenter(
          CropChart.makeLineChartPricePerMetricTonFood(new RegionHistory(EnumRegion.CALIFORNIA), EnumFood.SPECIAL));

      specialWindow.setVisible(true);
    }
    else if (button == veggieButton)
    {
      produce[9] = true;
      rightArrow.setVisible(true);
      leftArrow.setVisible(true);
      PPT.setVisible(true);
      PPT.setCenter(
          CropChart.makeLineChartPricePerMetricTonFood(new RegionHistory(EnumRegion.CALIFORNIA), EnumFood.VEGGIES));

      veggieWindow.setVisible(true);
    }
    else if (button == nutButton)
    {
      produce[10] = true;
      rightArrow.setVisible(true);
      leftArrow.setVisible(true);
      PPT.setVisible(true);
      PPT.setCenter(
          CropChart.makeLineChartPricePerMetricTonFood(new RegionHistory(EnumRegion.CALIFORNIA), EnumFood.NUT));

      nutWindow.setVisible(true);
    }
    else if (button == fishButton)
    {
      produce[11] = true;
      rightArrow.setVisible(true);
      leftArrow.setVisible(true);
      PPT.setVisible(true);
      PPT.setCenter(
          CropChart.makeLineChartPricePerMetricTonFood(new RegionHistory(EnumRegion.CALIFORNIA), EnumFood.FISH));

      fishWindow.setVisible(true);
    }
    else if(button == closeEarth)
    {
      worldPane.setVisible(false);
      largeEarthPane.getChildren().remove(earthViewer.getEarthOverlay());
    }
  }



  /**
   * Makes cards bigger when mouse hovers over them.
   * @param event Mouse event.
   */
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
    else if (event.getSource() == card6)
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


  /**
   * When mouse leaves card, it returns it back to it's normal size.
   * @param event Mouse event.
   */
  @FXML
  public void returnToNormal(MouseEvent event)
  {
    if (event.getSource() == card1)
    {
      double width = card1Image.getFitWidth();
      double height = card1Image.getFitHeight();

      card1Image.setTranslateX(0);

      card1Image.setFitWidth(width / 4);
      card1Image.setFitHeight(height / 4);

    }
    else if (event.getSource() == card2)
    {

      double width = card2Image.getFitWidth();
      double height = card2Image.getFitHeight();

      card2Image.setTranslateX(0);
      card2Image.setTranslateY(0);

      card2Image.setFitWidth(width / 4);
      card2Image.setFitHeight(height / 4);

    }
    else if (event.getSource() == card3)
    {

      double width = card3Image.getFitWidth();
      double height = card3Image.getFitHeight();

      card3Image.setTranslateX(0);
      card3Image.setTranslateY(0);

      card3Image.setFitWidth(width / 4);
      card3Image.setFitHeight(height / 4);


    }
    else if (event.getSource() == card4)
    {

      double width = card4Image.getFitWidth();
      double height = card4Image.getFitHeight();

      card4Image.setTranslateX(0);
      card4Image.setTranslateY(0);

      card4Image.setFitWidth(width / 4);
      card4Image.setFitHeight(height / 4);

    }
    else if (event.getSource() == card5)
    {

      double width = card5Image.getFitWidth();
      double height = card5Image.getFitHeight();

      card5Image.setTranslateX(0);
      card5Image.setTranslateY(0);

      card5Image.setFitWidth(width / 4);
      card5Image.setFitHeight(height / 4);

    }
    else if (event.getSource() == card6)
    {

      double width = card6Image.getFitWidth();
      double height = card6Image.getFitHeight();

      card6Image.setTranslateX(0);
      card6Image.setTranslateY(0);

      card6Image.setFitWidth(width / 4);
      card6Image.setFitHeight(height / 4);

    }
    else if (event.getSource() == card7)
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
   * @param cardButton    Card clicked
   * @param discard Discard option 'X'
   * @param cardHandIndex index in hand to draft.
   */
  private boolean tryDraftingCard(Button cardButton, ImageView cardImage, Label discard, int cardHandIndex)
  {
    PolicyCard card = getGameController().getCard(cardHandIndex);

    if (card.getRequiredVariables(PolicyCard.EnumVariable.X) != null)
    {
      card.setX(draftXControl.getValue());
    }
    if (card.getRequiredVariables(PolicyCard.EnumVariable.Y) != null)
    {
      card.setY(draftYControl.getValue());
    }
    if (card.getRequiredVariables(PolicyCard.EnumVariable.Z) != null)
    {
      card.setZ(draftZControl.getValue());
    }

    if (card.getValidTargetFoods() != null)
    {
      card.setTargetFood(draftTargetFood.getValue());
    }

    if (card.getValidTargetRegions() != null)
    {
      card.setTargetRegion(draftTargetRegion.getValue());
    }

    if(card.validate() != null)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION, card.validate(), ButtonType.CLOSE);
      alert.setResizable(true);
      alert.setWidth(alert.getContentText().length() * 7);
      alert.showAndWait();
      return false;
    }

    cardsDrafted++;
    if (cardsDrafted == 1)
    {
      cardButton.setDisable(true);
      discard.setDisable(true);
      discardDraft1.setVisible(true);
      draft1Image.setImage(cardImage.getImage());
      draft1.setVisible(true);
      draft1Card = card; //Policy card at position n
    }
    else if (cardsDrafted == 2)
    {
      cardButton.setDisable(true);
      discard.setDisable(true);
      discardDraft2.setVisible(true);
      draft2Image.setImage(cardImage.getImage());
      draft2.setVisible(true);
      draft2Card = card; //Policy card at position n
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION, "Already two cards drafted.", ButtonType.CLOSE);
      alert.showAndWait();

      cardsDrafted = 2;
      return false;
    }

    return true;
  }

  /**
   * Resets cards every time this scene is shown.
   */
  private void resetCards()
  {
    draft1.setVisible(false);
    draft1Image.setImage(null);
    discardDraft1.setVisible(false);
    draft2.setVisible(false);
    draft2Image.setImage(null);
    discardDraft2.setVisible(false);

    card1.setVisible(true);
    card1.setDisable(false);
    discard1.setVisible(true);
    discard1.setDisable(false);
    card2.setVisible(true);
    card2.setDisable(false);
    discard2.setVisible(true);
    discard2.setDisable(false);
    card3.setVisible(true);
    card3.setDisable(false);
    discard3.setVisible(true);
    discard3.setDisable(false);
    card4.setVisible(true);
    card4.setDisable(false);
    discard4.setVisible(true);
    discard4.setDisable(false);
    card5.setVisible(true);
    card5.setDisable(false);
    discard5.setVisible(true);
    discard5.setDisable(false);
    card6.setVisible(true);
    card6.setDisable(false);
    discard6.setVisible(true);
    discard6.setDisable(false);
    card7.setVisible(true);
    card7.setDisable(false);
    discard7.setVisible(true);
    discard7.setDisable(false);

    disNum = 0;
    discardedNum.setText("" + disNum);
    numCardsinHand = 7;
    cardsDrafted = 0;

    draft1Card = null;
    draft2Card = null;
  }


  /**
   * When draw pile is clicked, this method is called and replaces an empty spot in the player's
   * hand with another random card.
   */
  private void drawNewCard()
  {
    if (numCardsinHand < 7)
    {
      if (!card1.isVisible())
      {
        card1.setVisible(true);
        discard1.setVisible(true);
        numCardsinHand++;
      }
      else if (!card2.isVisible())
      {
        card2.setVisible(true);
        discard2.setVisible(true);
        numCardsinHand++;
      }
      else if (!card3.isVisible())
      {
        card3.setVisible(true);
        discard3.setVisible(true);
        numCardsinHand++;
      }
      else if (!card4.isVisible())
      {
        card4.setVisible(true);
        discard4.setVisible(true);
        numCardsinHand++;
      }
      else if (!card5.isVisible())
      {
        card5.setVisible(true);
        discard5.setVisible(true);
        numCardsinHand++;
      }
      else if (!card6.isVisible())
      {
        card6.setVisible(true);
        discard6.setVisible(true);
        numCardsinHand++;
      }
      else if (!card7.isVisible())
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


  /**
   * When user is ready to vote, this method is called and saves the cards that the user drafted.
   */
  private void saveDraftedCards()
  {
    getGameController().playDrafts(draft1Card, draft2Card);
  }


  /**
   * Called whenever the user clicks on a black X.
   * Related card is found and removed from hand.
   * Card is sent to the top of the discard pile.
   * @param event Mouse event.
   */
  @FXML
  public void discard(MouseEvent event)
  {
    Label discard = (Label) event.getSource();
    if (discard == discard1)
    {
      numCardsinHand--;
      card1.setVisible(false);
      discard1.setVisible(false);
      lastDiscardImage.setImage(card1Image.getImage());
      disNum++;
      discardedNum.setText("" + disNum);
      getGameController().player.discardCard(0);
    }
    else if (discard == discard2)
    {
      numCardsinHand--;
      card2.setVisible(false);
      discard2.setVisible(false);
      lastDiscardImage.setImage(card2Image.getImage());
      disNum++;
      discardedNum.setText("" + disNum);
      getGameController().player.discardCard(1);
    }
    else if (discard == discard3)
    {
      numCardsinHand--;
      card3.setVisible(false);
      discard3.setVisible(false);
      lastDiscardImage.setImage(card3Image.getImage());
      disNum++;
      discardedNum.setText("" + disNum);
      getGameController().player.discardCard(2);
    }
    else if (discard == discard4)
    {
      numCardsinHand--;
      card4.setVisible(false);
      discard4.setVisible(false);
      lastDiscardImage.setImage(card4Image.getImage());
      disNum++;
      discardedNum.setText("" + disNum);
      getGameController().player.discardCard(3);
    }
    else if (discard == discard5)
    {
      numCardsinHand--;
      card5.setVisible(false);
      discard5.setVisible(false);
      lastDiscardImage.setImage(card5Image.getImage());
      disNum++;
      discardedNum.setText("" + disNum);
      getGameController().player.discardCard(4);
    }
    else if (discard == discard6)
    {
      numCardsinHand--;
      card6.setVisible(false);
      discard6.setVisible(false);
      lastDiscardImage.setImage(card6Image.getImage());
      disNum++;
      discardedNum.setText("" + disNum);
      getGameController().player.discardCard(5);
    }
    else if (discard == discard7)
    {
      numCardsinHand--;
      card7.setVisible(false);
      discard7.setVisible(false);
      lastDiscardImage.setImage(card7Image.getImage());
      disNum++;
      discardedNum.setText("" + disNum);
      getGameController().player.discardCard(6);
    }
    else if (discard == discardDraft1)
    {
      //Swap card2 with card1 and undraft card2
      //This keeps things consistent.
      if(cardsDrafted == 2)
      {

        Image temp = draft1Image.getImage();
        draft1Image.setImage(draft2Image.getImage());
        draft2Image.setImage(temp);

        draft1Card = draft2Card;
        draft2Card = null;

        unDraft(draft2Image, discardDraft2, draft2);
      }
      else
      {
        draft1Card = null;
        unDraft(draft1Image, discardDraft1, draft1);
      }

    }
    else if (discard == discardDraft2)
    {
      draft2Card = null;
      unDraft(draft2Image, discardDraft2, draft2);
    }
  }

  private void unDraft(ImageView draftImage, Label discardDraft, Button draft)
  {
    cardsDrafted--;
    if (draftImage.getImage() == card1Image.getImage() && card1Image.isDisabled())
    {
      card1.setDisable(false);
      discard1.setDisable(false);
      card1Image.setDisable(false);
    }
    else if (draftImage.getImage() == card2Image.getImage() && card2Image.isDisabled())
    {
      card2.setDisable(false);
      discard2.setDisable(false);
      card2Image.setDisable(false);
    }
    else if (draftImage.getImage() == card3Image.getImage() && card3Image.isDisabled())
    {
      card3.setDisable(false);
      discard3.setDisable(false);
      card3Image.setDisable(false);
    }
    else if (draftImage.getImage() == card4Image.getImage() && card4Image.isDisabled())
    {
      card4.setDisable(false);
      discard4.setDisable(false);
      card4Image.setDisable(false);
    }
    else if (draftImage.getImage() == card5Image.getImage() && card5Image.isDisabled())
    {
      card5.setDisable(false);
      discard5.setDisable(false);
      card5Image.setDisable(false);
    }
    else if (draftImage.getImage() == card6Image.getImage() && card6Image.isDisabled())
    {
      card6.setDisable(false);
      discard6.setDisable(false);
      card6Image.setDisable(false);
    }
    else if (draftImage.getImage() == card7Image.getImage() && card7Image.isDisabled())
    {
      card7.setDisable(false);
      discard7.setDisable(false);
      card7Image.setDisable(false);
    }

    draft.setVisible(false);
    draftImage.setImage(null); //Prevents playing undrafted card.
    discardDraft.setVisible(false);
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

    System.out.println("myRegion: " + getGameController().getMyRegion());

    makeAllInvisible();

    if (ImageRegion.CALIFORNIA.contains(x, y))
    {
      cali.setVisible(true);
      caliLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.CALIFORNIA);
      makeGraphs(EnumRegion.CALIFORNIA);


      System.out.println("Selected cali");
    }
    else if (ImageRegion.HEARTLAND.contains(x, y))
    {
      heartland.setVisible(true);
      heartLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.HEARTLAND);
      makeGraphs(EnumRegion.HEARTLAND);

      System.out.println("Selected heartland");
    }
    else if (ImageRegion.MOUNTAINST.contains(x, y))
    {
      mountSt.setVisible(true);
      mountLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.MOUNTAIN);
      makeGraphs(EnumRegion.MOUNTAIN);

      System.out.println("Selected Mountain States");
    }
    else if (ImageRegion.NORTHPLAINS.contains(x, y))
    {
      nPlains.setVisible(true);
      nPlainLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.NORTHERN_PLAINS);
      makeGraphs(EnumRegion.NORTHERN_PLAINS);

      System.out.println("Selected North Plains");
    }
    else if (ImageRegion.NORTHEAST.contains(x, y))
    {
      northSt.setVisible(true);
      neLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.NORTHERN_CRESCENT);
      makeGraphs(EnumRegion.NORTHERN_CRESCENT);

      System.out.println("Selected Northeast");
    }
    else if (ImageRegion.SOUTHEAST.contains(x, y))
    {

      southEast.setVisible(true);
      seLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.SOUTHEAST);
      makeGraphs(EnumRegion.SOUTHEAST);
      System.out.println("Selected Southeast");

    }
    else if (ImageRegion.SOUTHPLAINS.contains(x, y))
    {
      sPlains.setVisible(true);
      sPlainLabel.setVisible(true);
      currentRegion.setText("Current Region:  " + EnumRegion.SOUTHERN_PLAINS);
      makeGraphs(EnumRegion.SOUTHERN_PLAINS);

      System.out.println("Selected South Plains");
    }

  }

  /**
   * Allows regions to be highlighted when drafting scene begins.
   */
  @FXML
  public void showMyRegion()
  {
    highlightMyRegion(getGameController().getMyRegion());
    playerRegion
        .setText("My Region: " + getGameController().getMyRegion());
    currentRegion
        .setText("Current Region: " + getGameController().getMyRegion());
  }

  /**
   * Checks to see what player's current region is and highlights it.
   * Also shows graph info on each region when highlighted.
   * @param myRegion Player's EnumRegion.
   */
  public void highlightMyRegion(EnumRegion myRegion)
  {
    if (myRegion == EnumRegion.CALIFORNIA)
    {
      cali.setVisible(true);
      makeGraphs(EnumRegion.CALIFORNIA);
    }
    else if (myRegion == EnumRegion.MOUNTAIN)
    {
      mountSt.setVisible(true);
      makeGraphs(EnumRegion.MOUNTAIN);
    }
    else if (myRegion == EnumRegion.NORTHERN_CRESCENT)
    {
      northSt.setVisible(true);
      makeGraphs(EnumRegion.NORTHERN_CRESCENT);
    }
    else if (myRegion == EnumRegion.NORTHERN_PLAINS)
    {
      nPlains.setVisible(true);
      makeGraphs(EnumRegion.NORTHERN_PLAINS);
    }
    else if (myRegion == EnumRegion.SOUTHEAST)
    {
      southEast.setVisible(true);
      makeGraphs(EnumRegion.SOUTHEAST);
    }
    else if (myRegion == EnumRegion.SOUTHERN_PLAINS)
    {
      sPlains.setVisible(true);
      makeGraphs(EnumRegion.SOUTHERN_PLAINS);
    }
    else if (myRegion == EnumRegion.HEARTLAND)
    {
      heartland.setVisible(true);
      makeGraphs(EnumRegion.HEARTLAND);
    }
  }

  /**
   * Quickly displays graphs for each region when one is selected.
   * @param region Region currently selected.
   */
  private void makeGraphs(EnumRegion region)
  {
    cropGraph.setCenter(CropChart.makeRegionFoodPieChart(Main
        .getGameController().getRegion(region)));
//    revGraph.setCenter(CropChart.makeOneRegionRevenuePieChart(player.getRegionHistories().get(region)));
    hdiGraph.setCenter(CropChart.makeHDIPieChart(new RegionHistory(region)));
   // popGraph.setCenter(CropChart.makeLineChartRegionPopulation(player.getRegionHistories().get(region)));
    revGraph.setCenter(CropChart.makeOneRegionRevenuePieChart(new RegionHistory(region)));
    popGraph.setCenter(CropChart.makeLineChartRegionRevenue(new RegionHistory(region)));
  }

  /**
   * When mouse is over product button, show a label with it's name.
   * @param event Mouse event.
   */
  @FXML
  public void mouseOverProduce(MouseEvent event)
  {
    hideCropLabels();

    if (event.getSource() == appleButton)
    {
      appleLabel.setVisible(true);
    }
    else if (event.getSource() == grainsButton)
    {
      grainLabel.setVisible(true);
    }
    else if (event.getSource() == citrusButton)
    {
      citrusLabel.setVisible(true);
    }
    else if (event.getSource() == feedButton)
    {
      feedLabel.setVisible(true);
    }
    else if (event.getSource() == dairyButton)
    {
      dairyLabel.setVisible(true);
    }
    else if (event.getSource() == fishButton)
    {
      fishLabel.setVisible(true);
    }
    else if (event.getSource() == meatButton)
    {
      meatLabel.setVisible(true);
    }
    else if (event.getSource() == nutButton)
    {
      nutLabel.setVisible(true);
    }
    else if (event.getSource() == oilButton)
    {
      oilLabel.setVisible(true);
    }
    else if (event.getSource() == poultryButton)
    {
      poultryLabel.setVisible(true);
    }
    else if (event.getSource() == veggieButton)
    {
      veggieLabel.setVisible(true);
    }
    else if (event.getSource() == specialButton)
    {
      specialLabel.setVisible(true);
    }
    else if (event.getSource() == closeWindow ||
        event.getSource() == closeWindow2 ||
        event.getSource() == closeWindow3 ||
        event.getSource() == closeWindow4 ||
        event.getSource() == closeWindow5 ||
        event.getSource() == closeWindow6 ||
        event.getSource() == closeWindow7 ||
        event.getSource() == closeWindow8 ||
        event.getSource() == closeWindow9 ||
        event.getSource() == closeWindow10 ||
        event.getSource() == closeWindow11 ||
        event.getSource() == closeWindow12)
    {
      changeTextColor(Color.BLUE);

    }

  }


  /**
   * Shows region labels when mouse is over a specific region.
   * @param x X-coordinate on map.
   * @param y Y-coordinate on map.
   */
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

    for (int i = 0; i < 5; i++)
    {
      dataList.add(new PieChart.Data(i + "", i * 5));
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
   * Checks for mouse movements on scene.
   * @param event Mouse event.
   */
  @FXML
  public void mouseMoved(MouseEvent event)
  {
    double x = event.getX();
    double y = event.getY();

    hideRegionLabels();
    showLabel(x, y);

  }


  /**
   * Hides certain labels when mouse is no longer on them.
   */
  @FXML
  public void mouseExit()
  {
    hideCropLabels();
    changeTextColor(Color.BLACK);
  }

  /**
   * Closes produce windows by default.
   */
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
   * Changes text color for produce close[x]
   * @param color Specific color.
   */
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


  /**
   * Hides regions unless one is clicked on.
   */
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

  /**
   * Hides region labels until mouse over.
   */
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

  /**
   * Hides crop labels until mouse over.
   */
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

}
