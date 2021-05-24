
package Routines;


import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;


public class ResourceUsageChart extends Pane{

        ////////////////////////////////////////////////  DATAFIELDS  ////////
    
    //private CategoryAxis yAxis = new CategoryAxis();
    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private ObservableList<XYChart.Series> resourceList = FXCollections.observableArrayList();
    private StackedBarChart<String, Number> chart = new StackedBarChart(xAxis, yAxis);
    
    
    
    
    
    
        //////////////////////////////////////////////  CONSTRUCTORS  /////////
    
    public ResourceUsageChart(Routine routine){
        chart.setTitle(routine.getRoutineName() + " Resource Usage Chart");
        xAxis.setSide(Side.TOP);
        yAxis.setTickLabelsVisible(false);
        setData(routine);
        for(int i = 0; i < resourceList.size(); i++){
            chart.getData().add(resourceList.get(i));
        }
        this.getChildren().add(chart);
    }//end default-constructor
    
    
    
    
    
    
    
    
        ////////////////////////////////////////////  CHART METHODS  /////////
    
    public void setData(Routine routine){
        LinkedList<XYChart.Series> dataList = new LinkedList<>();
        for(int i = 0; i < routine.getRoutineTasks().size(); i++){
            dataList.add(new XYChart.Series());
            dataList.get(i).setName(routine.getRoutineTasks().get(i).getName());
        }
        for(int i = 0; i < routine.getRoutineTasks().size(); i++){
            for(int j = 0; j < routine.getRoutineTasks().get(i).getAssignedResources().size(); j++){
                dataList.get(i).getData().add(new XYChart.Data(routine.getRoutineTasks().get(i).getAssignedResources().get(j).getName(), routine.getRoutineTasks().get(i).getDuration()));
            }
        }
        resourceList.addAll(dataList);
    }//end setData()
    
}//end ResourceUsageChart
