package Routines;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;


public class TaskDialog extends Dialog implements Initializable {

        /////////////////////////////////////////////  DATAFIELDS  ///////////
    @FXML
    TextField txtTaskName;
    
    private Task task;
    
    
        ////////////////////////////////////////////  CONSTRUCTORS  /////////
    
    /**
     * 
     * @param confirmLabel <p>What the confirmation button needs to say, probably just "add" or "update"</p>
     */
    public TaskDialog(String confirmLabel){
        this.setTitle("Routines: Task");
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskDialogGUI.fxml"));
            loader.setController(this);
            this.setDialogPane(loader.load());
        }
        catch(Exception e){
            //just move on then
        }
        ButtonType btnAddTask = new ButtonType(confirmLabel, ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(btnAddTask, ButtonType.CANCEL);
        Optional<ButtonType> clicked = this.showAndWait();
        if(clicked.get() == btnAddTask){
            this.task = new Task(txtTaskName.getText());
        }
        else{
            this.task = null;
        }
    }//end one-arg constructor
    
    
    
        /////////////////////////////////////////////  GETTERS AND SETTERS////
    public Task getTask(){
        return this.task;
    }//end getTask()
    
    
    
    
    
    
        ///////////////////////////////////////////  JAVA OBJECTS  ///////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this but the interface requires it
    }//end initialize()
    
}//end TaskDialog()
