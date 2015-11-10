package starvationevasion.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Controller{

  @FXML
  private Button appleButton;
  @FXML
  private Button grainsButton;

  @FXML
  public void buttonPressed(ActionEvent event)
  {
    Button button = (Button)event.getSource();
    if(button == appleButton){
      System.out.println("Pressed apple button");
    }
    else if(button == grainsButton){
      System.out.println("Pressed grains button");
    }


  }




}

