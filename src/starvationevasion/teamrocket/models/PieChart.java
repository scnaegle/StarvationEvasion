package starvationevasion.teamrocket.models;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import starvationevasion.common.EnumFood;
import starvationevasion.common.EnumRegion;

import javax.swing.*;

/**
 * Created by Tyler on 11/18/2015.
 */
public class PieChart extends ApplicationFrame
{
  private Region region;

  public PieChart(String title, Region region)
  {
    super(title);
    this.region = region;
    setContentPane(createRootPane());
  }

  private static PieDataset createDataset()
  {
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue(EnumFood.CITRUS.name(), new Double(20));
    return dataset;
  }

  private static JFreeChart createChart(PieDataset dataset)
  {
    JFreeChart chart =
        ChartFactory.createPieChart("Crop Yield", dataset, true, true, false);
    return chart;
  }

  public static JPanel createDemoPanel()
  {
    JFreeChart chart = createChart(createDataset());
    return  new ChartPanel(chart);
  }


  public static void main( String[ ] args )
  {
    PieChart demo = new PieChart( "Crop Yeild", new Region(EnumRegion.CALIFORNIA));
    demo.setSize( 560 , 367 );
    RefineryUtilities.centerFrameOnScreen(demo);
    demo.setVisible( true );
  }


}
