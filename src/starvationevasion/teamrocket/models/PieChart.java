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
    setContentPane(createPanel());
  }

  private PieDataset createDataset()
  {
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue(EnumFood.CITRUS.name(), region.getCitrusYield());
    dataset.setValue(EnumFood.FRUIT.name(), region.getFruitYield());
    dataset.setValue(EnumFood.FEED.name(), region.getFeedYield());
    dataset.setValue(EnumFood.DAIRY.name(), region.getDairyYield());
    dataset.setValue(EnumFood.GRAIN.name(), region.getGrainYeild());
    dataset.setValue(EnumFood.FISH.name(), region.getFishYield());
    dataset.setValue(EnumFood.MEAT.name(), region.getMeatYield());
    dataset.setValue(EnumFood.SPECIAL.name(), region.getSpecialYield());
    dataset.setValue(EnumFood.VEGGIES.name(), region.getVeggieYield());
    dataset.setValue(EnumFood.NUT.name(), region.getNutYield());
    dataset.setValue(EnumFood.OIL.name(), region.getOilYield());
    dataset.setValue(EnumFood.POULTRY.name(), region.getPoultryYield());



    return dataset;
  }

  private static JFreeChart createPieChart(PieDataset dataset)
  {
    JFreeChart chart =
        ChartFactory.createPieChart("Crop Yield", dataset, true, true, false);
    return chart;
  }

  public JPanel createPanel()
  {
    JFreeChart chart = createPieChart(createDataset());
    return  new ChartPanel(chart);
  }


  public static void main( String[ ] args )
  {
    Region region = new Region(EnumRegion.CALIFORNIA);
//    region = new Region()
    region.setCitrusYield(10);
    region.setFruitYield(10);
    region.setFishYield(10);
    region.setDairyYield(10);
    region.setMeatYield(10);
    region.setPoultryYield(10);
    region.setSpecialYield(10);
    region.setOilYield(10);
    region.setVeggieYield(10);
    region.setNutYield(5);
    region.setFeedYield(3);
    region.setGrainYield(2);
    PieChart demo = new PieChart( "Crop Yeild", region );
    demo.setSize( 560 , 367 );
    RefineryUtilities.centerFrameOnScreen(demo);
    demo.setVisible( true );
  }


}
