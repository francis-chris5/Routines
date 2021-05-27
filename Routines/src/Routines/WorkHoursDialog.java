
package Routines;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class WorkHoursDialog extends Dialog implements Initializable{

        /////////////////////////////////////////////  DATAFIELDS  /////////
    
    @FXML
    ComboBox cmbWeekStart;
    @FXML
    ComboBox cmbWeekEnd;
    @FXML
    ComboBox cmbDayStart;
    @FXML
    ComboBox cmbLunchBreak;
    @FXML
    ComboBox cmbDayEnd;
    @FXML
    DatePicker dtHolidays;
    @FXML
    ListView lstHolidays;
    
    private DayOfWeek weekStart;
    private DayOfWeek weekEnd;
    private LocalTime dayStart;
    private LocalTime dayEnd;
    
    
    
    
    
    
    
        ///////////////////////////////////////////  CONSTRUCTORS  ///////////
    
    public WorkHoursDialog(Routine routine){
        this.setTitle("Routines: Define Work Schedule");
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WorkHoursDialogGUI.fxml"));
            loader.setController(this);
            this.setDialogPane(loader.load());
        }
        catch(Exception e){
            //just move on then
        }
        Image icon = new Image(getClass().getResourceAsStream("Images/RoutinesIcon.png"));
        Stage stage = (Stage)this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);
        setUpOptions();
        cmbWeekStart.setValue(routine.getWorkHours().getWeekStart());
        cmbWeekEnd.setValue(routine.getWorkHours().getWeekEnd());
        cmbDayStart.setValue(routine.getWorkHours().getDayStart());
        cmbLunchBreak.setValue(routine.getWorkHours().getLunchBreak());
        cmbDayEnd.setValue(routine.getWorkHours().getDayEnd());
        lstHolidays.getItems().clear();
        lstHolidays.getItems().addAll(routine.getWorkHours().getHolidays());
        ButtonType btnConfirm = new ButtonType("Set Schedule", ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(btnConfirm, ButtonType.CANCEL);
        Optional<ButtonType> clicked = this.showAndWait();
        if(clicked.get() == btnConfirm){
            //should be finished when button is clicked
        }
        else{
            //should be finished at this point
        }
    }//end default constructor
    
    
    
    
    
    
        ////////////////////////////////////////////  DIALOG METHODS  ////////
    
    public void setUpOptions(){
        cmbWeekStart.getItems().addAll(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
        cmbWeekEnd.getItems().addAll(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
        for(LocalTime t = LocalTime.of(0, 0, 0); t.compareTo(LocalTime.of(23, 0, 0)) < 0; t = t.plusHours(1)){
            cmbDayStart.getItems().add(t);
            cmbLunchBreak.getItems().add(t);
            cmbDayEnd.getItems().add(t);
        }
        cmbDayStart.getItems().add(LocalTime.of(23, 0, 0));
        cmbLunchBreak.getItems().add(LocalTime.of(23, 0, 0));
        cmbDayEnd.getItems().add(LocalTime.of(23, 0, 0));
    }//end setUpOptions()
    
    
    
    
    public void addHoliday(){
        lstHolidays.getItems().add(dtHolidays.getValue());
        dtHolidays.getEditor().clear();
    }//end addHoliday()
    
    
    
    
    public void removeHoliday(){
        int day = lstHolidays.getSelectionModel().getSelectedIndex();
        if(day >= 0){
            lstHolidays.getItems().remove(day);
        }
    }//end removeHoliday()
    
    
    
    public WorkHours getWorkHours(){
        WorkHours schedule = new WorkHours();
        schedule.setWeekStart((DayOfWeek)cmbWeekStart.getValue());
        schedule.setWeekEnd((DayOfWeek)cmbWeekEnd.getValue());
        schedule.setDayStart((LocalTime)cmbDayStart.getValue());
        schedule.setLunchBreak((LocalTime)cmbLunchBreak.getValue());
        schedule.setDayEnd((LocalTime)cmbDayEnd.getValue());
        for(int i = 0; i < lstHolidays.getItems().size(); i++){
            schedule.getHolidays().add((LocalDate)lstHolidays.getItems().get(i));
        }
        schedule.setWorkingLists();
        return schedule;
    }//end getWorkSchedule()
    
    
    
    
    
    
    
    
        //////////////////////////////////////////////  JAVA OBJECTS  ///////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this but need it for the interface
    }//end initialize()
    
}//end WorkHoursDialog
