package Routines;


import java.io.Serializable;


public class Resource implements Serializable{
    
    
        //////////////////////////////////////////  DATAFIELDS  ///////////////
    private String name;
    
    
    
        /////////////////////////////////////////  CONSTRUCTORS  //////////////
    
    public Resource(){
        this.name = "untitled resource";
    }//end default constructor()
    
    public Resource(String name){
        this.name = (name.length() == 0)? "untitled resource": name;
    }//end one-arg constructor()
    
    
    
    
        /////////////////////////////////////////  GETTERS AND SETTERS  ///////
    
    public String getName(){
        return this.name;
    }//end getName()
    
    public void setName(String name){
        this.name = name;
    }//end setName()
    
    
    
    
        ///////////////////////////////////////// JAVA OBJECT  ///////////////
    
    @Override
    public String toString(){
        return this.name;
    }//end toString()
}//end Resource
