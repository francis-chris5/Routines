
package Routines;

import java.util.LinkedList;
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
    private ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    
    
    public TimePie(){
        chart.setTitle("Test Time Pie");
        
        setData();
        
        chart.setData(data);
        this.getChildren().add(chart);
    }//end default constructor
    
    
    
    
    
    public void setData(){
        LinkedList<Integer> tasks = new LinkedList<>();
        
        tasks.add(7);
        tasks.add(2);
        tasks.add(4);
        tasks.add(9);
        tasks.add(2);
        
        double total = 0;
        for(int i = 0; i < tasks.size(); i++){
            total += tasks.get(i);
        }
        
        for(int i = 0; i < tasks.size(); i++){
            data.add(new PieChart.Data("task " + i, tasks.get(i)/total));
        }
    }//end setData()
    
}//end TimePie
