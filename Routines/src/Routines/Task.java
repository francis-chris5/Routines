package Routines;


import java.io.Serializable;
import java.util.LinkedList;


public class Task implements Serializable{
	
		/////////////////////////////////////////////  DATAFIELDS  ///////////////////
		
    private String name;
    public LinkedList<Resource> assignedResources = new LinkedList<>();
	
	
		////////////////////////////////////////////  CONSTRUCTORS  //////////////////
    
    public Task(){
        this.name = "untitled task";
    }//end default constructor()
    
    public Task(String name){
        this.name = (name.length() == 0)? "untitled task": name;
    }//end one-arg constructor()
    
	
	
		/////////////////////////////////////////// GETTERS AND SETTERS  ////////////
		
    public String getName(){
        return this.name;
    }//end getName()
    
    public void setName(String name){
        this.name = name;
    }//end setName()

    
    
    /*
    * Return a checkbox and name for the list view
    * use strike through text if checked
    */
    
	
	///////////////////////////////////////////////  JAVA OBJECT  ///////////////
	
    @Override
    public String toString(){
        return this.name;
    }//end toString()
}//end Task
