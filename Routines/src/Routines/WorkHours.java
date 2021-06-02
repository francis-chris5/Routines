
package Routines;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;


/**
 * The class created to hold and transfer data between the Routine object and the WorkHours dialog the user will interface with, pretty much set up as a standard Bean.
 * @author Chris Francis
 */
public class WorkHours implements Serializable {
    
        ///////////////////////////////////////////////  DATAFIELDS  /////////
    
    private DayOfWeek weekStart = DayOfWeek.MONDAY;
    private DayOfWeek weekEnd = DayOfWeek.FRIDAY;
    private LocalTime dayStart = LocalTime.of(8, 0, 0);
    private LocalTime lunchBreak = LocalTime.of(12, 0, 0);
    private LocalTime dayEnd = LocalTime.of(17, 0, 0);
    private LinkedList<LocalDate> holidays = new LinkedList<>();
    private LinkedList<DayOfWeek> workingDays = new LinkedList<>();
    private LinkedList<LocalTime> workingHours = new LinkedList<>();
    
    
    
    
    
    
        /////////////////////////////////////////////  CONSTRUCTORS  /////////
    
    public WorkHours(){
    }//end default constructor
    
    
    
    
    
    
    
    
        ///////////////////////////////////////////  GETTERS AND SETTERS  /////
    
    public DayOfWeek getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(DayOfWeek weekStart) {
        this.weekStart = weekStart;
    }

    public DayOfWeek getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(DayOfWeek weekEnd) {
        this.weekEnd = weekEnd;
    }

    public LocalTime getDayStart() {
        return dayStart;
    }

    public void setDayStart(LocalTime dayStart) {
        this.dayStart = dayStart;
    }

    public LocalTime getLunchBreak() {
        return lunchBreak;
    }

    public void setLunchBreak(LocalTime lunchBreak) {
        this.lunchBreak = lunchBreak;
    }

    public LocalTime getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(LocalTime dayEnd) {
        this.dayEnd = dayEnd;
    }

    public LinkedList<LocalDate> getHolidays() {
        return holidays;
    }

    public void setHolidays(LinkedList<LocalDate> holidays) {
        this.holidays = holidays;
    }

    public LinkedList<DayOfWeek> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(LinkedList<DayOfWeek> workingDays) {
        this.workingDays = workingDays;
    }

    public LinkedList<LocalTime> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(LinkedList<LocalTime> workingHours) {
        this.workingHours = workingHours;
    }
    
    
    
    
    
    
    
    
        /////////////////////////////////////////////  CLASS METHODS  ////////
    
    /**
     * The dialog only has the user enter start and end dates, this fills in the list with all days/hours between
     */
    public void setWorkingLists(){
        for(DayOfWeek d = weekStart; !d.equals(weekEnd.plus(1)); d = d.plus(1)){
            workingDays.add(d);
        }
        for(LocalTime h = dayStart; !h.equals(dayEnd.plusHours(1)); h = h.plusHours(1)){
            workingHours.add(h);
        }
    }//end setWorkingLists()
    
    
    
    
    
    
    
        //////////////////////////////////////////  JAVA OBJECT  //////////
    
    /**
     * overrides the default method
     * @return <b>String</b> summarizing the work week into sentence form
     */
    @Override
    public String toString() {
        return getWeekStart() + " to " + getWeekEnd() + ", from " + getDayStart() + " to " + getDayEnd()+ " except for lunch at " + this.getLunchBreak() + ", and excluding " + (getHolidays().isEmpty()?"none":"holidays on " + this.getHolidays());
    }

}//end WorkHours
