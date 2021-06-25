package Routines;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class RoutineMain extends Application{

    @Override
    public void start(Stage stgMain) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("RoutineGUI.fxml"));
        Scene scnMain = new Scene(root);
        stgMain.setScene(scnMain);
        stgMain.setTitle("Routines");
        Image icon = new Image(getClass().getResourceAsStream("Images/RoutinesIcon.png"));
        stgMain.getIcons().add(icon);
        stgMain.show();
    }//end start()
    
    
    public static void main(String[] args){
        Application.launch(args);
    }//end main()
    
}//end RoutineMain
