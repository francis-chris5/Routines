
package Routines;

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
    private XYChart.Series resource = new XYChart.Series();
    private XYChart.Series cost = new XYChart.Series();
    
    public ResourceBurnUp(){
        resource.setName("Resource Usage");
        cost.setName("Cost Projection");
        
        resource.getData().add(new XYChart.Data("test 1", 0.87));
        resource.getData().add(new XYChart.Data("test 2", 0.34));
        resource.getData().add(new XYChart.Data("test 3", 0.72));
        
        cost.getData().add(new XYChart.Data("test 1", 0.23));
        cost.getData().add(new XYChart.Data("test 2", 0.67));
        cost.getData().add(new XYChart.Data("test 3", 0.42));
        
        this.chart.getData().addAll(resource, cost);
        this.getChildren().add(chart);
    }//end default constructor
    
}//end ResourceBurnUp
