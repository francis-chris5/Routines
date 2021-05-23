
package Routines;


import java.time.LocalDateTime;
import java.time.LocalTime;
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
        chart.setPrefHeight(taskList.size()*22 + 250);
        for(int i = 0; i < taskList.size(); i++){
            chart.getData().add(taskList.get(i));
        }
        chart.setCreateSymbols(false);
        chart.setLegendSide(Side.TOP);
        this.getChildren().add(chart);
    }//end default-constructor
    
    
    
    
    
    
    
    
        ////////////////////////////////////////////  CHART METHODS  /////////
    
    public void setData(Routine routine){
        for(int i=0; i < routine.getRoutineTasks().size(); i++){
            XYChart.Series data = new XYChart.Series();
            for(LocalDateTime j = routine.getRoutineTasks().get(i).getStartTime(); !j.equals(routine.getRoutineTasks().get(i).getEndTime()); j = j.plus(1, routine.getDefaultTimescale().getChronoUnits())) {
                if(routine.getWorkHours().getWorkingHours().contains(LocalTime.of(j.getHour(), 0, 0)) && routine.getWorkHours().getWorkingDays().contains(j.getDayOfWeek())){
                    data.getData().add(new XYChart.Data(j.toString(), -i));
                    data.setName(routine.getRoutineTasks().get(i).getName());
                }
            }
            taskList.add(data);
        }
    }//end setData()
    
}//end GanttChart
