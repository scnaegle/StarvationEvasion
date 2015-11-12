package starvationevasion.main;

import javafx.animation.FadeTransition;
import javafx.event.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.awt.*;
import java.util.Objects;

public class Controller
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


  @FXML
  public void mouseEntered(MouseEvent event)
  {
    System.out.println("reached here");
    ImageView image = (ImageView)event.getSource();


  }

  @FXML
  public void mouseMoved(MouseEvent event)
  {
    System.out.println("MOVING");
    double x = event.getX();
    double y = event.getY();
    System.out.println(x + "," + y);

    if(x>=15 && x <=70 && y>=50 && y<200)
    {
      cali.setVisible(true);
    }
    else if/*(x>=10 && x<200 && y>=5 && y<200 ||*/( x>70 && x<200 && y>=5 && y<200)
    {
      mountSt.setVisible(true);
    }
    //southEast.setVisible(true);
  }

  @FXML
  public void mouseExited(MouseEvent event)
  {
    System.out.println("mouse has left image");

    cali.setVisible(false);
    mountSt.setVisible(false);
  }
}

