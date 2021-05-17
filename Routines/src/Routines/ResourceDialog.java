
package Routines;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;


public class ResourceDialog extends Dialog implements Initializable {

    
        //////////////////////////////////////////////////  DATAFIELDS  /////
    
    @FXML
    TextField txtResourceName;
    @FXML
    TextField txtResourceDefaultRole;
    @FXML
    TextField txtPrimaryContactInfo;
    @FXML
    TextField txtResourceCost;
    @FXML
    RadioButton rdbPerHour;
    @FXML
    RadioButton rdbPerDay;
    @FXML
    RadioButton rdbPerWeek;
    @FXML
    RadioButton rdbFlatFee;
    @FXML
    DatePicker dtResourceDaysOff;
    @FXML
    Slider sldResourceStamina;
    @FXML
    ListView lstDaysOff;
    
    
    private Resource resource;
    
    
    
        ////////////////////////////////////////////////  CONSTRUCTORS  ///////
    
    public ResourceDialog(){
    }//end default constructor
    

    public ResourceDialog(String purpose){
        this.resource = new Resource();
        this.setTitle("Routines: Resource");
        if(purpose.equals("add")){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ResourceDialogGUI.fxml"));
                loader.setController(this);
                this.setDialogPane(loader.load());
            }
            catch(Exception e){
                //just move on then
            }
            ButtonType btnConfirm = new ButtonType("Add Resource", ButtonData.OK_DONE);
            this.getDialogPane().getButtonTypes().addAll(btnConfirm, ButtonType.CANCEL);
            Optional<ButtonType> clicked = this.showAndWait();
            if(clicked.get() == btnConfirm){
                //should be finished by the time the button is clicked
            }
            else{
                this.resource = null;
            }
        }
    }//end string-arg constructor
    
    
    
    public ResourceDialog(Resource resource){
        this.resource = resource;
        this.setTitle("Routines: Resource");
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResourceDialogGUI.fxml"));
            loader.setController(this);
            this.setDialogPane(loader.load());
        }
        catch(Exception e){
            //just move on then
        }
        ButtonType btnConfirm = new ButtonType("Update Resource", ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(btnConfirm, ButtonType.CANCEL);
        txtResourceName.setText(this.resource.getName());
        txtResourceDefaultRole.setText(this.resource.getDefaultRole());
        txtPrimaryContactInfo.setText(this.resource.getPrimaryContactInfo());
        txtResourceCost.setText("" + this.resource.getCost());
        setCostBasisRadioButton(this.resource.getUnits());
        sldResourceStamina.setValue(this.resource.getStamina());
        lstDaysOff.getItems().clear();
        lstDaysOff.getItems().addAll(this.resource.daysOff);
        Optional<ButtonType> clicked = this.showAndWait();
        if(clicked.get() == btnConfirm){
            //should be finished by the time the button is clicked
        }
        else{
            this.resource = null;
        }
    }//end one-arg constructor
    
    
    
        ///////////////////////////////////////  GETTERS AND SETTERS  ////////
    
   
    
    
    
    
    
        ///////////////////////////////////////////  RESOURCE METHODS ////////
    
    public CostBasis getCostBasis(){
        if(rdbPerHour.isSelected()){
            return CostBasis.PER_HOUR;
        }
        else if(rdbPerDay.isSelected()){
            return CostBasis.PER_DAY;
        }
        else if(rdbPerWeek.isSelected()){
            return CostBasis.PER_WEEK;
        }
        else if(rdbFlatFee.isSelected()){
            return CostBasis.FLAT_FEE;
        }
        else{
            return CostBasis.FLAT_FEE;
        }
    }//end getCostBasis()
    
    
    
    
    public void setCostBasisRadioButton(CostBasis cb){
        if(cb != null){
            switch(cb){
                case PER_HOUR:
                    rdbPerHour.setSelected(true);
                    break;
                case PER_DAY:
                    rdbPerDay.setSelected(true);
                    break;
                case PER_WEEK:
                    rdbPerWeek.setSelected(true);
                    break;
                case FLAT_FEE:
                    rdbFlatFee.setSelected(true);
                    break;
                default:
                    rdbFlatFee.setSelected(true);
            }
        }
        else{
            rdbFlatFee.setSelected(true);
        }
    }//end setCostBasisRadioButton()
    

    
    public void addDayOff(){
        lstDaysOff.getItems().add(dtResourceDaysOff.getValue());
    }//end addDayOff()
    
    
    
    
    
    public void removeDayOff(){
        String day = lstDaysOff.getSelectionModel().getSelectedItem().toString();
        lstDaysOff.getItems().remove(day);
    }//end removeDayOff()
    
    
    
    
    
     public Resource addResource(){
        if(this.resource != null){
            this.resource.setName(txtResourceName.getText().length()==0?"untitled resource":txtResourceName.getText());
            this.resource.setDefaultRole(txtResourceDefaultRole.getText());
            this.resource.setPrimaryContactInfo(txtPrimaryContactInfo.getText());
            try{
                this.resource.setCost(Double.parseDouble(txtResourceCost.getText()));
            }
            catch(NumberFormatException e){
                this.resource.setCost(1.0);
            }
            this.resource.setUnits(this.getCostBasis());
            this.resource.daysOff.clear();
            this.resource.daysOff.addAll(lstDaysOff.getItems());
            this.resource.setStamina(sldResourceStamina.getValue());
        }
        return this.resource;
    }//end addResource()
     
     
     
     
     
    public Resource editResource(){
        if(this.resource != null){
            this.resource.setName(txtResourceName.getText().length()==0?"untitled resource":txtResourceName.getText());
            this.resource.setDefaultRole(txtResourceDefaultRole.getText());
            this.resource.setPrimaryContactInfo(txtPrimaryContactInfo.getText());
            try{
                this.resource.setCost(Double.parseDouble(txtResourceCost.getText()));
            }
            catch(NumberFormatException e){
                this.resource.setCost(1.0);
            }
            this.resource.setUnits(this.getCostBasis());
            this.resource.daysOff.clear();
            this.resource.daysOff.addAll(lstDaysOff.getItems());
            this.resource.setStamina(sldResourceStamina.getValue());
        }
        return this.resource;
    }//end editResource()
    
    
        ///////////////////////////////////////////  JAVA OBJECTS  ///////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I rarely use this but the interface requires it
    }//end initialize()
    
}//end ResourceDialog