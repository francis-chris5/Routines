package Routines;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;


public class Task implements Serializable{
	
        /////////////////////////////////////////////  DATAFIELDS  //////////
		
    private String name;
    private long duration;
    private TimeBasis units;
    private Task predecessor;
    private LocalTime startTime; //convert input string to milliseconds past Unix-Epoch routine start
    private LocalDate startDate;
    private LocalDate endDate;
    public LinkedList<Resource> assignedResources = new LinkedList<>();
    private double complexity; //this value couples with stamina from resources to produce warnings/suggestions if two exhaustive tasks were assigned to a lazy person back to back
	
	
        ///////////////////////////////////////////  CONSTRUCTORS  ////////////
    
    public Task(){
    }//end default constructor()
    
    
    
    
    public Task(String name){
        this.name = (name.length() == 0)? "untitled task": name;
    }//end one-arg constructor()
    
    
    
    
    
    
    
    
        ///////////////////////////////////////// GETTERS AND SETTERS  ////////
    
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LinkedList<Resource> getAssignedResources() {
        return assignedResources;
    }
    
    public String getResourceNames(){
        String rNames = new String("[");
        for(int i=0; i < assignedResources.size(); i++){
            if(i < assignedResources.size() -1){
                rNames += assignedResources.get(i).getName() + ", ";
            }
            else{
                rNames += assignedResources.get(i).getName() + "]";
            }
        }
        return rNames;
    }

    public void setAssignedResources(LinkedList<Resource> assignedResources) {
        this.assignedResources = assignedResources;
    }

    public double getComplexity() {
        return complexity;
    }

    public void setComplexity(double complexity) {
        this.complexity = complexity;
    }
    
    
    
    
    
    
    
    
    
    
    
	///////////////////////////////////////////  JAVA OBJECT  /////////////
	
    @Override
    public String toString(){
        return this.getName() + " comes after " + (this.getPredecessor() == null?"nothing":this.predecessor.getName()) + " and should take " + this.getDuration() + " " + this.getUnits().toString() + " to be completed by " + (this.assignedResources.isEmpty()?"nobody???":this.getResourceNames());
    }//end toString()
    
}//end Task
