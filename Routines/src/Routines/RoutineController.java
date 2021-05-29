
package Routines;


import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class RoutineController implements Initializable{
	
	
        ///////////////////////////////////////////   FXML COMPONENTS   ///////////
    
    @FXML
    Button btnRoutineDetails;
    @FXML
    ListView lstTasks;
    @FXML
    ListView lstResources;
    
    @FXML
    Label lblLeftStatus;
    @FXML
    Label lblRightStatus;
    
    @FXML
    VBox chtGanttChart;
    @FXML
    VBox chtTimePie;
    @FXML
    VBox chtStaminaComplexityControl;
    @FXML
    VBox chtResourceBurnUp;
    @FXML
    VBox chtCostPie;
    @FXML
    VBox chtResourceUsageChart;
    @FXML
    VBox chtPertChart;
    
    
    
    
    
    
    
        //////////////////////////////////////////////  DATAFIELDS  ///////////
    
    private Routine routine = new Routine();
    private int currentTask = 0;
    private int currentResource = 0;
    
    
    
    
    
    
    
        //////////////////////////////////////////  VISUAL ANALYSIS  /////////
    
    public void updateGraphicalAnalysis(){
        chtTimePie.getChildren().clear();
        chtTimePie.getChildren().add(new TimePie(this.routine));
        chtResourceBurnUp.getChildren().clear();
        chtResourceBurnUp.getChildren().add(new ResourceBurnUp(this.routine));
        chtStaminaComplexityControl.getChildren().clear();
        chtStaminaComplexityControl.getChildren().add(new StaminaComplexityControl(this.routine));
        chtCostPie.getChildren().clear();
        chtCostPie.getChildren().add(new CostPie(this.routine));
        chtGanttChart.getChildren().clear();
        chtGanttChart.getChildren().add(new GanttChart(this.routine));
        chtResourceUsageChart.getChildren().clear();
        chtResourceUsageChart.getChildren().add(new ResourceUsageChart(this.routine));
        chtPertChart.getChildren().clear();
        chtPertChart.getChildren().add(new PERTChart(this.routine));
    }//end updateGraphicalAnalysis()
    
    
    
    
    
    
    
    
    
    
    
    
	/////////////////////////////////////////////  TASKS  /////////////////
		
    public void showTasks(){
        lstTasks.getItems().clear();
        for(int i=0; i < routine.getRoutineTasks().size(); i++){
            lstTasks.getItems().add(routine.getRoutineTasks().get(i));
        }
    }//end showTasks()
    
    
    
    
    public void newTask(){
        setLeftStatus("Status: creating new task...");
        Task dt = new TaskDialog("add", this.routine).addTask();
        if(dt != null){
            currentTask = routine.getRoutineTasks().size();
            routine.getRoutineTasks().add(dt);
            showTasks();
            showResources();
            updateGraphicalAnalysis();
            routine.setSaved(false);
        }
        setLeftStatus("Status:...");
    }//end newTask()
    
    
    
    
    public void editTask(){
        setLeftStatus("Status: editing task details...");
        try{
            String selection = lstTasks.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.getRoutineTasks().size(); i++){
                if(routine.getRoutineTasks().get(i).toString().equals(selection)){
                    Task task = new TaskDialog(routine.getRoutineTasks().get(i), routine).editTask();
                    if(task != null){
                        routine.getRoutineTasks().set(i, task);
                        showTasks();
                        showResources();
                        updateGraphicalAnalysis();
                        routine.setSaved(false);
                    }
                }
            }
        }
        catch(Exception e){
            //just move on then
        }
        setLeftStatus("Status:...");
    }//end editTask()
    
    
    
    
    
    public void moveTaskUp(){
        try{
            String selection = lstTasks.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.getRoutineTasks().size(); i++){
                if(routine.getRoutineTasks().get(i).toString().equals(selection)){
                    currentTask = i;
                }
            }
            if(currentTask > 0){
                Collections.swap(routine.getRoutineTasks(), currentTask, currentTask-1);
            }
        }
        catch(Exception e){
            //just move on
        }
        showTasks();
        updateGraphicalAnalysis();
        currentTask -= 1;
        routine.setSaved(false);
    }//end moveTaskUp()
    
    
    
    
    public void moveTaskDown(){
        try{
            String selection = lstTasks.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.getRoutineTasks().size(); i++){
                if(routine.getRoutineTasks().get(i).toString().equals(selection)){
                    currentTask = i;
                }
            }
            if(currentTask < routine.getRoutineTasks().size()-1){
                Collections.swap(routine.getRoutineTasks(), currentTask, currentTask+1);
            }
        }
        catch(Exception e){
            //just move on
        }
        showTasks();
        updateGraphicalAnalysis();
        currentTask += 1; 
        routine.setSaved(false);
    }//end moveTaskDown()
    
    
    
    
    public void deleteTask(){
        try{
            currentTask = -1;
            String selection = lstTasks.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.getRoutineTasks().size(); i++){
                if(routine.getRoutineTasks().get(i).toString().equals(selection)){
                    currentTask = i;
                }
            }
            if(currentTask >= 0){
                routine.getRoutineTasks().remove(currentTask);
            }
        }
        catch(Exception e){
            //just move on
        }
        showTasks();
        currentTask = -1;
        showTasks();
        showResources();
        updateGraphicalAnalysis();
        routine.setSaved(false);
    }//end deleteTask()
    
    
    
    
    
    
    
    
        /////////////////////////////////////////////  RESOURCES  /////////////
    
    public void showResources(){
        lstResources.getItems().clear();
        for(int i=0; i < routine.getAvailableResources().size(); i++){
            lstResources.getItems().add(routine.getAvailableResources().get(i));
        }
    }//end showResources()
    
    
    
    
    public void newResource(){
        setLeftStatus("Status: creating new resource...");
        Resource nr = new ResourceDialog("add").addResource();
        if(nr != null){
            routine.getAvailableResources().add(nr);
            showResources();
            showTasks();
            updateGraphicalAnalysis();
            routine.setSaved(false);
        }
        setLeftStatus("Status:...");
    }//end addResource()
     
     
     
    
    public void editResource(){
        setLeftStatus("Status: editing resource details...");
        try{
            String selection = lstResources.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.getAvailableResources().size(); i++){
                if(routine.getAvailableResources().get(i).toString().equals(selection)){
                    Resource res = new ResourceDialog(routine.getAvailableResources().get(i)).editResource();
                    if(res != null){
                        routine.getAvailableResources().set(i, res);
                        showResources();
                        showTasks();
                        updateGraphicalAnalysis();
                        routine.setSaved(false);
                    }
                }
            }
        }
        catch(Exception e){
            //just move on then
        }
        setLeftStatus("Status:...");
    }//end editResource()
    
    
    
    
    public void moveResourceUP(){
        try{
            String selection = lstResources.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.getAvailableResources().size(); i++){
                if(routine.getAvailableResources().get(i).toString().equals(selection)){
                    currentResource = i;
                }
            }
            if(currentResource > 0){
                Collections.swap(routine.getAvailableResources(), currentResource, currentResource-1);
            }
        }
        catch(Exception e){
            //just move on
        }
        showResources();
        updateGraphicalAnalysis();
        currentResource -= 1;
        routine.setSaved(false);
    }//end moveTaskUp()
    
    
    
    
    public void moveResourceDown(){
        try{
            String selection = lstResources.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.getAvailableResources().size(); i++){
                if(routine.getAvailableResources().get(i).toString().equals(selection)){
                    currentResource = i;
                }
            }
            if(currentResource < routine.getAvailableResources().size()-1){
                Collections.swap(routine.getAvailableResources(), currentResource, currentResource+1);
            }
        }
        catch(Exception e){
            //just move on
        }
        showResources();
        updateGraphicalAnalysis();
        currentResource += 1; 
        routine.setSaved(false);
    }//end moveTaskDown()
    
    
    
    
    public void deleteResource(){
        try{
            currentResource = -1;
            String selection = lstResources.getSelectionModel().getSelectedItem().toString();
            for(int i=0; i < routine.getAvailableResources().size(); i++){
                if(routine.getAvailableResources().get(i).toString().equals(selection)){
                    currentResource = i;
                }
            }
            if(currentResource >= 0){
                routine.getAvailableResources().remove(currentResource);
            }
        }
        catch(Exception e){
            //just move on
        }
        showResources();
        updateGraphicalAnalysis();
        currentResource = -1;
        routine.setSaved(false);
    }//end deleteTask()
    
    
    
    
    
    
    
    
        //////////////////////////////////////////////  ROUTINES  /////////////
		
    public void newRoutine(){
        closeRoutine();
        routine = new Routine();
        editRoutineDetails();
        }//end newRoutine()
    
    
    
    
    public void editRoutineDetails(){
        setLeftStatus("Status: Editing routine details...");
        this.routine = new DetailsDialog(this.routine).editRoutine();
        this.routine.findRoutineEndTime();
        btnRoutineDetails.setText(this.routine.toString());
        this.routine.setSaved(false);
        updateGraphicalAnalysis();
        setLeftStatus("Status:...");
        Stage stgMain = (Stage)btnRoutineDetails.getScene().getWindow();
        stgMain.setTitle(routine.getFilepath() != null? "Routines:\t\t" + routine.getFilepath(): "Routines: \t\tunsaved routine");
    }//end editRoutineDetails()
    
    
    
    
    public void saveRoutine(){
        if(routine.getFilename() == null){
            routine.saveAsRoutine();
        }
        else{
            routine.saveRoutine();
        }
        Stage stgMain = (Stage)btnRoutineDetails.getScene().getWindow();
        stgMain.setTitle(routine.getFilepath() != null? "Routines:\t\t" + routine.getFilepath(): "Routines: \t\tunsaved routine");
    }//end saveRoutine()
    
    
    
    
    public void saveAsRoutine(){
        routine.saveAsRoutine();
        Stage stgMain = (Stage)btnRoutineDetails.getScene().getWindow();
        stgMain.setTitle(routine.getFilepath() != null? "Routines:\t\t" + routine.getFilepath(): "Routines: \t\tunsaved routine");
    }//end saveAsRoutine()
    
    
    
    
    public void openRoutine(){
        closeRoutine();
        //Routine another = new Routine();
        //routine = another.openRoutine();
        Routine another = new Routine().openRoutine();
        if(another != null){
            routine = another;
            showTasks();
            showResources();
            updateGraphicalAnalysis();
            btnRoutineDetails.setText(this.routine.toString());
            routine.setSaved(true);
        }
        Stage stgMain = (Stage)btnRoutineDetails.getScene().getWindow();
        stgMain.setTitle(routine.getFilepath() != null? "Routines:\t\t" + routine.getFilepath(): "Routines: \t\tunsaved routine");
    }//end openRoutine()
    
    
    
    
    public void closeRoutine(){
        boolean close = true;
        if(!routine.isSaved()){
            Alert wantSave = new Alert(AlertType.CONFIRMATION);
            Image icon = new Image(getClass().getResourceAsStream("Images/RoutinesIcon.png"));
            Stage stage = (Stage)wantSave.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            wantSave.setTitle("Routines");
            wantSave.setHeaderText("Current routine has not been saved");
            wantSave.setContentText("Do you want to save the changes to this routine before closing?");
            wantSave.getDialogPane().getStylesheets().add(getClass().getResource("Stylesheets/RoutineStyle.css").toExternalForm());
            wantSave.getDialogPane().getStyleClass().add("RoutineStyle");
            
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
            chtTimePie.getChildren().clear();
            chtResourceBurnUp.getChildren().clear();
            chtStaminaComplexityControl.getChildren().clear();
            chtCostPie.getChildren().clear();
            chtGanttChart.getChildren().clear();
            chtResourceUsageChart.getChildren().clear();
            chtPertChart.getChildren().clear();
            Stage stgMain = (Stage)btnRoutineDetails.getScene().getWindow();
            stgMain.setTitle("Routines");
        }
    }//end closeRoutine();
    
    
    
    
    
    
    
    
        ////////////////////////////////////////////// APPEARANCE  ///////////
    
    public void changeTheme(ActionEvent event){
        try{
                //// USE THIS FOR RUNING IN NETBEANS (DEBUGGING)
            //File stylesheet = new File("src/Routines/Stylesheets/RoutineStyle.css");
                //// USE THIS FOR LAUNCHING BUILD PACKAGE CLASSES FROM COMMAND PROMPT
            File stylesheet = new File(Paths.get("").toAbsolutePath().toString() + "/bin/Routines/Stylesheets/RoutineStyle.css");
            PrintWriter pw = new PrintWriter(stylesheet);
            switch(((MenuItem)event.getSource()).getId()){
                case "miLightTheme":
                    pw.print(Themes.LIGHT.changeTheme());
                    break;
                case "miDarkTheme":
                    pw.print(Themes.DARK.changeTheme());
                    break;
                default:
                    pw.print(Themes.LIGHT.changeTheme());
            }
            
            pw.close();
        }
        catch(Exception e){
            //probably a file not found error
            //don't worry about it then
        }
        setRightStatus("Pertinent: *Restart Required to Apply Theme Changes*");
    }//end changeTheme()
    
    
    
    
    
    
        //////////////////////////////////////////////  APPLICATION  /////////
    
    public void getHelp(){
        new HelpDialog("Routines/References/UserManual.txt");
    }//end getHelp()
    
    
    
    
    public void getAbout(){
        new HelpDialog("Routines/References/about.txt");
    }//end getAbout()
    
    
    
    
    public void exit(){
        closeRoutine();
        Platform.exit();
    }//end exit()
    
    
    
    
    public void setLeftStatus(String currentStatus){
        lblLeftStatus.setText(currentStatus);
    }//end setLeftStatus()
    
    
    
    
    public void setRightStatus(String currentStatus){
        lblRightStatus.setText(currentStatus);
    }//end setRightStatus();
    
    
    
    
    
    
    
        ////////////////////////////////////////////  JAVA OBJECECT  /////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this, just need it here for interfacing requirements
        
    }//end initialize()
    
}//end Control
