
package Routines;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;


/**
 * The Java class to accompany a GUI constructed in FXML. Basically this take the general Routine data (name, descriptione, etc.) from a Routine object and sets it up to be displayed nicely on the screen for a user.
 * @author Chris Francis
 */
public class DetailsDialog extends Dialog implements Initializable {

    
        ///////////////////////////////////////////////  DATA FIELDS  ////////
    
    @FXML
    TextField txtRoutineName;
    @FXML
    DatePicker dtRoutineStartDate;
    @FXML
    TextField txtRoutineStartTime;
    @FXML
    HTMLEditor txtRoutineNotes;
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
    @FXML
    TextField txtRoutineBudget;
    @FXML
    Button btnLaunchScheduler;
    
    
    private Routine routine;
    
    
    
    
    
    
    
    
        //////////////////////////////////////////////  CONSTRUCTORS  /////////
    
    /**
     * the default constructor (I don't think I used this anywhere)
     */
    public DetailsDialog(){
    }//end default constructor
    
    
    
    
    /**
     * The one-string constructor is called from the new routine button in the file menu, originally the plan was to pass in the reason for opening it, but edit functionality was moved to the other one-arg constructor so only new files call this one (it assumes the dialog inputs are all empty)
     * @param purpose The reason for opening the dialog, the only valid entry at this point is "new"
     */
    public DetailsDialog(String purpose){
        routine = new Routine();
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
            ButtonType btnConfirm = new ButtonType("Create Routine", ButtonData.OTHER);
            this.getDialogPane().getButtonTypes().addAll(btnConfirm, ButtonType.CANCEL);
            Image icon = new Image(getClass().getResourceAsStream("Images/RoutinesIcon.png"));
            Stage stage = (Stage)this.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            Optional<ButtonType> clicked = this.showAndWait();
            if(clicked.get() == btnConfirm){
                //should be finished at this point
            }
            else{
                this.routine = null;
            }
        }
    }//end string-arg constructor
    
    
    
    
    /**
     * The one-routine constructor is the go to for most of the code. It loads in the fxml file and sets the inputs on the GUI to whatever is stored in the Routine object
     * @param routine The currently open Routine this dialog will be used for editing the data of
     */
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
        Image icon = new Image(getClass().getResourceAsStream("Images/RoutinesIcon.png"));
        Stage stage = (Stage)this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);
        ButtonType btnConfirm = new ButtonType("Update Routine", ButtonData.OTHER);
        this.getDialogPane().getButtonTypes().addAll(btnConfirm, ButtonType.CANCEL);
        txtRoutineName.setText(this.routine.getRoutineName());
        dtRoutineStartDate.setValue(this.routine.getRoutineStartTime().toLocalDate());
        txtRoutineStartTime.setText(routine.getRoutineStartTime().toLocalTime().toString());
        txtRoutineBudget.setText("" + this.routine.getRoutineBudget());
        txtRoutineNotes.setHtmlText(this.routine.getRoutineNotes());
        setDefaultTimeRadioButton(this.routine.getDefaultTimescale());
        //setStartFormat();
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
    
    
    /**
     * selects a default time measurement scale for Tasks in this Routine
     * @return <b>TimeBasis</b> based off the chosen radio button
     */
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
    
    
    
    
    /**
     * Uses the default timescale for the Routine to set the appropriate radio button when opening this dialog
     * @param tb The default timescale for this Routine
     */
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
    
    
    
    
    /**
     * switches input methods between start date or start time inputs based on default timescale
     */
    public void setStartFormat(){
        if(rdbMinutes.isSelected() || rdbHours.isSelected()){
            txtRoutineStartTime.setDisable(false);
        }
        else{
            txtRoutineStartTime.setDisable(true);
        }
    }
    
    
    
    
    /**
     * updates the routine data to match what the user entered in the dialog GUI
     * @return -should probably update this to a void because the routine being edited is passed by reference-
     */
    public Routine editRoutine(){
        this.routine.setRoutineName(txtRoutineName.getText());
             try{
                int[] time = new int[3];
                String[] stringTime = txtRoutineStartTime.getText().split(":");
                for(int i=0; i < stringTime.length; i++){
                    time[i] = Integer.parseInt(stringTime[i]);
                }
                this.routine.setRoutineStartTime(LocalDateTime.of(dtRoutineStartDate.getValue(), LocalTime.of(time[0], time[1], time[2])));
            }
            catch(NumberFormatException e){
                //just move on then
            }
        try{
            this.routine.setRoutineBudget(Double.parseDouble(txtRoutineBudget.getText()));
        }
        catch(NumberFormatException e0){
            try{
                this.routine.setRoutineBudget(Double.parseDouble(txtRoutineBudget.getText().substring(1)));
            }
            catch(NumberFormatException e1){
                this.routine.setRoutineBudget(-1.0);
            }  
        }
        this.routine.setRoutineNotes(txtRoutineNotes.getHtmlText());
        this.routine.setDefaultTimescale(this.getDefaultTimeBasis());
        return this.routine;
    }//end editRoutine()
    
    
    
    
    /**
     * launches the WorkHoursDialog and resets the Routine's working hours to the results from that dialog
     */
    public void defineWorkHours(){
        routine.setWorkHours(new WorkHoursDialog(routine).getWorkHours());
    }//end defineWorkScedule()
    
    
    
    
    
    
    
        ///////////////////////////////////////////////  JAVA OBJECTS  ////////
    
    /**
     * I rarely use this but the interfacing requirements for FXML require it
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this but the interface requires it
    }//end Initialize()
    
}//end DetailsDialog
