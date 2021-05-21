
package Routines;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;



public class TimePie extends HBox {
    
        //////////////////////////////////////////////  DATAFIELDS  /////////
    
    private PieChart chart = new PieChart();
    private ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    
    
    
    
    
    
    
    
        //////////////////////////////////////////  CONSTRUCTORS  ////////////
    
    public TimePie(Routine routine){
        chart.setTitle(routine.getRoutineName() + " Time Pie");
        setData(routine);
        chart.setData(data);
        this.getChildren().add(chart);
    }//end default constructor
    
    
    
    
    
    
    
    
        /////////////////////////////////////////  CHART METHODS  ///////////
    
    public void setData(Routine routine){
        if(!routine.routineTasks.isEmpty()){
            double total = 0;
            for(int i = 0; i < routine.routineTasks.size(); i++){
                total += routine.routineTasks.get(i).getDuration();
            }

            for(int i = 0; i < routine.routineTasks.size(); i++){
                data.add(new PieChart.Data(routine.routineTasks.get(i).getName(), routine.routineTasks.get(i).getDuration()/total));
            }
        }
    }//end setData()
    
}//end TimePie
