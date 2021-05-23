
package Routines;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class ResourceUsageChart extends Pane{

        ////////////////////////////////////////////////  DATAFIELDS  ////////
    
    //private CategoryAxis yAxis = new CategoryAxis();
    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private NumberAxis zAxis = new NumberAxis();
    private LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
    private ObservableList<XYChart.Series> taskList = FXCollections.observableArrayList();
    private ObservableList<XYChart.Series> resourceList = FXCollections.observableArrayList();
    private StackedBarChart<String, Number> chart2 = new StackedBarChart(xAxis, zAxis);
    
    
    
    
    
    
        //////////////////////////////////////////////  CONSTRUCTORS  /////////
    
    public ResourceUsageChart(Routine routine){
        chart.setTitle(routine.getRoutineName() + " Resource Usage Chart");
        chart.setTitle(routine.getRoutineName() + " Resource Usage Stacked Bar Chart");
        xAxis.setSide(Side.TOP);
        yAxis.setTickLabelsVisible(false);
        setData(routine);
        //chart.setPrefHeight(taskList.size()*22 + 250);
        for(int i = 0; i < taskList.size(); i++){
            chart.getData().add(taskList.get(i));
        }
        for(int i = 0; i < resourceList.size(); i++){
            chart2.getData().add(resourceList.get(i));
        }
        chart.setCreateSymbols(false);
        chart.setLegendSide(Side.TOP);
        VBox vbxCharts = new VBox(chart, chart2);
        this.getChildren().addAll(vbxCharts);
    }//end default-constructor
    
    
    
    
    
    
    
    
        ////////////////////////////////////////////  CHART METHODS  /////////
    
    public void setData(Routine routine){
        for(int i=0; i < routine.getAvailableResources().size(); i++){
            XYChart.Series data = new XYChart.Series();
            
            for(int j = 0; j < routine.getAvailableResources().get(i).getAssignedTo().size(); j++){
                data.getData().add(new XYChart.Data(routine.getAvailableResources().get(i).getAssignedTo().get(j).getName(), -i-1));
                data.setName(routine.getAvailableResources().get(i).getName());
                XYChart.Series data2 = new XYChart.Series();
                data2.getData().add(new XYChart.Data(routine.getAvailableResources().get(i).getName(), routine.getAvailableResources().get(i).getAssignedTo().get(j).getDuration()));
                data2.setName(routine.getAvailableResources().get(i).getAssignedTo().get(j).getName());
                resourceList.add(data2);
            }
            taskList.add(data);
            
        }
    }//end setData()
    
}//end ResourceUsageChart
