package Routines;


import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;



public class RoutineController implements Initializable{
	
	
		///////////////////////////////////////////   FXML COMPONENTS   ///////////

    @FXML
    ListView lstTasks;
    @FXML
    ListView lstResources;
    @FXML
    ComboBox cmbAddResource;

    
	
	
	
	
	//////////////////////////////////////////////  DATAFIELDS  ///////////
    
    private Routine routine = new Routine();
    private int currentTask = 0;
    

    
	
	/////////////////////////////////////////////  TASKS  /////////////////
		
    public void showTasks(){
        lstTasks.getItems().clear();
        for(int i=0; i < routine.dailyTasks.size(); i++){
            lstTasks.getItems().add(routine.dailyTasks.get(i));
        }
    }//end showTasks()
    
    

    
    
    public void newTask(){
        Task dt = new TaskDialog("Add Task").getTask();
        if(dt != null){
            currentTask = routine.dailyTasks.size();
            routine.dailyTasks.add(dt);
            showTasks();
            routine.setSaved(false);
        }
    }//end newTask()
    
    

    
    public void moveTaskUp(){
        try{
            String selection = lstTasks.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.dailyTasks.size(); i++){
                if(routine.dailyTasks.get(i).getName().equals(selection)){
                    currentTask = i;
                }
            }
            if(currentTask > 0){
                Collections.swap(routine.dailyTasks, currentTask, currentTask-1);
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
            for(int i=0; i < routine.dailyTasks.size(); i++){
                if(routine.dailyTasks.get(i).getName().equals(selection)){
                    currentTask = i;
                }
            }
            if(currentTask < routine.dailyTasks.size()-1){
                Collections.swap(routine.dailyTasks, currentTask, currentTask+1);
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
            for(int i=0; i < routine.dailyTasks.size(); i++){
                if(routine.dailyTasks.get(i).getName().equals(selection)){
                    currentTask = i;
                }
            }
            if(currentTask >= 0){
                routine.dailyTasks.remove(currentTask);
            }
        }
        catch(Exception e){
            //just move on
        }
        showTasks();
        currentTask = -1;
        if(routine.dailyTasks.isEmpty()){
            routine.dailyTasks.add(new Task("Begin"));
        }
        routine.setSaved(false);
    }//end deleteTask()
    
    
    
        /////////////////////////////////////////////  RESOURCES  /////////////
    
    public void showResources(){
        lstResources.getItems().clear();
        for(int i=0; i < routine.dailyTasks.get(currentTask).assignedResources.size(); i++){
            lstResources.getItems().add(routine.dailyTasks.get(currentTask).assignedResources.get(i));
        }
        //cmbAddResource.setPromptText("Assign Resources");
    }//end showResources()
    
    
    public void setResourceChoices(){
        cmbAddResource.getItems().clear();
        for(int i = 0; i < routine.resources.size(); i++){
            cmbAddResource.getItems().add(routine.resources.get(i).getName());
        }
    }//end setResourceChoices()
    
    public void chooseResources(){
        int i = cmbAddResource.getSelectionModel().getSelectedIndex();
        routine.dailyTasks.get(currentTask).assignedResources.add(routine.resources.get(i));
        showResources();
        //clear combobox back to prompt text???
    }//end chooseResources()
    
    public void newResource(){
        Resource resource = new Resource();
        Dialog dlgResource = new Dialog();
        dlgResource.setTitle("Routines");
        dlgResource.setHeaderText("Input new Resource data");
        
        ButtonType addResource = new ButtonType("Add", ButtonData.OK_DONE);
        dlgResource.getDialogPane().getButtonTypes().addAll(addResource, ButtonType.CANCEL);
        
        VBox vbxNewResource = new VBox();
        TextField txtResourceName = new TextField();
        txtResourceName.setPromptText("Resource name");
        vbxNewResource.getChildren().addAll(txtResourceName);
        
        dlgResource.getDialogPane().setContent(vbxNewResource);
        
        Optional<ButtonType> result = dlgResource.showAndWait();
        if(result.get() == addResource && txtResourceName.getText().length() > 0){
            String name = txtResourceName.getText();
            routine.resources.add(new Resource(name));
        }
        setResourceChoices();
    }//end addResource()
    
    
    
	
        //////////////////////////////////////////////  ROUTINES  /////////////
		
    public void newRoutine(){
        closeRoutine();
        routine = new Routine();
    }//end newRoutine()
    
    
    
    public void saveRoutine(){
        if(routine.getTitle() == null){
            routine.saveAsRoutine();
        }
        else{
            routine.saveRoutine();
        }
    }//end saveRoutine()
    
    
    public void openRoutine(){
        closeRoutine();
        Routine another = new Routine();
        routine = another.openRoutine();
        showTasks();
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
                if(routine.getTitle() == null){
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
            lstTasks.getItems().clear();
        }
    }//end closeRoutine();
    
    
    
	
	//////////////////////////////////////////////  APPLICATION  /////////
		
    public void getAbout(){
        String about = new String();
        try{
            File file = new File("src/about.txt");
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this, just need it here for interfacing requirements
    }//end initialize()
    
}//end Control
