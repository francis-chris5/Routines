
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
    private final NumberAxis yAxis = new NumberAxis();
    private final CategoryAxis xAxis = new CategoryAxis();
    private final LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);
    private XYChart.Series data = new XYChart.Series();
    private XYChart.Series ucl = new XYChart.Series();
    private XYChart.Series lcl = new XYChart.Series();
    
    public StaminaComplexityControl(){
        chart.setTitle("Test Control");
        
        setData();
        setControlLimits();
        
        
        chart.getData().addAll(ucl, lcl, data);
        chart.setCreateSymbols(false);
        chart.setLegendVisible(false);
        
        this.getChildren().add(chart);
    }//end default constructor
    
    
    public void setControlLimits(){
        for(int i=0; i < data.getData().size(); i++){
            ucl.getData().add(new XYChart.Data("task " + i, 1.0));
            lcl.getData().add(new XYChart.Data("task " + i, -1.0));
        }
    }//end setControlLimits()
    
    
    
    
    public void setData(){
        LinkedList<Double> testStamina = new LinkedList<>();
        LinkedList<Double> testComplexity = new LinkedList<>();
        
            //just some random values to test with
        testStamina.add(72.0);
        testStamina.add(83.4);
        testStamina.add(19.1);
        testStamina.add(27.2);
        testStamina.add(65.7);
        
        testComplexity.add(23.2);
        testComplexity.add(32.4);
        testComplexity.add(48.7);
        testComplexity.add(32.3);
        testComplexity.add(83.9);
        testComplexity.add(58.2);
        testComplexity.add(27.4);
        
        data.getData().add(new XYChart.Data("task 0", testStamina.get(0)/testComplexity.get(0) - 1));
        data.getData().add(new XYChart.Data("task 1", testStamina.get(2)/testComplexity.get(1) - 1));
        data.getData().add(new XYChart.Data("task 2", ( (testStamina.get(1) + testStamina.get(4))/2)/testComplexity.get(2) - 1));
        data.getData().add(new XYChart.Data("task 3", testStamina.get(3)/testComplexity.get(3) - 1));
        data.getData().add(new XYChart.Data("task 4", testStamina.get(4)/testComplexity.get(4) - 1));
        data.getData().add(new XYChart.Data("task 5", ( (testStamina.get(2) + testStamina.get(1) + testStamina.get(4))/3)/testComplexity.get(5) - 1));
        data.getData().add(new XYChart.Data("task 6", testStamina.get(3)/testComplexity.get(6) - 1));
    }//end setData()
    
}//end StaminaComplexityControl