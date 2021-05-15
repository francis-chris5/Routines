package Routines;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Routine implements Serializable{
    
		///////////////////////////////////////////////////  DATAFIELDS  //////////////
		
    private String title = null;
    private String filepath = null;
    public LinkedList<Task> dailyTasks = new LinkedList();
    public LinkedList<Resource> resources = new LinkedList();
    private boolean saved = true;
	
	
	
    
		//////////////////////////////////////////////////  CONSTRUCTORS  ///////////////////////
    public Routine(){
        dailyTasks.add(new Task("Begin"));
        resources.add(new Resource("Self"));
    }//end default constructor()
    
	
	
	
	
		/////////////////////////////////////////////////  GETTERS AND SETTERS  /////////////////
		
    public String getTitle(){
        return this.title;
    }//end getTitle()
    
    public boolean isSaved(){
        return this.saved;
    }//end isSaved()
    
    public void setSaved(boolean saved){
        this.saved = saved;
    }//end setSaved()
    
    
	
		///////////////////////////////////////////////  ROUTINES FILE MENU OPERATIONS  ////////
     public boolean saveRoutine(){
        try{
            File file = new File(this.filepath);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.dailyTasks);
            oos.close();
            this.saved = true;
            return this.saved;
        }
        catch(Exception e){
            return false;
        }
    }//end saveRoutine()
    
    public boolean saveAsRoutine(){
        try{
            FileChooser choose = new FileChooser();
            FileChooser.ExtensionFilter routineFilter = new FileChooser.ExtensionFilter("Routine, .rtne", "*.rtne");
            choose.getExtensionFilters().add(routineFilter);
            File file = choose.showSaveDialog(null);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.dailyTasks);
            oos.close();
            this.filepath = file.getPath();
            this.title = file.getName();
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
            this.dailyTasks = (LinkedList<Task>)ois.readObject();
            this.title = file.getName();
            this.filepath = file.getPath();
            this.saved = true;
            ois.close();
            return this;
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
    
}//end serializable()
