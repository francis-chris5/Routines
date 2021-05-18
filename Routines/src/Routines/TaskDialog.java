package Routines;


import java.net.URL;
import java.time.LocalTime;
import java.util.HashSet;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;


public class TaskDialog extends Dialog implements Initializable {

        /////////////////////////////////////////////  DATAFIELDS  ///////////
    @FXML
    TextField txtName;
    @FXML
    TextField txtDuration;
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
    ComboBox cmbPredecessor;
    @FXML
    TextField txtStartTime;
    @FXML
    DatePicker dtStartDate;
    @FXML
    DatePicker dtEndDate;
    @FXML
    ComboBox cmbAssignedResources;
    @FXML
    ListView lstAssignedResources;
    @FXML
    Slider sldComplexity;
    
    
    private Task task;
    private Routine routine;
    
    
        ////////////////////////////////////////////  CONSTRUCTORS  /////////
    
    public TaskDialog(){
    }//end default constructor
    
    
    
    
    public TaskDialog(String purpose, Routine routine){
        this.task = new Task("");
        this.routine = routine;
        this.setTitle("Routines: Task");
        if(purpose.equals("add")){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskDialogGUI.fxml"));
                loader.setController(this);
                this.setDialogPane(loader.load());
            }
            catch(Exception e){
                //just move on then
            }
            ButtonType btnConfirm = new ButtonType("Add Task", ButtonData.OK_DONE);
            this.getDialogPane().getButtonTypes().addAll(btnConfirm, ButtonType.CANCEL);
            setTimeBasisRadioButton(routine.getDefaultTimescale());
            setPredecessorChoices();
            setResourceChoices();
            Optional<ButtonType> clicked = this.showAndWait();
            if(clicked.get() == btnConfirm){
                //should be finished by the time the button is clicked
            }
            else{
                this.task = null;
            }
        }
    }//end string, routine-arg constructor
    
    
    
    
    public TaskDialog(Task task, Routine routine){
        this.task = task;
        this.routine = routine;
        this.setTitle("Routines: Task");
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskDialogGUI.fxml"));
            loader.setController(this);
            this.setDialogPane(loader.load());
        }
        catch(Exception e){
            //just move on then
        }
        ButtonType btnConfirm = new ButtonType("Update Task", ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(btnConfirm, ButtonType.CANCEL);
        txtName.setText(this.task.getName());
        txtDuration.setText("" + this.task.getDuration());
        this.setTimeBasisRadioButton(this.task.getUnits());
        this.setPredecessorChoices();
        cmbPredecessor.setValue(this.task.getPredecessor());
        txtStartTime.setText(this.task.getStartTime()==null?"":this.task.getStartTime().toString());
        dtStartDate.setValue(this.task.getStartDate());
        dtEndDate.setValue(this.task.getEndDate());
        setResourceChoices();
        lstAssignedResources.getItems().clear();
        lstAssignedResources.getItems().addAll(this.task.getAssignedResources());
        sldComplexity.setValue(this.task.getComplexity());
        Optional<ButtonType> clicked = this.showAndWait();
        if(clicked.get() == btnConfirm){
            //should be finished by the time the button is clicked
        }
        else{
            this.task = null;
        }
    }//end task,routine-arg constructor
    
    
    
    
    
    
    
    
        ///////////////////////////////////////////  TASK METHODS  //////////
    
    public TimeBasis getTimeBasis(){
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
    }//end getTimeBasis()
    
    
    
    
    public void setTimeBasisRadioButton(TimeBasis tb){
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
    }//end setTimeBasisRadioButton()
    
    
    
    
    
    public void setResourceChoices(){
        cmbAssignedResources.getItems().clear();
        for(int i = 0; i < routine.availableResources.size(); i++){
            cmbAssignedResources.getItems().add(routine.availableResources.get(i).getName());
        }
    }//end setResourceChoices()
    
    
    
    
    public void setPredecessorChoices(){
        cmbPredecessor.getItems().clear();
        for(int i=0; i < routine.routineTasks.size(); i++){
            cmbPredecessor.getItems().add(routine.routineTasks.get(i));
        }
    }//end setPredecessorChoices()
    
    
    
    
    public void addResource(){
        int i = cmbAssignedResources.getSelectionModel().getSelectedIndex();
        lstAssignedResources.getItems().add(routine.availableResources.get(i));
        routine.availableResources.get(i).assignedTo.add(this.task);
    }//end addResource()
    
    
    
    
    public void removeResource(){
        int res = lstAssignedResources.getSelectionModel().getSelectedIndex();
        if(res >= 0){
            lstAssignedResources.getItems().remove(res);
        }
    }//end removeResource()
    
    
    
    public Task addTask(){
        if(this.task != null){
            this.task.setName(txtName.getText());
            try{
                this.task.setDuration(Long.parseLong(txtDuration.getText()));
            }
            catch(NumberFormatException e){
                this.task.setDuration(1L);
            }
            this.task.setUnits(this.getTimeBasis());
            this.task.setPredecessor((Task)cmbPredecessor.getValue());
            try{
                int[] time = new int[3];
                String[] stringTime = txtStartTime.getText().split(":");
                for(int i=0; i < stringTime.length; i++){
                    time[i] = Integer.parseInt(stringTime[i]);
                }
                this.task.setStartTime(LocalTime.of(time[0], time[1], time[2]));
            }
            catch(NumberFormatException e){
                //just move on then
            }
            this.task.setStartDate(dtStartDate.getValue());
            this.task.setEndDate(dtEndDate.getValue());
            this.task.assignedResources.clear();
            this.task.assignedResources.addAll(lstAssignedResources.getItems());
            this.task.setComplexity(sldComplexity.getValue());
        }
        return this.task;
    }//end addTask()
    
    
    
    
    
    public Task editTask(){
        if(this.task != null){
            this.task.setName(txtName.getText());
            try{
                this.task.setDuration(Long.parseLong(txtDuration.getText()));
            }
            catch(NumberFormatException e){
                this.task.setDuration(1L);
            }
            this.task.setUnits(this.getTimeBasis());
            this.task.setPredecessor((Task)cmbPredecessor.getValue());
            try{
                int[] time = new int[3];
                String[] stringTime = txtStartTime.getText().split(":");
                for(int i=0; i < stringTime.length; i++){
                    time[i] = Integer.parseInt(stringTime[i]);
                }
                this.task.setStartTime(LocalTime.of(time[0], time[1], time[2]));
            }
            catch(NumberFormatException e){
                //just move on then
            }
            this.task.setStartDate(dtStartDate.getValue());
            this.task.setEndDate(dtEndDate.getValue());
            this.task.assignedResources.clear();
            this.task.assignedResources.addAll(lstAssignedResources.getItems());
            this.task.setComplexity(sldComplexity.getValue());
        }
        return this.task;
    }//end editTask()
    
    
    
    
    
    
    
    
        ///////////////////////////////////////////  JAVA OBJECTS  ///////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this but the interface requires it
    }//end initialize()
    
}//end TaskDialog()
