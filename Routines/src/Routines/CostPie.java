
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
public class CostPie extends HBox {
    
    private PieChart chart = new PieChart();
    private ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    
    
    public CostPie(Routine routine){
        chart.setTitle(routine.getRoutineName() + " Cost Pie");
        setData(routine);
        chart.setData(data);
        this.getChildren().add(chart);
    }//end default constructor
    
    
    
    
    
    public void setData(Routine routine){
        LinkedList<Double> taskCost = new LinkedList<>();
        for(int i = 0; i < routine.routineTasks.size(); i++){
            for(int j = 0; j < routine.routineTasks.get(i).getAssignedResources().size(); j++){
                if(routine.routineTasks.get(i).getAssignedResources().get(j).getUnits() != CostBasis.FLAT_FEE){
                    taskCost.add(routine.routineTasks.get(i).getAssignedResources().get(j).getCost()* routine.routineTasks.get(i).getDuration());
                }
                else{
                    taskCost.add(routine.routineTasks.get(i).getAssignedResources().get(j).getCost() / routine.routineTasks.get(i).getAssignedResources().get(j).getAssignedTo().size());
                }
            }
        }
        
        double total = 0.0;
        for(int i = 0; i < taskCost.size(); i++){
            total += taskCost.get(i);
        }
        
        for(int i = 0; i < routine.routineTasks.size(); i++){
            data.add(new PieChart.Data(routine.routineTasks.get(i).getName(), taskCost.get(i)/total));
        }
    }//end setData()
    
}//end TimePie
