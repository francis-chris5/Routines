package Routines;


import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;


public class Resource implements Serializable{
    
    
        //////////////////////////////////////////  DATAFIELDS  ///////////////
    private String name;
    private String defaultRole;
    private String primaryContactInfo;
    private double cost;
    private CostBasis units;
    public LinkedList<Date> daysOff;
    public LinkedList<Task> assignedTo;
    private int stamina;
    
    
    
        /////////////////////////////////////////  CONSTRUCTORS  //////////////
    
    public Resource(){
    }//end default constructor()
    
    public Resource(String name){
        this.name = (name.length() == 0)? "untitled resource": name;
    }//end one-arg constructor()
    
    
    
    
        /////////////////////////////////////////  GETTERS AND SETTERS  ///////
            //need all of them to be an observable for table factories
    public String getName() {
        if(this.name.length() == 0){
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

    public LinkedList<Date> getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(LinkedList<Date> daysOff) {
        this.daysOff = daysOff;
    }
    
    public LinkedList<Task> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(LinkedList<Task> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    
    
    
    
    
    
        ///////////////////////////////////////// JAVA OBJECT  ///////////////
    
    @Override
    public String toString(){
        return this.getName();
        //return this.name + " reached at " + this.primaryContactInfo + " is assigned to " + this.assignedTo.toString() + " and gets paid " + this.cost + " " + this.units.toString();
    }//end toString()
}//end Resource
