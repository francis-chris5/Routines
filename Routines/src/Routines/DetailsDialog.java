
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

public class DetailsDialog extends Dialog implements Initializable {

    
        ///////////////////////////////////////////////  DATA FIELDS  ////////
    
    private Routine routine;
    
    
    
    
        //////////////////////////////////////////////  CONSTRUCTORS  /////////
    
    public DetailsDialog(){
    }//end default constructor
    
    
    public DetailsDialog(String purpose){
        this.setTitle("Routines: Routine Details");
        if(purpose.equals("new")){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsDialogGUI.fxml"));
                loader.setController(this);
                this.setDialogPane(loader.load());
            }
            catch(Exception e){
                //just move on then
            }
            ButtonType btnConfirm = new ButtonType("Create Routine", ButtonData.OK_DONE);
            this.getDialogPane().getButtonTypes().addAll(btnConfirm, ButtonType.CANCEL);
            Optional<ButtonType> clicked = this.showAndWait();
            if(clicked.get() == btnConfirm){
                routine = new Routine();
            }
            else{
                this.routine = null;
            }
        }
        else if(purpose.equals("edit")){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsDialogGUI.fxml"));
                loader.setController(this);
                this.setDialogPane(loader.load());
            }
            catch(Exception e){
                //just move on then
            }
            ButtonType btnConfirm = new ButtonType("Update Routine", ButtonData.OK_DONE);
            this.getDialogPane().getButtonTypes().addAll(btnConfirm, ButtonType.CANCEL);
            Optional<ButtonType> clicked = this.showAndWait();
            if(clicked.get() == btnConfirm){
                routine = null; //then call editRoutine()
            }
            else{
                this.routine = null;
            }
        }
    }
    
    
    
        ////////////////////////////////////////  GETTERS AND SETTERS  ///////
    
    public Routine getRoutine(){
        return this.routine;
    }//end getRoutine()
    
    
    
    
    
    
        //////////////////////////////////////////////  ROUTINE METHODS  /////
    
    public Routine editRoutine(Routine routine){
        this.routine = routine;
        //adjust it to match inputs on dialog
        return this.routine;
    }//end editRoutine()
    
    
    
    
    
    
    
        ///////////////////////////////////////////////  JAVA OBJECTS  ////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this but the interface requires it
    }//end Initialize()
    
}//end DetailsDialog
