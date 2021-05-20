
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
    
    private PieChart chart = new PieChart();
    private ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    
    
    public TimePie(Routine routine){
        chart.setTitle(routine.getRoutineName() + " Time Pie");
        setData(routine);
        chart.setData(data);
        this.getChildren().add(chart);
    }//end default constructor
    
    
    
    
    
    public void setData(Routine routine){
        double total = 0;
        for(int i = 0; i < routine.routineTasks.size(); i++){
            total += routine.routineTasks.get(i).getDuration();
        }
        
        for(int i = 0; i < routine.routineTasks.size(); i++){
            data.add(new PieChart.Data(routine.routineTasks.get(i).getName(), routine.routineTasks.get(i).getDuration()/total));
        }
    }//end setData()
    
}//end TimePie
