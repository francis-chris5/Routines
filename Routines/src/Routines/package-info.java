/**
 * <h3>Description</h3>
 * <p>I just can't seem to find the project management software I want. Most of them are made for big projects and seem to glitch out on small timescales (i.e. hours and minutes), but I teach software development courses and like to use Gantt Charts and statistical analysis features from project management software when planning out my courses/lectures. Up to now, I have just used some common project management software ignoring the timescale and doing a mental translation of monetary cost into exhaustion/complexity concerns, finally getting around to making the one I want, but trying to keep it in line with other project management software functionality at the same time.</p>
 * <p>The application is being constructed with Java and JavaFX to ease the replication into web or mobile versions of the app.</p>
 * 
 * <h4>STRUCTURE:</h4>
 * <ul>
 * <li>Routine is the primary object for a project, it is what users will open/save/work-with in the software</li>
 * <li>A Routine is a collection of tasks (actions that need carried out) and resources (people and tools necessary to carry out the tasks), A Routine will also have the start date for the project, a schedule of working/non-working hours and days, and a default timescale to schedule tasks with</li>
 * <li>A Task consists of start and end times, a complexity level, and list of resources assigned to perform the Task</li>
 * <li>A Resource represents a person or tool to be used in the project, a list of days off, a cost for usage, and an Endurance/Exhaustion level</li>
 * <li>An assortment of visual analysis tools have been implemented to help make sense of how the components necessary to carry out the Routine will work together</li>
 * </ul>
 * 
 * 
 * 
 * 
 * <h4>USER-INTERFACE</h4>
 * <ul>
 * <li>The main GUI will contain a list summarizing each Task in sentence form and a list summarizing each Resource in sentence form (the object followed all rules for Beans, so converting to a table rather than a list should be an easy modification), a tab pane holding the visual analysis representations of the Routine, and an assortment of buttons and menus to operate the software.</li>
 * <li>Interaction from the user will be carried out primarily through dialog boxes (selected as a precursor to a mobile version of the application where interaction will be carried out on a specialized Activity to minimize clutter on the main screen)</li>
 * </ul>
 * 
 * 
 * 
 * 
 * <h4>NOTES:</h4>
 * <ul>
 * <li>nothing at this moment, but I wanted to keep this consistent with how I always set up my javadoc so keeping a notes section</li>
 * </ul>
 * 
 * 
 * 
 * @author Chris Francis
 * @version 1.1.2
 */
package Routines;
