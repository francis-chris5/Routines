package Routines;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;


/**
 * <h2>Summary</h2>
 * <p>A resource is a person or tool needed to carry out the Tasks in this Routine.</p>
 * <p>Each resource is given a name by which to identify it and a default role in the Routine, contact info seems a standard to include here, lists for days requested off and Tasks this Resource has been assigned to, and finally a numerical representation of stamina -how quickly this resource becomes exhausted- in order to try and prevent Resources becoming over or under whelmed while working through the Routine.</p>
 * @author Chris Francis
 */
public class Resource implements Serializable{
    
    
        //////////////////////////////////////////  DATAFIELDS  ///////////////
    private String name;
    private String defaultRole;
    private String primaryContactInfo;
    private double cost;
    private CostBasis units;
    private LinkedList<LocalDate> daysOff = new LinkedList<>();
    private LinkedList<Task> assignedTo = new LinkedList<>();
    private double stamina;
    
    
    
    
    
    
    
    
        /////////////////////////////////////////  CONSTRUCTORS  //////////////
    
    /**
     * the default constructor to be used in conjunction with getters and setters
     */
    public Resource(){
    }//end default constructor()
    
    
    
    
    /**
     * The one-arg constructor is used most often in the code and requires a value be passed in to identify this resource in the various lists, legends, and summaries it will show up on
     * @param name The identifier for this resource
     */
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
    
    public String getTaskNames(){
        String tNames = new String("[");
        for(int i=0; i < assignedTo.size(); i++){
            if(i < assignedTo.size() - 1){
                tNames += assignedTo.get(i).getName() + ", ";
            }
            else{
                tNames += assignedTo.get(i).getName() + "]";
            }
        }
        return tNames;
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
    
    /**
     * override of default method
     * @return <b>String</b> offering a summary of some key points about this Resource in phrase form (needs rest of sentence concatenated on for grammatically correct output).
     */
    @Override
    public String toString(){
        return this.getName() + " reached at " + this.getPrimaryContactInfo() + " is assigned to " + (this.getAssignedTo().isEmpty()?"none":this.getTaskNames()) + " and gets paid " + this.getCost() + " " + (this.getUnits()!=null?this.getUnits().toString():CostBasis.FLAT_FEE) + ", and needs off on " + (this.daysOff.isEmpty()?"none":this.daysOff.toString());
    }//end toString()
}//end Resource
