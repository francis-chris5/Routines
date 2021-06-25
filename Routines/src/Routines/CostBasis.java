
package Routines;


/**
 * List of manners in which cost can be broken down. The plan is for this to also return a matrix to left multiply on a matrix of resource costs assigned to a particular task (still working on that though)
 * @author Chris Francis
 */
public enum CostBasis {
    PER_HOUR, PER_DAY, PER_WEEK, FLAT_FEE;
    
    
    /**
     * overrides default method
     * @return <b>String</b> in adverb form to concatenate into sentences
     */
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
