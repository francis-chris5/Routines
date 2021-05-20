
package Routines;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;


/**
 * simply the duration of each task as a portion of the total, round to an
 * integer for the way the data series is set by default
 * @author Chris
 */
public class TimePie extends HBox {
    
    private final PieChart chart = new PieChart();
    private ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
            new PieChart.Data("Test 1", 25),
            new PieChart.Data("Test 2", 32),
            new PieChart.Data("Test 3", 43)
    );
    
    
    public TimePie(){
        chart.setTitle("First Test Data Set");
        chart.setData(data);
        this.getChildren().add(chart);
    }//end default constructor
    
    
}
