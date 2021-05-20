
package Routines;

import java.util.LinkedList;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

/**
 * tasks on x axis, percentage on y, resource stamina (total per task) as a 
 * percentage of total resource stamina in one area and cost per task as a
 * percentage of total budget for other area
 * @author Chris
 */
public class ResourceBurnUp extends HBox{
    private final NumberAxis yAxis = new NumberAxis();
    private final CategoryAxis xAxis = new CategoryAxis();
    private final AreaChart<String, Number> chart = new AreaChart<String, Number>(xAxis, yAxis);
    private XYChart.Series data = new XYChart.Series();
    private XYChart.Series costProjection = new XYChart.Series();
    private double projection = 7654.32;
    
    
    public ResourceBurnUp(){
        chart.setTitle("Test Resource Burn");
        data.setName("Resource Expense");
        costProjection.setName("Projected Cost");
        
        setData();
        setCostProjection();

        
        chart.getData().addAll(data, costProjection);
        chart.setCreateSymbols(false);
        this.getChildren().add(chart);
    }//end default constructor
    
    
    
    
    public void setData(){
        LinkedList<Double> resourceCosts = new LinkedList<>();
        resourceCosts.add(23.50); //hourly rate
        resourceCosts.add(234.56); //FLAT_FEE
        resourceCosts.add(17.85); //hourly rate
        resourceCosts.add(22.22); //hourly rate
        resourceCosts.add(28.75); //hourly rate
        resourceCosts.add(321.75); //FLAT_FEE
        
        
            //FIGURE OUT A GOOD WAY TO DO THIS AFTER SOME REST... (might be easier when I have the actual task object with resource and duration lists in it)
                //the multiplier will just be the duration of the task
        double task0 = resourceCosts.get(0) * 5 + resourceCosts.get(3) * 5;
        double task1 = resourceCosts.get(0) * 2 + resourceCosts.get(3) * 2 + resourceCosts.get(1) + task0;
        double task2 = resourceCosts.get(2) * 4 + resourceCosts.get(3) * 4 + task0 + task1;
        double task3 = resourceCosts.get(0) * 2 + resourceCosts.get(2) * 2 + task0 + task1 + task2;
        double task4 = resourceCosts.get(4) * 5 + resourceCosts.get(5) + task0 + task1 + task2 + task3;
        double task5 = resourceCosts.get(0) * 2 + resourceCosts.get(3) * 2 + task0 + task1 + task2 + task3 + task4;
        
        
        data.getData().add(new XYChart.Data("task 0", task0));
        data.getData().add(new XYChart.Data("task 1", task1));
        data.getData().add(new XYChart.Data("task 2", task2));
        data.getData().add(new XYChart.Data("task 3", task3));
        data.getData().add(new XYChart.Data("task 4", task4));
        data.getData().add(new XYChart.Data("task 5", task5));
        

    }//end setData()
    
    
    
    
    public void setCostProjection(){
        double step = projection / data.getData().size();
        for(int i = 0; i < data.getData().size(); i++){
            costProjection.getData().add(new XYChart.Data("task " + i, (i+1) * step));
        }
    }//end setCostProjection()
    
}//end ResourceBurnUp
