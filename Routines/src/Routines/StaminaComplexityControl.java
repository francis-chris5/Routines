
package Routines;

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
 * @author Chris
 */
public class StaminaComplexityControl extends HBox {
    private final NumberAxis yAxis = new NumberAxis();
    private final CategoryAxis xAxis = new CategoryAxis();
    private final LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);
    private XYChart.Series data = new XYChart.Series();
    
    public StaminaComplexityControl(){
        chart.setTitle("Test Data 1");
        data.setName("Test Data");
        data.getData().add(new XYChart.Data("test 1", 0.22));
        data.getData().add(new XYChart.Data("test 2", -0.29));
        data.getData().add(new XYChart.Data("test 3", 0.17));
        data.getData().add(new XYChart.Data("test 4", -0.34));
        
        this.chart.getData().add(data);
        this.getChildren().add(chart);
    }//end default constructor
    
    
}//end StaminaComplexityControl
