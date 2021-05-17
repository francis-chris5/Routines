package Routines;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;


public class Resource implements Serializable{
    
    
        //////////////////////////////////////////  DATAFIELDS  ///////////////
    private String name;
    private String defaultRole;
    private String primaryContactInfo;
    private double cost;
    private CostBasis units;
    public LinkedList<LocalDate> daysOff = new LinkedList<>();
    public LinkedList<Task> assignedTo = new LinkedList<>();
    private double stamina;
    
    
    
        /////////////////////////////////////////  CONSTRUCTORS  //////////////
    
    public Resource(){
    }//end default constructor()
    
    public Resource(String name){
        this.name = (name.length() == 0)? "untitled resource": name;
    }//end one-arg constructor()
    
    
    
    
        /////////////////////////////////////////  GETTERS AND SETTERS  ///////
            //need all of them to be an observable for table factories
    public String getName() {
        if(this.name == null){
            this.name = "untitled resource";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(String defaultRole) {
        this.defaultRole = defaultRole;
    }

    public String getPrimaryContactInfo() {
        return primaryContactInfo;
    }

    public void setPrimaryContactInfo(String primaryContactInfo) {
        this.primaryContactInfo = primaryContactInfo;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public CostBasis getUnits() {
        return units;
    }

    public void setUnits(CostBasis units) {
        this.units = units;
    }

    public LinkedList<LocalDate> getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(LinkedList<LocalDate> daysOff) {
        this.daysOff = daysOff;
    }
    
    public LinkedList<Task> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(LinkedList<Task> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public double getStamina() {
        return stamina;
    }

    public void setStamina(double stamina) {
        this.stamina = stamina;
    }
    
    
    
    
    
    
        ///////////////////////////////////////// JAVA OBJECT  ///////////////
    
    @Override
    public String toString(){
        return this.getName() + " reached at " + this.getPrimaryContactInfo() + " is assigned to " + (this.getAssignedTo().isEmpty()?"none":this.getAssignedTo().toString()) + " and gets paid " + this.getCost() + " " + (this.getUnits()!=null?this.getUnits().toString():CostBasis.FLAT_FEE) + ", and needs off on " + (this.daysOff.isEmpty()?"none":this.daysOff.toString());
    }//end toString()
}//end Resource