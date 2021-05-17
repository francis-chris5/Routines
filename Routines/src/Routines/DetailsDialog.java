
package Routines;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DetailsDialog extends Dialog implements Initializable {

    
        ///////////////////////////////////////////////  DATA FIELDS  ////////
    
    @FXML
    TextField txtRoutineName;
    @FXML
    DatePicker dtRoutineStartDate;
    @FXML
    TextArea txtRoutineNotes;
    @FXML
    RadioButton rdbMinutes;
    @FXML
    RadioButton rdbHours;
    @FXML
    RadioButton rdbDays;
    @FXML
    RadioButton rdbWeeks;
    @FXML
    RadioButton rdbMonths;
    @FXML
    RadioButton rdbYears;
    
    
    private Routine routine;
    
    
    
    
    
    
    
    
        //////////////////////////////////////////////  CONSTRUCTORS  /////////
    
    public DetailsDialog(){
    }//end default constructor
    
    
    
    
    public DetailsDialog(String purpose){
        this.setTitle("Routines: Routine Details");
        if(purpose.equals("new")){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsDialogGUI.fxml"));
                loader.setController(this);
                this.setDialogPane(loader.load());
            }
            catch(Exception e){
                //just move on then
            }
            ButtonType btnConfirm = new ButtonType("Create Routine", ButtonData.OK_DONE);
            this.getDialogPane().getButtonTypes().addAll(btnConfirm, ButtonType.CANCEL);
            Optional<ButtonType> clicked = this.showAndWait();
            if(clicked.get() == btnConfirm){
                routine = new Routine();
            }
            else{
                this.routine = null;
            }
        }
    }//end string-arg constructor
    
    
    
    
    public DetailsDialog(Routine routine){
        this.setTitle("Routines: Routine Details");
        this.routine = routine;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsDialogGUI.fxml"));
            loader.setController(this);
            this.setDialogPane(loader.load());
        }
        catch(Exception e){
            //just move on then
        }
        ButtonType btnConfirm = new ButtonType("Update Routine", ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(btnConfirm, ButtonType.CANCEL);
        txtRoutineName.setText(this.routine.getRoutineName());
        dtRoutineStartDate.setValue(this.routine.getRoutineStartDate());
        txtRoutineNotes.setText(this.routine.getRoutineNotes());
        setDefaultTimeRadioButton(this.routine.getDefaultTimescale());
        Optional<ButtonType> clicked = this.showAndWait();
        if(clicked.get() == btnConfirm){
            //should be finished at this point
        }
        else{
            //should be finished when button is clicked
        }
    }//end routine-arg consturctor
    
    
    
    
    
    
    
    
        ////////////////////////////////////////  GETTERS AND SETTERS  ///////
    
    public Routine getRoutine(){
        return this.routine;
    }//end getRoutine()
    
    
    
    
    
    
    
    
        //////////////////////////////////////////////  ROUTINE METHODS  /////
    
    public TimeBasis getDefaultTimeBasis(){
        if(rdbMinutes.isSelected()){
            return TimeBasis.MINUTES;
        }
        else if(rdbHours.isSelected()){
            return TimeBasis.HOURS;
        }
        else if(rdbDays.isSelected()){
            return TimeBasis.DAYS;
        }
        else if(rdbWeeks.isSelected()){
            return TimeBasis.WEEKS;
        }
        else if(rdbMonths.isSelected()){
            return TimeBasis.MONTHS;
        }
        else if(rdbYears.isSelected()){
            return TimeBasis.YEARS;
        }
        else{
            return TimeBasis.DAYS;
        }
    }//end getDefaultTimeBasis()
    
    
    
    
    public void setDefaultTimeRadioButton(TimeBasis tb){
        if(tb != null){
            switch(tb){
                case MINUTES:
                    rdbMinutes.setSelected(true);
                    break;
                case HOURS:
                    rdbHours.setSelected(true);
                    break;
                case DAYS:
                    rdbDays.setSelected(true);
                    break;
                case WEEKS:
                    rdbWeeks.setSelected(true);
                    break;
                case MONTHS:
                    rdbMonths.setSelected(true);
                    break;
                case YEARS:
                    rdbYears.setSelected(true);
                    break;
                default:
                    rdbDays.setSelected(true);   
            }
        }
        else{
            rdbDays.setSelected(true);
        }
    }//end setDefaultTimeRadioButton()
    
    
    
    
    public Routine editRoutine(){
        this.routine.setRoutineName(txtRoutineName.getText());
        this.routine.setRoutineStartDate(dtRoutineStartDate.getValue());
        this.routine.setRoutineNotes(txtRoutineNotes.getText());
        this.routine.setDefaultTimescale(this.getDefaultTimeBasis());
        return this.routine;
    }//end editRoutine()
    
    
    
    
    
    
    
    
        ///////////////////////////////////////////////  JAVA OBJECTS  ////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this but the interface requires it
    }//end Initialize()
    
}//end DetailsDialog
