package starvationevasion.teamrocket.gui;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class GuiController
{


  @FXML
  private Button drawCardButton;
  @FXML
  private Button appleButton;
  @FXML
  private Button grainsButton;

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

  private boolean initialRegionSelected = false;
  private boolean clickedRegion = false;


  private boolean caliSelected;
  private boolean heartlandSelected;
  private boolean mountainSelected;
  private boolean nPlainSelected;
  private boolean northeastSelected;
  private boolean southeastSelected;
  private boolean sPlainSelected;

  private boolean[] regionSelected = {caliSelected,heartlandSelected,mountainSelected,nPlainSelected,northeastSelected
  , southeastSelected, sPlainSelected};

  @FXML
  public void buttonPressed(ActionEvent event)
  {
    Button button = (Button) event.getSource();
    if (button == drawCardButton)
    {
      System.out.println("Drawing card from deck");
    }
    else if (button == appleButton)
    {
      System.out.println("Pressed apple button");
    }
    else if (button == grainsButton)
    {
      System.out.println("Pressed grains button");
    }


  }

  @FXML
  public void updateLabel()
  {

  }


//  @FXML
//  public void mouseEntered(MouseEvent event)
//  {
//    System.out.println("reached here");
//    ImageView image = (ImageView)event.getSource();
//
//
//  }

  @FXML
  public void mouseMoved(MouseEvent event)
  {
    System.out.println("MOVING");
    double x = event.getX();
    double y = event.getY();
//    System.out.println(x + "," + y);
    if(!clickedRegion)
    {
      caliLabel.setVisible(false);
      mountLabel.setVisible(false);
      nPlainLabel.setVisible(false);
      sPlainLabel.setVisible(false);
      heartLabel.setVisible(false);
      seLabel.setVisible(false);
      neLabel.setVisible(false);

      if (ImageRegion.CALIFORNIA.contains(x, y))//(x>25 && x <80 && y>70 && y<150)
      {
        caliLabel.setVisible(true);
      }
      if (ImageRegion.MOUNTAINST.contains(x, y))
      {
        mountLabel.setVisible(true);
      }
      if (ImageRegion.NORTHPLAINS.contains(x, y))// || x>200 && x<250 && y>=5 && y<=160)
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
  }


  @FXML
  public void clickedRegion(MouseEvent event)
  {

    double x = event.getX();
    double y = event.getY();

    if(!clickedRegion)
    {
      if (ImageRegion.CALIFORNIA.contains(x, y))
      {
        cali.setVisible(true);
        clickedRegion = true;
        System.out.println("Selected cali");
      }
      else if (ImageRegion.HEARTLAND.contains(x, y))
      {
        heartland.setVisible(true);
        clickedRegion = true;
        System.out.println("Selected heartland");
      }
      else if (ImageRegion.MOUNTAINST.contains(x, y))
      {
        mountSt.setVisible(true);
        clickedRegion = true;
        System.out.println("Selected Mountain States");
      }
      else if (ImageRegion.NORTHPLAINS.contains(x, y))
      {
        nPlains.setVisible(true);
        clickedRegion = true;
        System.out.println("Selected North Plains");
      }
      else if (ImageRegion.NORTHEAST.contains(x, y))
      {
        northSt.setVisible(true);
        clickedRegion = true;
        System.out.println("Selected Northeast");
      }
      else if (ImageRegion.SOUTHEAST.contains(x, y))
      {
        southEast.setVisible(true);
        clickedRegion = true;
        System.out.println("Selected Southeast");

      }
      else if (ImageRegion.SOUTHPLAINS.contains(x, y))
      {
        sPlains.setVisible(true);
        clickedRegion = true;
        System.out.println("Selected South Plains");

      }
    }
  }

  @FXML
  public void selectInitialRegion(MouseEvent event)
  {
    double x = event.getX();
    double y = event.getY();

    if(!initialRegionSelected)
    {
      if (ImageRegion.CALIFORNIA1.contains(x, y))
      {
        cali.setVisible(true);
        initialRegionSelected = true;
        regionSelected[0] = true;
        System.out.println("Selected cali");
      }
      else if (ImageRegion.HEARTLAND1.contains(x, y))
      {
        heartland.setVisible(true);
        initialRegionSelected = true;
        System.out.println("Selected heartland");
      }
      else if (ImageRegion.MOUNTAINST1.contains(x, y))
      {
        mountSt.setVisible(true);
        initialRegionSelected = true;
        System.out.println("Selected Mountain States");
      }
      else if (ImageRegion.NORTHPLAINS1.contains(x, y))
      {
        nPlains.setVisible(true);
        initialRegionSelected = true;
        System.out.println("Selected North Plains");
      }
      else if (ImageRegion.NORTHEAST1.contains(x, y))
      {
        northSt.setVisible(true);
        initialRegionSelected = true;
        System.out.println("Selected Northeast");
      }
      else if (ImageRegion.SOUTHEAST1.contains(x, y))
      {
        southEast.setVisible(true);
        initialRegionSelected = true;
        System.out.println("Selected Southeast");

      }
      else if (ImageRegion.SOUTHPLAINS1.contains(x, y))
      {
        sPlains.setVisible(true);
        initialRegionSelected = true;
        System.out.println("Selected South Plains");
      }
    }
    else
    {

    }
  }

  @FXML
  public void findPolygon(MouseEvent event)
  {
    double x = event.getX();
    double y = event.getY();

    System.out.println(x+","+y+",");
  }
}

