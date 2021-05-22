
package Routines;

import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;



public class CostPie extends HBox {
    
        ///////////////////////////////////////////////  DATAFIELDS  /////////
    
    private PieChart chart = new PieChart();
    private ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    
    
    
    
    
    
    
    
        //////////////////////////////////////////////  CONSTRUCTORS  ////////
    
    public CostPie(Routine routine){
        chart.setTitle(routine.getRoutineName() + " Cost Pie");
        setData(routine);
        chart.setData(data);
        this.getChildren().add(chart);
    }//end default constructor
    
    
    
    
    
    
    
    
        ///////////////////////////////////////////  CHART METHODS  /////////
    
    public void setData(Routine routine){
        try{
            LinkedList<Double> taskCost = new LinkedList<>();
            for(int i = 0; i < routine.getRoutineTasks().size(); i++){
                for(int j = 0; j < routine.getRoutineTasks().get(i).getAssignedResources().size(); j++){
                    if(routine.getRoutineTasks().get(i).getAssignedResources().get(j).getUnits() != CostBasis.FLAT_FEE){
                        taskCost.add(routine.getRoutineTasks().get(i).getAssignedResources().get(j).getCost()* routine.getRoutineTasks().get(i).getDuration());
                    }
                    else{
                        taskCost.add(routine.getRoutineTasks().get(i).getAssignedResources().get(j).getCost() / routine.getRoutineTasks().get(i).getAssignedResources().get(j).getAssignedTo().size());
                    }
                }
            }
            double total = 0.0;
            for(int i = 0; i < taskCost.size(); i++){
                total += taskCost.get(i);
            }
            for(int i = 0; i < routine.getRoutineTasks().size(); i++){
                data.add(new PieChart.Data(routine.getRoutineTasks().get(i).getName(), taskCost.get(i)/total));
            }
        }
        catch(Exception e){
            //could be no tasks in routine, or it could be no resources in a task
        }
    }//end setData()
    
}//end TimePie
