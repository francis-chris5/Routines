
package Routines;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;


public class StaminaComplexityControl extends HBox {
    
        ////////////////////////////////////////////////  DATAFIELDS  ////////
    
    private NumberAxis yAxis = new NumberAxis();
    private CategoryAxis xAxis = new CategoryAxis();
    private LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);
    private XYChart.Series data = new XYChart.Series();
    private XYChart.Series ucl = new XYChart.Series();
    private XYChart.Series lcl = new XYChart.Series();
    
    
    
    
    
    
    
    
        /////////////////////////////////////////////  CONSTRUCTORS  /////////
    
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
    
    
    
    
    
    
    
    
        /////////////////////////////////////////////  CHART METHODS  ////////
    
    public void setControlLimits(Routine routine){
        if(!routine.routineTasks.isEmpty()){
            for(int i=0; i < routine.routineTasks.size(); i++){
                ucl.getData().add(new XYChart.Data(routine.routineTasks.get(i).getName(), 1.0));
                lcl.getData().add(new XYChart.Data(routine.routineTasks.get(i).getName(), -1.0));
            }
        }
    }//end setControlLimits()
    
    
    
    
    public void setData(Routine routine){
        try{
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
        }
        catch(Exception e){
            //probably task.asignedResource is empty
        }
    }//end setData()
    
}//end StaminaComplexityControl