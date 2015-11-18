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

  public PieChart(String title)
  {
    super(title);
    setContentPane(createDemoPanel());
  }

  private static PieDataset createDataset()
  {
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue(EnumFood.CITRUS.name(), new Double(1));
    dataset.setValue(EnumFood.FRUIT.name(), new Double(1));
    dataset.setValue(EnumFood.FEED.name(), new Double(1));
    dataset.setValue(EnumFood.DAIRY.name(), new Double(1));
    dataset.setValue(EnumFood.GRAIN.name(), new Double(1));
    dataset.setValue(EnumFood.FISH.name(), new Double(1));
    dataset.setValue(EnumFood.MEAT.name(), new Double(1));
    dataset.setValue(EnumFood.SPECIAL.name(), new Double(1));
    dataset.setValue(EnumFood.VEGGIES.name(), new Double(1));
    dataset.setValue(EnumFood.NUT.name(), new Double(1));
    dataset.setValue(EnumFood.OIL.name(), new Double(1));
    dataset.setValue(EnumFood.POULTRY.name(), new Double(89));



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
    PieChart demo = new PieChart( "Crop Yeild" );
    demo.setSize( 560 , 367 );
    RefineryUtilities.centerFrameOnScreen(demo);
    demo.setVisible( true );
  }


}
