package starvationevasion.teamrocket.main;

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

  private boolean mouseOnSE = false;
  private boolean mouseOnCali = false;
  private boolean mouseOnMount = false;
  private boolean mouseOnSPlains = false;
  private boolean mouseOnNPlains = false;
  private boolean mouseOnHeart = false;
  private boolean mouseOnNE = false;

  private boolean[] mouseOn = {mouseOnCali,mouseOnMount,mouseOnNE,
      mouseOnSE,mouseOnNPlains,mouseOnSPlains,mouseOnHeart};

  private boolean caliSelected = false;
  private boolean heartlandSelected = false;
  private boolean mountainSelected = false;
  private boolean nPlainSelected= false;
  private boolean northeastSelected = false;
  private boolean southeastSelected = false;
  private boolean sPlainSelected = false;


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
    System.out.println(x + "," + y);

    if(ImageRegion.CALIFORNIA.contains(x,y))//(x>25 && x <80 && y>70 && y<150)
    {
      mouseOnCali = true;
      cali.setVisible(true);
      caliLabel.setVisible(true);
    }
    if(ImageRegion.MOUNTAINST.contains(x,y))
    {
      mouseOnMount = true;
      mountSt.setVisible(true);
      mountLabel.setVisible(true);
    }
    if(ImageRegion.NORTHPLAINS.contains(x,y))// || x>200 && x<250 && y>=5 && y<=160)
    {
      mouseOnNPlains = true;
      nPlains.setVisible(true);
      nPlainLabel.setVisible(true);
    }
    else if(ImageRegion.SOUTHPLAINS.contains(x,y))
    {
      mouseOnSPlains = true;
      sPlains.setVisible(true);
      sPlainLabel.setVisible(true);
    }
    else if(ImageRegion.HEARTLAND.contains(x,y))
    {
      mouseOnHeart = true;
      heartland.setVisible(true);
      heartLabel.setVisible(true);
    }
    else if(ImageRegion.NORTHEAST.contains(x,y))
    {
      mouseOn[2]=true;
      northSt.setVisible(true);
      neLabel.setVisible(true);
    }
    else if(ImageRegion.SOUTHEAST.contains(x,y))
    {
      mouseOnSE = true;
      southEast.setVisible(true);
      seLabel.setVisible(true);
    }
  }

  @FXML
  public void mouseExited(MouseEvent event)
  {
    System.out.println("mouse has left image");

    if(!caliSelected)
    {
      cali.setVisible(false);
      caliLabel.setVisible(false);
    }
    if(!mountainSelected)
    {
      mountSt.setVisible(false);
      mountLabel.setVisible(false);
    }
    if(!nPlainSelected)
    {
      nPlains.setVisible(false);
      nPlainLabel.setVisible(false);
    }
    if(!sPlainSelected)
    {
      sPlains.setVisible(false);
      sPlainLabel.setVisible(false);
    }
    if(!heartlandSelected)
    {
      heartland.setVisible(false);
      heartLabel.setVisible(false);
    }
    if(!northeastSelected)
    {
      northSt.setVisible(false);
      neLabel.setVisible(false);
    }
    if(!southeastSelected)
    {
      southEast.setVisible(false);
      seLabel.setVisible(false);
    }

  }

  @FXML
  public void clickedRegion(MouseEvent event)
  {

    double x = event.getX();
    double y = event.getY();

   if(ImageRegion.CALIFORNIA.contains(x,y))
   {
     cali.setVisible(true);
     caliSelected = true;
     System.out.println("Selected cali");
   }
    else if(ImageRegion.HEARTLAND.contains(x,y))
   {
     heartland.setVisible(true);
     heartlandSelected = true;
     System.out.println("Selected heartland");
   }
    else if(ImageRegion.MOUNTAINST.contains(x,y))
   {
     mountSt.setVisible(true);
     mountainSelected = true;
     System.out.println("Selected Mountain States");
   }
    else if(ImageRegion.NORTHPLAINS.contains(x,y))
   {
     nPlains.setVisible(true);
     nPlainSelected = true;
     System.out.println("Selected North Plains");
   }
    else if(ImageRegion.NORTHEAST.contains(x,y))
   {
     northSt.setVisible(true);
     northeastSelected = true;
     System.out.println("Selected Northeast");
   }
   else if(ImageRegion.SOUTHEAST.contains(x,y))
   {
     southEast.setVisible(true);
     southeastSelected = true;
     System.out.println("Selected Southeast");

   }
   else if(ImageRegion.SOUTHPLAINS.contains(x,y))
   {
     sPlains.setVisible(true);
     sPlainSelected = true;
     System.out.println("Selected South Plains");

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

