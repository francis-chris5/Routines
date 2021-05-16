
package Routines;

/**
 * @description <p>The software will be using Unix-Epoch time instead of calendar dates, so the GUI units need converted to a millisecond basis for internal calculations.</p>
 */
public enum TimeBasis {
    MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS;
    
    public long getTimeBasis(){
            // would work days be better here than calendar --can it change dynamically???
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
