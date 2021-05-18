package Routines;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import javafx.stage.FileChooser;


public class Routine implements Serializable{
    
        ///////////////////////////////////////////  DATAFIELDS  //////////////
		
    private String filename = null;
    private String filepath = null;
    private String routineName;
    private LocalDate routineStartDate;
    private double routineBudget;
    private String routineNotes;
    private TimeBasis defaultTimescale = TimeBasis.DAYS;
    public LinkedList<Task> routineTasks = new LinkedList();
    public LinkedList<Resource> availableResources = new LinkedList();
    private boolean saved = true;
    
    
    
    
    
    
    
    
        ////////////////////////////////////////  CONSTRUCTORS  //////////////
    
    public Routine(){
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

    public LocalDate getRoutineStartDate() {
        return routineStartDate;
    }

    public void setRoutineStartDate(LocalDate routineStartDate) {
        this.routineStartDate = routineStartDate;
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
            System.out.println(this.filename + " @ " + this.filepath);
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
            LinkedList<Task> fail = new LinkedList<Task>();
            fail.add(new Task("saved tasks failed to load"));
            e.printStackTrace();
            return new Routine();
        }
    }//end openRoutine()
    
    
    
    
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
        return this.getRoutineName() + " is scheduled to start on " + this.getRoutineStartDate() + " with tasks measured in " + this.getDefaultTimescale() + " with a budget of " + this.getRoutineBudget() + ", and includes the following...";
    }//end toString()

}//end Routine
