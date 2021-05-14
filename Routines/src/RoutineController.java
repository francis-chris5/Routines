
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;



public class RoutineController implements Initializable{

    @FXML
    Button btnAddTask;
    @FXML
    Button btnClearTask;
    @FXML
    TextField txtTaskName;
    @FXML
    ListView lstTasks;
    @FXML
    Button btnUp;
    @FXML
    Button btnDown;
    
    
    private Routine routine = new Routine();
    

    
    public void showTasks(){
        lstTasks.getItems().clear();
        for(int i=0; i < routine.dailyTasks.size(); i++){
            lstTasks.getItems().add(routine.dailyTasks.get(i));
        }
    }//end showTasks()
    
    
    public void taskDetails(){
        if(lstTasks.getItems().size() > 0){
            String selection = lstTasks.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.dailyTasks.size(); i++){
                if(routine.dailyTasks.get(i).getName().equals(selection)){
                    txtTaskName.setText(routine.dailyTasks.get(i).toString());
                }
            }
        }
    }//end taskDetails()
    
    
    public void newTask(){
        Task dt = new Task(txtTaskName.getText());
        txtTaskName.clear();
        routine.dailyTasks.add(dt);
        showTasks();
        routine.setSaved(false);
    }//end newTask()
    
    
    
    public void clearTaskDetails(){
        txtTaskName.clear();
    }//end clearTaskDetails()
    
    
    public void moveTaskUp(){
        try{
            int target = 0;
            String selection = lstTasks.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.dailyTasks.size(); i++){
                if(routine.dailyTasks.get(i).getName().equals(selection)){
                    target = i;
                }
            }
            if(target > 0){
                Collections.swap(routine.dailyTasks, target, target-1);
            }
        }
        catch(Exception e){
            //just move on
        }
        showTasks();
        routine.setSaved(false);
    }//end moveTaskUp()
    
    
    
    
    public void moveTaskDown(){
        try{
            int target = 0;
            String selection = lstTasks.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.dailyTasks.size(); i++){
                if(routine.dailyTasks.get(i).getName().equals(selection)){
                    target = i;
                }
            }
            if(target < routine.dailyTasks.size()-1){
                Collections.swap(routine.dailyTasks, target, target+1);
            }
        }
        catch(Exception e){
            //just move on
        }
        showTasks();
        routine.setSaved(false);
    }//end moveTaskDown()
    
    
    
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
            txtTaskName.clear();
        }
    }//end closeRoutine();
    
    
    
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
            e.printStackTrace();
            about = "error reading about file";
        }
        Alert aboutDialog = new Alert(AlertType.INFORMATION);
        aboutDialog.setTitle("Routines");
        aboutDialog.setHeaderText("About Routines");
        aboutDialog.setContentText(about);
        aboutDialog.showAndWait();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this, just need it here
    }//end initialize()
    
}//end Control
