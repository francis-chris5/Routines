
package Routines;

import java.time.temporal.ChronoUnit;


/**
 * List of manners in which time to carry out a Task can be measured. The plan is for this to also return a matrix to right multiply on a matrix of tasks vectored by temporal units into costs (still working on that though)
 * @author Chris Francis
 */
public enum TimeBasis{
    
    MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS;

    
    
    /**
     * Converts this TimeBasis to regular java.time.temporal.ChronoUnit for working with LocalDateTime objects
     * @return <b>ChronoUnit</b> which is one of the time measurement tools built into the Java programming language
     */
    public ChronoUnit getChronoUnits(){
        switch(this){
            case MINUTES:
                return ChronoUnit.MINUTES;
            case HOURS:
                return ChronoUnit.HOURS;
            case DAYS:
                return ChronoUnit.DAYS;
            case WEEKS:
                return ChronoUnit.WEEKS;
            case MONTHS:
                return ChronoUnit.MONTHS;
            case YEARS:
                return ChronoUnit.YEARS;
            default:
                return ChronoUnit.DAYS;
        }
    }//end getChronoUnits()

    
    
    /**
     * overrides default method
     * @return <b>String</b> of the enumerated value in lowercase letters
     */
    @Override
    public String toString() {
        switch(this){
            case MINUTES:
                return "minutes";
            case HOURS:
                return "hours";
            case DAYS:
                return "days";
            case WEEKS:
                return "weeks";
            case MONTHS:
                return "months";
            case YEARS:
                return "years";
            default:
                return "days";
        }
    }//end toString()
  
    
}//end TimeBasis
