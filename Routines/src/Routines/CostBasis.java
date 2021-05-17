
package Routines;

/**
 * @description <p>The software will be using Unix-Epoch time instead of calendar dates, so the rates need converted to a millisecond basis for internal calculations.</p>
 */
public enum CostBasis {
    PER_HOUR, PER_DAY, PER_WEEK, FLAT_FEE;
    
    public long getCostBasis(){
            // would work days be better here than calendar --can it change dynamically???
        switch(this){
            case PER_HOUR:
                return 1000*60*60;
            case PER_DAY:
                return 1000*60*60*24;
            case PER_WEEK:
                return 1000*60*60*24*7;
            case FLAT_FEE:
                return 1;
            default:
                return 1;
        }
    }//end getCostBasis()

    @Override
    public String toString() {
        switch(this){
            case PER_HOUR:
                return "hourly";
            case PER_DAY:
                return "daily";
            case PER_WEEK:
                return "weekly";
            case FLAT_FEE:
                return "for a flat fee";
            default:
                return "for a flat fee";
        }
    }//end toString()
    
    
    
}//end CostBasis
