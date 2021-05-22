package Routines;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;


public class Task implements Serializable{
	
        /////////////////////////////////////////////  DATAFIELDS  //////////
		
    private String name;
    private long duration;
    private TimeBasis units;
    private TaskScheduler scheduler;
    private Task predecessor;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LinkedList<Resource> assignedResources = new LinkedList<>();
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

    public TaskScheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    
    

    public Task getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Task predecessor) {
        this.predecessor = predecessor;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
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
