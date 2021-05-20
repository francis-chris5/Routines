
package Routines;

import java.time.temporal.ChronoUnit;

/**
 * @description <p>The software PLAN will be using Unix-Epoch time instead of calendar dates, so the GUI units need converted to a millisecond basis for internal calculations.</p>
 */
public enum TimeBasis{
    
    MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS;
    
    public long getTimeBasis(){
        switch(this){
            case MINUTES:
                return 1000*60;
            case HOURS:
                return 1000*60*60;
            case DAYS:
                return 1000*60*60*24;
            case WEEKS:
                return 1000*60*60*24*7;
            case MONTHS:
                return 1000*60*60*24*30;
            case YEARS:
                return 1000*60*60*24*365;
            default:
                return 1000*60*60*24;
        }
    }//end getTimeBasis()
    
    
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
