
package Routines;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;


public class ResourceBurnUp extends HBox{
    
        ///////////////////////////////////////////////  DATAFIELDS  /////////
    
    private NumberAxis yAxis = new NumberAxis();
    private CategoryAxis xAxis = new CategoryAxis();
    private AreaChart<String, Number> chart = new AreaChart<>(xAxis, yAxis);
    private XYChart.Series data = new XYChart.Series();
    private XYChart.Series costProjection = new XYChart.Series();
    
    
    
    
    
    
    
    
        ///////////////////////////////////////////////  CONSTRUCTORS  ///////
    
    public ResourceBurnUp(Routine routine){
        chart.setTitle(routine.getRoutineName() + " Resource Burn");
        data.setName("Resource Expense");
        costProjection.setName("Projected Cost");
        setData(routine);
        setCostProjection(routine);
        chart.getData().addAll(data, costProjection);
        chart.setCreateSymbols(false);
        this.getChildren().add(chart);
    }//end default constructor
    
    
    
    
    
    
    
        /////////////////////////////////////////////  CHART METHODS  ////////
    
    public void setData(Routine routine){
        try{
            double runningTotal = 0.0;
            for(int i = 0; i < routine.getRoutineTasks().size(); i++){
                double taskExpense = 0.0;
                for(int j=0; j<routine.getRoutineTasks().get(i).getAssignedResources().size(); j++){
                    if(routine.getRoutineTasks().get(i).getAssignedResources().get(j).getUnits() != CostBasis.FLAT_FEE){
                        taskExpense += routine.getRoutineTasks().get(i).getAssignedResources().get(j).getCost()* routine.getRoutineTasks().get(i).getDuration();
                    }
                    else{
                        taskExpense += routine.getRoutineTasks().get(i).getAssignedResources().get(j).getCost() / routine.getRoutineTasks().get(i).getAssignedResources().get(j).getAssignedTo().size();
                    }
                }
                data.getData().add(new XYChart.Data(routine.getRoutineTasks().get(i).getName(), taskExpense + runningTotal));
                runningTotal += taskExpense;
            }
        }
        catch(Exception e){
            //probably task.assignedResources is empty
        }
    }//end setData()
    
    
    
    
    public void setCostProjection(Routine routine){
        if(!routine.getRoutineTasks().isEmpty()){
            double step = routine.getRoutineBudget() / data.getData().size();
            for(int i = 0; i < data.getData().size(); i++){
                costProjection.getData().add(new XYChart.Data(routine.getRoutineTasks().get(i).getName(), (i+1) * step));
            }
        }
    }//end setCostProjection()
    
}//end ResourceBurnUp
