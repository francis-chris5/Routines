
package Routines;

import java.util.LinkedList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

/**
 * take the average stamina of all resources assigned to a task as a
 * ratio over complexity of the task and plot as a control, 
 * UCL - probably at 2???
 * LCL - probably at 0???
 * REMEMBER TO SUBTRACT 1 FROM THE ACTUAL RATIO TO MAKE 0 THE MATCH POINT
 * @author Chris
 */
public class StaminaComplexityControl extends HBox {
    private NumberAxis yAxis = new NumberAxis();
    private CategoryAxis xAxis = new CategoryAxis();
    private LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);
    private XYChart.Series data = new XYChart.Series();
    private XYChart.Series ucl = new XYChart.Series();
    private XYChart.Series lcl = new XYChart.Series();
    
    public StaminaComplexityControl(Routine routine){
        chart.setTitle(routine.getRoutineName() + " Control");
        data.setName("Stamina Vs. Complexity");
        ucl.setName("+ stamina > complexity");
        lcl.setName("- complexity > staimina");
        
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(-2.5);
        yAxis.setUpperBound(2.5);
        
        setData(routine);
        setControlLimits(routine);
        
        
        chart.getData().addAll(ucl, lcl, data);
        chart.setCreateSymbols(false);
        chart.setLegendVisible(true);
        
        
        this.getChildren().add(chart);
    }//end default constructor
    
    
    public void setControlLimits(Routine routine){
        for(int i=0; i < routine.routineTasks.size(); i++){
            ucl.getData().add(new XYChart.Data(routine.routineTasks.get(i).getName(), 1.0));
            lcl.getData().add(new XYChart.Data(routine.routineTasks.get(i).getName(), -1.0));
        }
    }//end setControlLimits()
    
    
    
    
    public void setData(Routine routine){
        for(int i = 0; i < routine.routineTasks.size(); i++){
            double staminaSum = 0.0, ratio = 0.0;
            for(int j = 0; j < routine.routineTasks.get(i).getAssignedResources().size(); j++){
                staminaSum += routine.routineTasks.get(i).getAssignedResources().get(j).getStamina();
            }
            double staminaAverage = staminaSum / routine.routineTasks.get(i).getAssignedResources().size();
            if(routine.routineTasks.get(i).getComplexity() != 0){
                ratio = staminaAverage / routine.routineTasks.get(i).getComplexity();
            }
            else{
                ratio = staminaAverage / 0.0001;
            }
            data.getData().add(new XYChart.Data(routine.routineTasks.get(i).getName(), ratio - 1));
        }
    }//end setData()
    
}//end StaminaComplexityControl