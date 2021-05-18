package Routines;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;



public class RoutineController implements Initializable{
	
	
        ///////////////////////////////////////////   FXML COMPONENTS   ///////////
    
    @FXML
    Button btnRoutineDetails;
    @FXML
    ListView lstTasks;
    @FXML
    ListView lstResources;
    
    
    
    
    
    
    
    
        //////////////////////////////////////////////  DATAFIELDS  ///////////
    
    private Routine routine = new Routine();
    private int currentTask = 0;
    private int currentResource = 0;
    
    
    
    
    
    
    
    
	/////////////////////////////////////////////  TASKS  /////////////////
		
    public void showTasks(){
        lstTasks.getItems().clear();
        for(int i=0; i < routine.routineTasks.size(); i++){
            lstTasks.getItems().add(routine.routineTasks.get(i));
        }
    }//end showTasks()
    
    
    
    
    public void newTask(){
        Task dt = new TaskDialog("add", this.routine).addTask();
        if(dt != null){
            currentTask = routine.routineTasks.size();
            routine.routineTasks.add(dt);
            showTasks();
            showResources();
            routine.setSaved(false);
        }
    }//end newTask()
    
    
    
    
    public void editTask(){
        try{
            String selection = lstTasks.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.routineTasks.size(); i++){
                if(routine.routineTasks.get(i).toString().equals(selection)){
                    Task task = new TaskDialog(routine.routineTasks.get(i), routine).editTask();
                    if(task != null){
                        routine.routineTasks.set(i, task);
                        showTasks();
                        showResources();
                        routine.setSaved(false);
                    }
                }
            }
        }
        catch(Exception e){
            //just move on then
        }
    }//end editTask()
    
    
    
    
    
    public void moveTaskUp(){
        try{
            String selection = lstTasks.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.routineTasks.size(); i++){
                if(routine.routineTasks.get(i).toString().equals(selection)){
                    currentTask = i;
                }
            }
            if(currentTask > 0){
                Collections.swap(routine.routineTasks, currentTask, currentTask-1);
            }
        }
        catch(Exception e){
            //just move on
        }
        showTasks();
        currentTask -= 1;
        routine.setSaved(false);
    }//end moveTaskUp()
    
    
    
    
    public void moveTaskDown(){
        try{
            String selection = lstTasks.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.routineTasks.size(); i++){
                if(routine.routineTasks.get(i).toString().equals(selection)){
                    currentTask = i;
                }
            }
            if(currentTask < routine.routineTasks.size()-1){
                Collections.swap(routine.routineTasks, currentTask, currentTask+1);
            }
        }
        catch(Exception e){
            //just move on
        }
        showTasks();
        currentTask += 1; 
        routine.setSaved(false);
    }//end moveTaskDown()
    
    
    
    
    public void deleteTask(){
        try{
            currentTask = -1;
            String selection = lstTasks.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.routineTasks.size(); i++){
                if(routine.routineTasks.get(i).toString().equals(selection)){
                    currentTask = i;
                }
            }
            if(currentTask >= 0){
                routine.routineTasks.remove(currentTask);
            }
        }
        catch(Exception e){
            //just move on
        }
        showTasks();
        currentTask = -1;
        if(routine.routineTasks.isEmpty()){
            routine.routineTasks.add(new Task("Begin"));
        }
        showTasks();
        showResources();
        routine.setSaved(false);
    }//end deleteTask()
    
    
    
    
    
    
    
    
        /////////////////////////////////////////////  RESOURCES  /////////////
    
    public void showResources(){
        lstResources.getItems().clear();
        for(int i=0; i < routine.availableResources.size(); i++){
            lstResources.getItems().add(routine.availableResources.get(i));
        }
    }//end showResources()
    
    
    
    
    public void newResource(){
        Resource nr = new ResourceDialog("add").addResource();
        if(nr != null){
            routine.availableResources.add(nr);
            showResources();
            showTasks();
            routine.setSaved(false);
        }
    }//end addResource()
     
     
     
    
    public void editResource(){
        try{
            String selection = lstResources.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.availableResources.size(); i++){
                if(routine.availableResources.get(i).toString().equals(selection)){
                    Resource res = new ResourceDialog(routine.availableResources.get(i)).editResource();
                    if(res != null){
                        routine.availableResources.set(i, res);
                        showResources();
                        showTasks();
                        routine.setSaved(false);
                    }
                }
            }
        }
        catch(Exception e){
            //just move on then
        }
    }//end editResource()
    
    
    
    
    public void moveResourceUP(){
        try{
            String selection = lstResources.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.availableResources.size(); i++){
                if(routine.availableResources.get(i).toString().equals(selection)){
                    currentResource = i;
                }
            }
            if(currentResource > 0){
                Collections.swap(routine.availableResources, currentResource, currentResource-1);
            }
        }
        catch(Exception e){
            //just move on
        }
        showResources();
        currentResource -= 1;
        routine.setSaved(false);
    }//end moveTaskUp()
    
    
    
    
    public void moveResourceDown(){
        try{
            String selection = lstResources.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.availableResources.size(); i++){
                if(routine.availableResources.get(i).toString().equals(selection)){
                    currentResource = i;
                }
            }
            if(currentResource < routine.availableResources.size()-1){
                Collections.swap(routine.availableResources, currentResource, currentResource+1);
            }
        }
        catch(Exception e){
            //just move on
        }
        showResources();
        currentResource += 1; 
        routine.setSaved(false);
    }//end moveTaskDown()
    
    
    
    
    public void deleteResource(){
        try{
            currentResource = -1;
            String selection = lstResources.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.availableResources.size(); i++){
                if(routine.availableResources.get(i).toString().equals(selection)){
                    currentResource = i;
                }
            }
            if(currentResource >= 0){
                routine.availableResources.remove(currentResource);
            }
        }
        catch(Exception e){
            //just move on
        }
        showResources();
        currentResource = -1;
        if(routine.availableResources.isEmpty()){
            routine.availableResources.add(new Resource("Self"));
        }
        routine.setSaved(false);
    }//end deleteTask()
    
    
    
    
    
    
    
    
        //////////////////////////////////////////////  ROUTINES  /////////////
		
    public void newRoutine(){
        closeRoutine();
        routine = new Routine();
        editRoutineDetails();
    }//end newRoutine()
    
    
    
    
    public void editRoutineDetails(){
        this.routine = new DetailsDialog(this.routine).editRoutine();
        btnRoutineDetails.setText(this.routine.toString());
    }//end editRoutineDetails()
    
    
    
    
    public void saveRoutine(){
        if(routine.getFilename() == null){
            routine.saveAsRoutine();
        }
        else{
            routine.saveRoutine();
        }
    }//end saveRoutine()
    
    
    
    
    public void saveAsRoutine(){
        routine.saveAsRoutine();
    }
    
    
    
    
    public void openRoutine(){
        closeRoutine();
        Routine another = new Routine();
        routine = another.openRoutine();
        showTasks();
        showResources();
        routine.setSaved(true);
    }//end openRoutine()
    
    
    
    
    public void closeRoutine(){
        boolean close = true;
        if(!routine.isSaved()){
            Alert wantSave = new Alert(AlertType.CONFIRMATION);
            wantSave.setTitle("Routines");
            wantSave.setHeaderText("Current routine has not been saved");
            wantSave.setContentText("Do you want to save the changes to this routine before closing?");
            
            ButtonType save = new ButtonType("Save");
            ButtonType noSave = new ButtonType("Close Without Saving");
            ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            wantSave.getButtonTypes().setAll(save, noSave, cancel);
            Optional<ButtonType> result = wantSave.showAndWait();
            if(result.get() == save){
                if(routine.getFilename() == null){
                    routine.saveAsRoutine();
                }
                else{
                    close = routine.saveRoutine();
                }
                if(!close){
                    Alert fail = new Alert(AlertType.ERROR);
                    fail.setTitle("Routines");
                    fail.setHeaderText("Unable to save file");
                    fail.setContentText("Please try again to save the routine.");
                }
            }
            else if(result.get() == noSave){
                routine.setSaved(true);
                
            }
            else{
                close = false;
            }
        }
        if(close){
            routine = new Routine();
            btnRoutineDetails.setText("Routine Details");
            lstTasks.getItems().clear();
            lstResources.getItems().clear();
        }
    }//end closeRoutine();
    
    
    
    
    
    
    
    
        //////////////////////////////////////////////  APPLICATION  /////////
    
    public void getHelp(){
        String help = new String();
        try{
            File file = new File("src/Routines/References/UserManual.txt");
            Scanner readFile = new Scanner(file);
            while(readFile.hasNextLine()){
                help += readFile.nextLine();
                help += "\n";
            }
            readFile.close();
        }
        catch(Exception e){
            //whoops, no help file
            help = "error reading help file";
        }
        Alert helpDialog = new Alert(AlertType.INFORMATION);
        helpDialog.setTitle("Routines");
        helpDialog.setHeaderText("User Manual");
        TextArea txtHelp = new TextArea();
        txtHelp.setText(help);
        txtHelp.setWrapText(true);
        txtHelp.setEditable(false);
        VBox vbxHelp = new VBox(txtHelp);
        helpDialog.getDialogPane().setContent(vbxHelp);
        helpDialog.showAndWait();
    }//end getHelp()
    
    
    
    
    public void getAbout(){
        String about = new String();
        try{
            File file = new File("src/Routines/References/about.txt");
            Scanner readFile = new Scanner(file);
            while(readFile.hasNextLine()){
                about += readFile.nextLine();
                about += "\n";
            }
            readFile.close();
        }
        catch(Exception e){
            //whoops, no about file
            about = "error reading about file";
        }
        Alert aboutDialog = new Alert(AlertType.INFORMATION);
        aboutDialog.setTitle("Routines");
        aboutDialog.setHeaderText("About Routines");
        aboutDialog.setContentText(about);
        aboutDialog.showAndWait();
    }//end getAbout()
    
    
    
    
    
    
    
        ////////////////////////////////////////////  JAVA OBJECECT  /////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this, just need it here for interfacing requirements
    }//end initialize()
    
}//end Control
