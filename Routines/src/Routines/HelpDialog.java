
package Routines;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HelpDialog extends Dialog implements Initializable{

        /////////////////////////////////////////////  DATAFIELDS  //////////
    
    @FXML
    VBox vbxContent;
    
    
    
    
    
    
    
    
        /////////////////////////////////////////  CONSTRUCTORS  ///////////
    
    public HelpDialog(String filepath){
        this.setTitle("Routines:");
        Image icon = new Image(getClass().getResourceAsStream("Images/RoutinesIcon.png"));
        Stage stage = (Stage)this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HelpDialogGUI.fxml"));
            loader.setController(this);
            this.setDialogPane(loader.load());
        }
        catch(Exception e){
            //just move on then
        }
        String helpString = new String();
        try{
                ////use this in netbeans
            //File file = new File("src/" + filepath);
                ////use this in build
            File file = new File(Paths.get("").toAbsolutePath().toString() + "/bin/" + filepath);
            Scanner readFile = new Scanner(file);
            while(readFile.hasNextLine()){
                helpString += readFile.nextLine();
                helpString += "\n";
            }
            helpString = helpString.replace("&copy;", "" + '\u00a9');
            readFile.close();
        }
        catch(Exception e){
            //probably couldn't find the file
            helpString = "unable to read the file contents";
        }
        Text output = new Text(helpString);
        output.getStyleClass().add("from-file");
        output.setWrappingWidth(vbxContent.getPrefWidth());
        output.setFont(new Font(14));
        vbxContent.getChildren().add(output);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH);
        this.showAndWait();
    }//end constructor
    
    
    
    
    
    
    
    
        /////////////////////////////////////////  JAVA OBJECT  ////////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this, just in the interfacing requirements
    }//end initialize()
    
}//end HelpDialog
