
package Routines;

/**
 * The options of how a Task can be scheduled: BY_PREDECESSOR -which task must come before it start and end times are calculated (default), BY_START -what date/time it must start on and end time is calculated, BY_END -what date is must be completed by and start time is caluclated 
 * @author Chris Francis
 */
public enum TaskScheduler {
    
    BY_PREDECESSOR, BY_START, BY_END;

    /**
     * overrides default method
     * @return <b>String</b> which basically just tacks the word "by" on the enumerated label to fit nicely in a sentence
     */
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
