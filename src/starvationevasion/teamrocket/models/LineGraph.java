package starvationevasion.teamrocket.models;

import org.jfree.ui.ApplicationFrame;

/**
 * Created by Tyler on 11/18/2015.
 */
public class LineGraph extends ApplicationFrame
{
  public LineGraph(String title)
  {
    super(title);
    setContentPane(createRootPane());
  }


}
