
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class RoutineController implements Initializable{
	
	
        ///////////////////////////////////////////   FXML COMPONENTS   ///////////
    
    @FXML
    MenuItem miSave;
    
    @FXML
    ImageView imgSave;
    
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
    
    /**
     * Refreshes all of the graphs and charts to the current state of the routine
     */
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
	
    /**
     * Refreshes the lists of tasks
     */
    public void showTasks(){
        lstTasks.getItems().clear();
        for(int i=0; i < routine.getRoutineTasks().size(); i++){
            lstTasks.getItems().add(routine.getRoutineTasks().get(i));
        }
    }//end showTasks()
    
    
    
    /**
     * Adds a new task to the bottom of the list for the current routine
     */
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
            toggleSaveIcon();
        }
        setLeftStatus("Status:...");
    }//end newTask()
    
    
    
    
    /**
     * opens the task dialog with details from the one currently selected from the list for modification
     */
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
                        toggleSaveIcon();
                    }
                }
            }
        }
        catch(Exception e){
            //just move on then
        }
        setLeftStatus("Status:...");
    }//end editTask()
    
    
    
    
    
    /**
     * Moves the currently selected task nearer the beginning of the list
     */
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
        toggleSaveIcon();
    }//end moveTaskUp()
    
    
    
    
    /**
     * moves the currently selected task nearer the end of the list
     */
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
        toggleSaveIcon();
    }//end moveTaskDown()
    
    
    
    
    /**
     * Removes the selected task from the current routine
     */
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
        toggleSaveIcon();
    }//end deleteTask()
    
    
    
    
    
    
    
    
        /////////////////////////////////////////////  RESOURCES  /////////////
    
    /**
     * refresh the list of resources to its current state
     */
    public void showResources(){
        lstResources.getItems().clear();
        for(int i=0; i < routine.getAvailableResources().size(); i++){
            lstResources.getItems().add(routine.getAvailableResources().get(i));
        }
    }//end showResources()
    
    
    
    
    /**
     * add a new resource to this routine
     */
    public void newResource(){
        setLeftStatus("Status: creating new resource...");
        Resource nr = new ResourceDialog("add").addResource();
        if(nr != null){
            routine.getAvailableResources().add(nr);
            showResources();
            showTasks();
            updateGraphicalAnalysis();
            routine.setSaved(false);
            toggleSaveIcon();
        }
        setLeftStatus("Status:...");
    }//end addResource()
     
     
     
    
    /**
     * opens the dialog with details from the currently selected resource for editing
     */
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
                        toggleSaveIcon();
                    }
                }
            }
        }
        catch(Exception e){
            //just move on then
        }
        setLeftStatus("Status:...");
    }//end editResource()
    
    
    
    
    /**
     * moves the selected resource nearer the beginning of the list
     */
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
        toggleSaveIcon();
    }//end moveTaskUp()
    
    
    
    
    /**
     * moves the selected resource nearer the end of the list
     */
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
        toggleSaveIcon();
    }//end moveTaskDown()
    
    
    
    
    /**
     * removes the selected resource from the current routine
     */
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
        toggleSaveIcon();
    }//end deleteTask()
    
    
    
    
    
    
    
    
        //////////////////////////////////////////////  ROUTINES  /////////////
		
    /**
     * closes the current routine and open a new one
     */
    public void newRoutine(){
        closeRoutine();
        routine = new Routine();
        editRoutineDetails();
        }//end newRoutine()
    
    
    
    
    /**
     * opens the routine details dialog for editing
     */
    public void editRoutineDetails(){
        setLeftStatus("Status: Editing routine details...");
        this.routine = new DetailsDialog(this.routine).editRoutine();
        this.routine.findRoutineEndTime();
        btnRoutineDetails.setText(this.routine.toString());
        this.routine.setSaved(false);
        toggleSaveIcon();
        updateGraphicalAnalysis();
        setLeftStatus("Status:...");
        Stage stgMain = (Stage)btnRoutineDetails.getScene().getWindow();
        stgMain.setTitle(routine.getFilepath() != null? "Routines:\t\t" + routine.getFilepath(): "Routines: \t\tunsaved routine");
    }//end editRoutineDetails()
    
    
    
    
    /**
     * saves the current routine, or calls save as if it has not been initially saved
     */
    public void saveRoutine(){
        if(routine.getFilename() == null){
            routine.saveAsRoutine();
            toggleSaveIcon();
        }
        else{
            routine.saveRoutine();
            toggleSaveIcon();
        }
        Stage stgMain = (Stage)btnRoutineDetails.getScene().getWindow();
        stgMain.setTitle(routine.getFilepath() != null? "Routines:\t\t" + routine.getFilepath(): "Routines: \t\tunsaved routine");
    }//end saveRoutine()
    
    
    
    
    /**
     * calls the save as function for the routine object to choose a location to write the file to
     */
    public void saveAsRoutine(){
        routine.saveAsRoutine();
        toggleSaveIcon();
        Stage stgMain = (Stage)btnRoutineDetails.getScene().getWindow();
        stgMain.setTitle(routine.getFilepath() != null? "Routines:\t\t" + routine.getFilepath(): "Routines: \t\tunsaved routine");
    }//end saveAsRoutine()
    
    
    
    
    /**
     * closes the current routine and opens a file dialog to select a previously saved routine to open
     */
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
            toggleSaveIcon();
        }
        Stage stgMain = (Stage)btnRoutineDetails.getScene().getWindow();
        stgMain.setTitle(routine.getFilepath() != null? "Routines:\t\t" + routine.getFilepath(): "Routines: \t\tunsaved routine");
    }//end openRoutine()
    
    
    
    
    /**
     * closes the current routine after asking for confirmation if the routine is in an unsaved state
     */
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
            toggleSaveIcon();
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
    
    /**
     * rewrites the css file to values preset in the Themes enum
     * @param event The button click that called this method
     */
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
    
    /**
     * displays the information from the help text file in a dialog box
     */
    public void getHelp(){
        new HelpDialog("Routines/References/UserManual.txt");
    }//end getHelp()
    
    
    
    /**
     * displays the information from the about text file in a dialog box
     */
    public void getAbout(){
        new HelpDialog("Routines/References/about.txt");
    }//end getAbout()
    
    
    
    
    /**
     * confirms everything is saved and closes the application
     */
    public void exit(){
        closeRoutine();
        Platform.exit();
    }//end exit()
    
    
    
    
    /**
     * update the words showing in the left hand status bar
     * @param currentStatus The words to display in the left hand status bar
     */
    public void setLeftStatus(String currentStatus){
        lblLeftStatus.setText(currentStatus);
    }//end setLeftStatus()
    
    
    
    
    /**
     * update the words showing in the right hand status bar
     * @param currentStatus The words to display in the right hand status bar
     */
    public void setRightStatus(String currentStatus){
        lblRightStatus.setText(currentStatus);
    }//end setRightStatus();
    
    
    
    
    /**
     * alternates between a red and blue save icon or disables the main menu save button for a visual clue that it has or has not been saved
     */
    public void toggleSaveIcon(){
        if(routine.isSaved()){
            miSave.setDisable(true);
            imgSave.setImage(new Image(getClass().getResourceAsStream("Images/SaveIcon.png")));
        }
        else{
            miSave.setDisable(false);
            imgSave.setImage(new Image(getClass().getResourceAsStream("Images/NeedSaveIcon.png")));
        }
    }//end toggleSaveIcon()
    
    
    
    
    
    
    
        ////////////////////////////////////////////  JAVA OBJECECT  /////////
    
    /**
     * I rarely use this, just need it here for interfacing requirements
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this, just need it here for interfacing requirements
        
    }//end initialize()
    
}//end Control
