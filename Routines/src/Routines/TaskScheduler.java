
package Routines;


public enum TaskScheduler {
    
    BY_PREDECESSOR, BY_START, BY_END;

    @Override
    public String toString() {
        switch(this){
            case BY_PREDECESSOR:
                return "by predecessor task";
            case BY_START:
                return "by start date/time";
            case BY_END:
                return "by end date/time";
            default:
                return "by predecessor task";
        }
    }//end toString()
    
}//end TaskScheduler
