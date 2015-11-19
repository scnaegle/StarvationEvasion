package starvationevasion.teamrocket.models;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.*;

/**
 * Created by Tyler on 11/18/2015.
 */
public class LineGraph extends ApplicationFrame
{
  public LineGraph(String title, String graphTitle)
  {
    super(title);
    JFreeChart graph = ChartFactory.createLineChart(graphTitle, "Years",
        "Crop Output", createDataset(), PlotOrientation.VERTICAL, true, true,
        false);
    ChartPanel graphPanel = new ChartPanel(graph);
    //graph.setPreferredSize(new java.awt.Dimension(560, 367));
    setContentPane(graphPanel);
  }

  private DefaultCategoryDataset createDataset()
  {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    dataset.addValue(new Double(50), "Crop Output", "1980");
    dataset.addValue(new Double(30), "Crop Output", "1981");
    return dataset;
  }

  public static void main(String[] args)
  {
    LineGraph chart =
        new LineGraph("Crop Output vs Year", "Amount of Crop Output vs years");
      chart.pack();
      RefineryUtilities.centerFrameOnScreen(chart);
      chart.setVisible(true);
  }

}
