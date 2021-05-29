package Routines;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import javafx.stage.FileChooser;


public class Routine implements Serializable{
    
        ///////////////////////////////////////////  DATAFIELDS  //////////////
		
    private String filename = null;
    private String filepath = null;
    private String routineName;
    private LocalDateTime routineStartTime;
    private LocalDateTime routineEndTime;
    private WorkHours workHours = new WorkHours();
    private double routineBudget;
    private String routineNotes;
    private TimeBasis defaultTimescale = TimeBasis.DAYS;
    private LinkedList<Task> routineTasks = new LinkedList();
    private LinkedList<Resource> availableResources = new LinkedList();
    private boolean saved = true;
    
    
    
    
    
    
    
    
        ////////////////////////////////////////  CONSTRUCTORS  //////////////
    
    public Routine(){
        this.routineStartTime = LocalDateTime.now();
        this.findRoutineEndTime();
        this.workHours.setWorkingLists();
    }//end default constructor()
    
    
    
    
    
    
    
    
        ///////////////////////////////////////  GETTERS AND SETTERS  ///////
    public String getFilename(){
        return filename;
    }//end default constructor

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public LocalDateTime getRoutineStartTime() {
        return routineStartTime;
    }

    public LocalDateTime getRoutineEndTime() {
        return routineEndTime;
    }

    public void setRoutineEndTime(LocalDateTime routineEndTime) {
        this.routineEndTime = routineEndTime;
    }

    public WorkHours getWorkHours() {
        return workHours;
    }

    public void setWorkHours(WorkHours workHours) {
        this.workHours = workHours;
    }

    public void setRoutineStartTime(LocalDateTime routineStartTime) {
        this.routineStartTime = routineStartTime;
    }
    
    public double getRoutineBudget() {
        return routineBudget;
    }

    public void setRoutineBudget(double routineBudget) {
        this.routineBudget = routineBudget;
    }

    public String getRoutineNotes() {
        return routineNotes;
    }

    public void setRoutineNotes(String routineNotes) {
        this.routineNotes = routineNotes;
    }

    public TimeBasis getDefaultTimescale() {
        return defaultTimescale;
    }

    public void setDefaultTimescale(TimeBasis defaultTimescale) {
        this.defaultTimescale = defaultTimescale;
    }

    public LinkedList<Task> getRoutineTasks() {
        return routineTasks;
    }

    public void setRoutineTasks(LinkedList<Task> routineTasks) {
        this.routineTasks = routineTasks;
    }

    public LinkedList<Resource> getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(LinkedList<Resource> availableResources) {
        this.availableResources = availableResources;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
    
    
    
    
    
    
    
    
        /////////////////////////////  ROUTINES FILE MENU OPERATIONS  ////////
    
    public boolean saveRoutine() {
        try{
            File file = new File(this.filepath);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            this.saved = true;
            return this.saved;
        }
        catch(Exception e){
            return false;
        }
    } //end saveRoutine()
    
    
    
    
    public boolean saveAsRoutine(){
        try{
            FileChooser choose = new FileChooser();
            FileChooser.ExtensionFilter routineFilter = new FileChooser.ExtensionFilter("Routine, .rtne", "*.rtne");
            choose.getExtensionFilters().add(routineFilter);
            File file = choose.showSaveDialog(null);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            this.filepath = file.getPath();
            this.filename = file.getName();
            this.saved = true;
            return this.saved;
        }
        catch(Exception e){
            return false;
        }
    }//end saveAsRoutine()
    
    
    
    
    public Routine openRoutine(){
        try{
            FileChooser choose = new FileChooser();
            FileChooser.ExtensionFilter routineFilter = new FileChooser.ExtensionFilter("Routine, .rtne", "*.rtne");
            choose.getExtensionFilters().add(routineFilter);
            File file = choose.showOpenDialog(null);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Routine newRoutine = (Routine)ois.readObject();
            this.filename = file.getName();
            this.filepath = file.getPath();
            this.saved = true;
            ois.close();
            return newRoutine;
        }
        catch(Exception e){
            return null;
        }
    }//end openRoutine()
    
    
    
    
    public void findRoutineEndTime(){
        int duration = 0;
        int max = 0;
        for(int i=0; i < this.routineTasks.size(); i++){
            if(routineTasks.get(max).getEndTime().compareTo(routineTasks.get(i).getEndTime()) < 0){
                max = i;
            }
        }
        try{
            duration = (int)defaultTimescale.getChronoUnits().between(routineStartTime, routineTasks.get(max).getEndTime());
            this.routineEndTime = this.routineStartTime.plus(duration, defaultTimescale.getChronoUnits());
        }
        catch(Exception e){
            //probably no tasks yet
        }
    }//end findRoutineEndTime()
    
    
    
    
    public boolean exportRoutine(String format){
        //export in form compataible(?) with other software
            //xml
            //spreadsheet
            //csv
            //json
            //png or svg when gantt chart is ready
            //...
        return false;
    }//end exportRoutine()
    
    
    
    
    
    
    
        ///////////////////////////////////////////  JAVA OBJECTS  ///////////
    
    @Override
    public String toString() {
        return this.getRoutineName() + " is scheduled to start on " + this.getRoutineStartTime() + " with tasks measured in " + this.getDefaultTimescale() + " with a budget of " + this.getRoutineBudget() + ", and includes the following...";
    }//end toString()

}//end Routine
