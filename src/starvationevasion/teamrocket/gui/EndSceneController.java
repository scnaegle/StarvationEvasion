package starvationevasion.teamrocket.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Handles the end scene and displays a winning label or losing label depending on HDI score by 2052.
 */
public class EndSceneController
{
  @FXML
  private Label winLabel, lose1Label, lose2Label, first, second, third, fourth, fifth, sixth, seventh,
      hdi1, hdi2, hdi3, hdi4, hdi5, hdi6, hdi7;
  private boolean winner;


  private void showGameLabel()
  {
    if (winner)
    {
      winLabel.setVisible(true);
    }
    else
    {
      lose1Label.setVisible(true);
      lose2Label.setVisible(true);
    }

    //display user names and hdi's

  }

  private void compareHDI()
  {
    //add all player's hdi's to array
    //sort array
    //if(player.hdi == array[0]){
    //  winner = true; }
    //else{
    //  loop through array and compare hdi's to each player.
  }
}
