package Routines;


import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;


public class Task implements Serializable{
	
		/////////////////////////////////////////////  DATAFIELDS  ///////////////////
		
    private String name;
    private long duration;
    private TimeBasis units;
    private Task predecessor;
    private long startTime; //convert input string to milliseconds past Unix-Epoch routine start
    private Date startDate;
    private Date endDate;
    public LinkedList<Resource> assignedResources;
    private int complexity; //this value couples with stamina from resources to produce warnings/suggestions if two exhaustive tasks were assigned to a lazy person back to back
	
	
		////////////////////////////////////////////  CONSTRUCTORS  //////////////////
    
    public Task(){
    }//end default constructor()
    
    public Task(String name){
        this.name = (name.length() == 0)? "untitled task": name;
    }//end one-arg constructor()
    
	
	
        /////////////////////////////////////////// GETTERS AND SETTERS  ////////////
                //need all of them to be an observable for table factories
    public String getName() {
        if(this.name.length() == 0){
            this.name = "untitled task";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public TimeBasis getUnits() {
        return units;
    }

    public void setUnits(TimeBasis units) {
        this.units = units;
    }

    public Task getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Task predecessor) {
        this.predecessor = predecessor;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public LinkedList<Resource> getAssignedResources() {
        return assignedResources;
    }

    public void setAssignedResources(LinkedList<Resource> assignedResources) {
        this.assignedResources = assignedResources;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    
    

    
    
    /*
    * Return a checkbox and name for the list view
    * use strike through text if checked
    */
    
	
	///////////////////////////////////////////////  JAVA OBJECT  ///////////////
	
    @Override
    public String toString(){
        return this.getName();
        //return this.name + " comes after " + (this.predecessor == null?"nothing":this.predecessor.getName()) + " and should take " + this.duration + " " + this.units.toString() + " to be completed by " + this.assignedResources.toString();
    }//end toString()
}//end Task
