
package Routines;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;


public class GanttChart extends Pane{

        ////////////////////////////////////////////////  DATAFIELDS  ////////
    
    //private CategoryAxis yAxis = new CategoryAxis();
    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
    private ObservableList<XYChart.Series> taskList = FXCollections.observableArrayList();
    
    
    
    
    
    
    
    
        //////////////////////////////////////////////  CONSTRUCTORS  /////////
    
    public GanttChart(Routine routine){
        chart.setTitle(routine.getRoutineName() + " Gantt Chart");
        xAxis.setSide(Side.TOP);
        yAxis.setTickLabelsVisible(false);
        setData(routine);
        chart.setPrefHeight(taskList.size()*22*2.3);
        for(int i = 0; i < taskList.size(); i++){
            chart.getData().add(taskList.get(i));
        }
        chart.setCreateSymbols(false);
        chart.setLegendSide(Side.TOP);
        this.getChildren().add(chart);
    }//end default-constructor
    
    
    
    
    
    
    
    
        ////////////////////////////////////////////  CHART METHODS  /////////
    
    public void setData(Routine routine){
        for(int i=0; i < routine.routineTasks.size(); i++){
            XYChart.Series data = new XYChart.Series();
            for(LocalDate j = routine.routineTasks.get(i).getStartDate(); j.compareTo(routine.routineTasks.get(i).getEndDate()) < 0; j = j.plus(1, routine.getDefaultTimescale().getChronoUnits())) {
                //data.getData().add(new XYChart.Data(j.toString(), routine.routineTasks.get(i).getName()));
                data.getData().add(new XYChart.Data(j.toString(), -i));
                data.setName(routine.routineTasks.get(i).getName());
            }
            taskList.add(data);
        }
    }//end setData()
    
}//end GanttChart
