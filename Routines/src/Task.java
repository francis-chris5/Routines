
import java.io.Serializable;


public class Task implements Serializable{
    private String name;
    
    public Task(String name){
        this.name = (name.length() == 0)? "untitled task": name;
    }//end constructor()
    
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
    
    
    @Override
    public String toString(){
        return this.name;
    }
}//end Task
