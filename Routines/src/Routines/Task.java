package Routines;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;


/**
 * <h2>Summary</h2>
 * <p>A task is an activity that needs to be carried out in the Routine</p>
 * <p>Each task is given a name, start and end times, an optional predecessor task for scheduling purposes, a complexity level to avoid resources becoming over or under whelmed, and a list of resources assigned to carry out the task.</p>
 * @author Chris Francis
 */
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
    
    /**
     * the default constructor to be used in conjunction with getters and setters
     */
    public Task(){
    }//end default constructor()
    
    
    
    
    /**
     * The one-arg constructor is to make sure that every task is given a name with which to identify it by, this is the primary constructor used for tasks in the code
     * @param name The name by which to identify this tasks on the various lists it shows up on
     */
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
    
    /**
     * override of default method
     * @return <b>String</b> offering a summary of some key points about this Task in phrase form (needs rest of sentence concatenated on for grammatically correct output).
     */
    @Override
    public String toString(){
        return this.getName() + " comes after " + (this.getPredecessor() == null?"nothing":this.predecessor.getName()) + " and should take " + this.getDuration() + " " + this.getUnits().toString() + " to be completed by " + (this.assignedResources.isEmpty()?"nobody???":this.getResourceNames());
    }//end toString()
    
}//end Task
