package Routines;


import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    RadioButton rdbByPredecessor;
    @FXML
    RadioButton rdbByStart;
    @FXML
    RadioButton rdbByEnd;
    @FXML
    ComboBox cmbPredecessor;
    @FXML
    TextField txtScheduleTime;
    @FXML
    DatePicker dtScheduleDate;
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
            setScheduler(TaskScheduler.BY_PREDECESSOR);
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
        this.setScheduler(this.task.getScheduler());
        cmbPredecessor.setValue(this.task.getPredecessor());
        txtScheduleTime.setText(this.task.getStartTime()==null?"":this.task.getStartTime().toString());
        dtScheduleDate.setValue(this.task.getStartTime().toLocalDate());
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
    
    
    
    
    public void setScheduler(TaskScheduler ts){
        switch(ts){
            case BY_PREDECESSOR:
                rdbByPredecessor.setSelected(true);
                cmbPredecessor.setDisable(false);
                txtScheduleTime.setDisable(true);
                dtScheduleDate.setDisable(true);
                break;
            case BY_START:
                if(rdbMinutes.isSelected() || rdbHours.isSelected()){
                    rdbByStart.setSelected(true);
                    cmbPredecessor.setDisable(true);
                    txtScheduleTime.setDisable(false);
                    dtScheduleDate.setDisable(true);
                }
                else{
                    rdbByStart.setSelected(true);
                    cmbPredecessor.setDisable(true);
                    txtScheduleTime.setDisable(true);
                    dtScheduleDate.setDisable(false);
                }
                break;
            case BY_END:
                if(rdbMinutes.isSelected() || rdbHours.isSelected()){
                    rdbByEnd.setSelected(true);
                    cmbPredecessor.setDisable(true);
                    txtScheduleTime.setDisable(false);
                    dtScheduleDate.setDisable(true);
                }
                else{
                    rdbByEnd.setSelected(true);
                    cmbPredecessor.setDisable(true);
                    txtScheduleTime.setDisable(true);
                    dtScheduleDate.setDisable(false);
                }
                break;
                
        }
    }//end setSchedule()
    
    
    
    
    public void schedulerChanged(){
        if(rdbByStart.isSelected()){
            setScheduler(TaskScheduler.BY_START);
        }
        else if(rdbByEnd.isSelected()){
            setScheduler(TaskScheduler.BY_END);
        }
        else{
            setScheduler(TaskScheduler.BY_PREDECESSOR);
        }
    }//end schedulerChanged()
    
    
    
    
    public TaskScheduler getScheduler(){
        if(rdbByPredecessor.isSelected()){
            return TaskScheduler.BY_PREDECESSOR;
        }
        else if(rdbByStart.isSelected()){
            return TaskScheduler.BY_START;
        }
        else if(rdbByEnd.isSelected()){
            return TaskScheduler.BY_END;
        }
        else{
            return TaskScheduler.BY_PREDECESSOR;
        }
    }//end getScheduler()
    
    
    
    
    
    public void setResourceChoices(){
        cmbAssignedResources.getItems().clear();
        for(int i = 0; i < routine.getAvailableResources().size(); i++){
            cmbAssignedResources.getItems().add(routine.getAvailableResources().get(i).getName());
        }
    }//end setResourceChoices()
    
    
    
    
    public void setPredecessorChoices(){
        cmbPredecessor.getItems().clear();
        for(int i=0; i < routine.getRoutineTasks().size(); i++){
            cmbPredecessor.getItems().add(routine.getRoutineTasks().get(i));
        }
    }//end setPredecessorChoices()
    
    
    
    
    public void addResource(){
        int i = cmbAssignedResources.getSelectionModel().getSelectedIndex();
        lstAssignedResources.getItems().add(routine.getAvailableResources().get(i));
        routine.getAvailableResources().get(i).getAssignedTo().add(this.task);
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
            this.task.setScheduler(this.getScheduler());
            switch(this.task.getScheduler()){
                case BY_PREDECESSOR:
                    this.task.setPredecessor((Task)cmbPredecessor.getValue());
                    if(this.task.getPredecessor() != null){
                        if(rdbMinutes.isSelected() || rdbHours.isSelected()){
                            this.task.setStartTime(this.task.getPredecessor().getEndTime().plus(1, this.task.getUnits().getChronoUnits()));
                            this.task.setEndTime(this.task.getStartTime().plus(this.task.getDuration(), this.task.getUnits().getChronoUnits()));
                        }
                        else{
                            this.task.setStartTime(this.task.getPredecessor().getEndTime().plus(1, this.task.getUnits().getChronoUnits()));
                            this.task.setEndTime(this.task.getStartTime().plus(this.task.getDuration(), this.task.getUnits().getChronoUnits()));
                        }
                    }
                    else{
                        this.task.setStartTime(routine.getRoutineStartTime());
                        this.task.setEndTime(this.task.getStartTime().plus(this.task.getDuration(), this.task.getUnits().getChronoUnits()));
                    }
                    break;
                case BY_START:
                    try{
                        int[] time = new int[3];
                        String[] stringTime = txtScheduleTime.getText().split(":");
                        for(int i=0; i < stringTime.length; i++){
                            time[i] = Integer.parseInt(stringTime[i]);
                        }
                        this.task.setStartTime(LocalDateTime.of(dtScheduleDate.getValue(), LocalTime.of(time[0], time[1], time[2])));
                        this.task.setEndTime(this.task.getStartTime().plus(this.task.getDuration(), this.task.getUnits().getChronoUnits()));
                    }
                    catch(NumberFormatException e){
                        //just move on then
                    }
                    break;
                case BY_END:
                    try{
                        int[] time = new int[3];
                        String[] stringTime = txtScheduleTime.getText().split(":");
                        for(int i=0; i < stringTime.length; i++){
                            time[i] = Integer.parseInt(stringTime[i]);
                        }
                        this.task.setEndTime(LocalDateTime.of(dtScheduleDate.getValue(), LocalTime.of(time[0], time[1], time[2])));
                        this.task.setStartTime(this.task.getEndTime().minus(this.task.getDuration(), this.task.getUnits().getChronoUnits()));
                    }
                    catch(NumberFormatException e){
                        //just move on then
                    }
                    break;
                default:
                    this.task.setPredecessor((Task)cmbPredecessor.getValue());
            }
            
            this.task.getAssignedResources().clear();
            this.task.getAssignedResources().addAll(lstAssignedResources.getItems());
            this.task.setComplexity(sldComplexity.getValue()==0?0.0001:sldComplexity.getValue());
        }
        adjustToWorkingTime();
        routine.findRoutineEndTime();
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
            this.task.setScheduler(this.getScheduler());
            switch(this.task.getScheduler()){
                case BY_PREDECESSOR:
                    this.task.setPredecessor((Task)cmbPredecessor.getValue());
                    if(this.task.getPredecessor() != null){
                        if(rdbMinutes.isSelected() || rdbHours.isSelected()){
                            this.task.setStartTime(this.task.getPredecessor().getEndTime().plus(1, this.task.getUnits().getChronoUnits()));
                            this.task.setEndTime(this.task.getStartTime().plus(this.task.getDuration(), this.task.getUnits().getChronoUnits()));
                        }
                        else{
                            this.task.setStartTime(this.task.getPredecessor().getEndTime().plus(1, this.task.getUnits().getChronoUnits()));
                            this.task.setEndTime(this.task.getStartTime().plus(this.task.getDuration(), this.task.getUnits().getChronoUnits()));
                        }
                    }
                    else{
                        this.task.setStartTime(routine.getRoutineStartTime());
                        this.task.setEndTime(this.task.getStartTime().plus(this.task.getDuration(), this.task.getUnits().getChronoUnits()));
                    }
                    break;
                case BY_START:
                    try{
                        int[] time = new int[3];
                        String[] stringTime = txtScheduleTime.getText().split(":");
                        for(int i=0; i < stringTime.length; i++){
                            time[i] = Integer.parseInt(stringTime[i]);
                        }
                        this.task.setStartTime(LocalDateTime.of(dtScheduleDate.getValue(), LocalTime.of(time[0], time[1], time[2])));
                        this.task.setEndTime(this.task.getStartTime().plus(this.task.getDuration(), this.task.getUnits().getChronoUnits()));
                    }
                    catch(NumberFormatException e){
                        //just move on then
                    }
                    break;
                case BY_END:
                    try{
                        int[] time = new int[3];
                        String[] stringTime = txtScheduleTime.getText().split(":");
                        for(int i=0; i < stringTime.length; i++){
                            time[i] = Integer.parseInt(stringTime[i]);
                        }
                        this.task.setEndTime(LocalDateTime.of(dtScheduleDate.getValue(), LocalTime.of(time[0], time[1], time[2])));
                        this.task.setStartTime(this.task.getEndTime().minus(this.task.getDuration(), this.task.getUnits().getChronoUnits()));
                    }
                    catch(NumberFormatException e){
                        //just move on then
                    }
                    break;
                default:
                    this.task.setPredecessor((Task)cmbPredecessor.getValue());
            }
            
            this.task.getAssignedResources().clear();
            this.task.getAssignedResources().addAll(lstAssignedResources.getItems());
            this.task.setComplexity(sldComplexity.getValue()==0?0.0001:sldComplexity.getValue());
        }
        adjustToWorkingTime();
        routine.findRoutineEndTime();
        return this.task;
    }//end editTask()
    
    
    
    
    public void adjustToWorkingTime(){
        try{
            if(routine.getDefaultTimescale() == TimeBasis.MINUTES || routine.getDefaultTimescale() == TimeBasis.HOURS){
                for(int h = task.getStartTime().getHour(); h%24 != task.getEndTime().getHour() ;h++){
                    if(!routine.getWorkHours().getWorkingHours().contains(LocalTime.of(h%24, 0, 0))){
                        task.setEndTime(task.getEndTime().plusHours(1));
                    }
                }
            }
            else{
                for(DayOfWeek d = task.getStartTime().getDayOfWeek(); !d.equals(task.getEndTime().getDayOfWeek()); d = d.plus(1)){
                    if(!routine.getWorkHours().getWorkingDays().contains(d)){
                        task.setEndTime(task.getEndTime().plusDays(1));
                    }
                }
            }
        }
        catch(NullPointerException e){
            //probably canceled out of dialog box
        }
    }//end adjustToWorkingTime()
    
    
    
    
    
    
    
    
        ///////////////////////////////////////////  JAVA OBJECTS  ///////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this but the interface requires it
    }//end initialize()
    
}//end TaskDialog()
