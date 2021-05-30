# Routines


<h2>Intro</h2>

I just could not seem to find the project management software I really wanted, so I finally got around to just making it myself. The problem is project management software is set up for massive undertakings and tasks can rarely be scheduled for less than a day, and those that do have minutes and hours seem to glitch out at such short time scales. What I'm looking for is something that will allow me to use gantt charts and \<difficulty\> or \<complexity\> level resources when planning out my lectures for the classroom or other presentations. While I'm at it I might as well go ahead and make it usable for regular project management work as well.

<h2>Installation</h2>
The project is made with JavaFX which means that to run it requires a Java Runtime Enviroment as well as the JavaFX-sdk. The launcher scripts take care of that, but if you prefer the installers are ready and in the directory called "Installers", just download the appropriate one for your machine and run it: windows -double click it, linux -in terminal enter <b>sudo dpkg -i Routines_1.1.2_amd64.deb</b>, and for mac -double click it (I have not yet given the apple store $99 so a security warning will show, go to "System Preferences --> Security & Privacy --> General Tab" to run anway if you want to). To download and run the source simply click github's green "Code" button and download the zip file, after unzipping it navigate into the top-level "Routines" folder and run the appropriate launcher for your system (.bat for Windows and .sh for Linux, .zsh for Mac). Obviously all of this is assuming your machine already has java installed (JRE 8 or higher). If the javafx dependencies are not present when running from the source files it will download them -so it should only happen the first time the application is launched.

The "Routines" folder also contains the test run project (a .rtne file) used in the images shown below if you go with the source code route.





<h2>User Manual</h2>

<h4>ROUTINE:</h4>


<p>The Routines Application will open to an empty routine</p>

<p>Clicking the Routine Details Button will bring up the Routine Details Dialog, selecting [File -> New] from the main menu in order to create a new routine will open directly to the Routine Details Dialog, or selecting [File -> Routine Details] from the main menu will open the Routine Details Dialog: </p>

<ul>
        <li>Enter a name for the routine --NOTE: the name of the routine is different than the filename where it will be saved to allow versioning of a routine--</li>
        <li>Choose one of the Timescale Radio Buttons to set the default Timescale task durations will be measured in --NOTE: this can easily be overridden per-task--</li>
        <li>Enter the projected/anticipated/allocated budget for the project</li>
        <li>Use the date selector to choose the desired/anticipated/projected Start Date for the routine</li>
        <li>Click the "Define Work Schedule" button to configure which days and times will be included or ignored when calculating start and end times for tasks</li>
        <li>The "Notes" text area is for general note keeping on a routine-wide basis</li>

</ul>

 <p>A summary of the routine details will appear as the label for the Routine Details Button</p>
    
 <p>To edit any of the Routine Details simply relaunch the Routine Details Dialog by clicking the Routine Details Button</p>





<h4>RESOURCE:</h4>


   <p>Click the Add Resource Button, or select [Resources -> Add Resource] from the main menu to open the Resource Dialog:</p>
        <ul>
        <li>Enter the name, default role, and primary contact information for the resource</li>
        <li>Provide the cost for the resource and select one of the radio buttons to determine the Cost Basis of how the cost will be applied to the routine</li>
        <li>Move the slider to an appropriate Stamina value for the current resource: the resource stamina will be compared to task complexity as a ratio in a control chart in the Routine Visual Analysis Tab to make sure resources are not over-or-under-whelmed on the project</li>
        <li>Use the Date Picker to add requested days off for the current resource, right click a date on the list to remove it</li>
        <li>All details for a resource are optional fields</li>
        </ul>
        
   <p>A summary of the resource will appear in the Available Resources List on the main Graphical User Interface</p>
        <ul>
        Select a resource from the Available Resources List and use the two arrows located just above the list, or select [Resources -> Move Resource Up/Down] from the main menu, to move the selected resource up or down the list
        Select a resource from the Available Resources List and click the Edit Resource Button, or right-click on the desired resource, or select [Resources -> Edit Resource] to open the Resource Dialog with the selected resource's details
        Select a resource from the Available Resources List and click the Delete Resource Button, or select [Resources -> Delete Resource] to delete the resource from the current routine
        </ul>





<h4>TASK:</h4>


   <p>Click the Add Task Button, or select [Tasks -> Add Task] from the main menu to open the Task Dialog:</p>
    <ul>
        <li>Enter the name, duration, override time scale if necessary</li>
        <li>Select an option for how the task will be scheduled choose a predecessor task OR select start date/time OR select end date/time, if the Timescale is set to minutes or hours enter the start or end time as HH:MM:SS in 24 hour format, otherwise select the desired date from the calendar choices</li>
        <li>assign resources to the task, and select a complexity level for the current task</li>
        <li>All details for a task are optional fields</li>
        </ul>

   <p>A summary of the task will appear in the Task List on the main Graphical User Interface</p>
   <ul>
        <li>Select a task from the Task List and use the arrows located just above the list, or select [Tasks -> Move Resource Up/Down] from the main menu, to move the selected task up or down the list. NOTE: this will not change the task's assigned predecessor</li>
        <li>Select a task from the Task List and click the Edit Task Button, or right-click on the desired task, or select [Tasks -> Edit Selected Task] from the main menu, to open the Task Dialog with the selected tasks details</li>
        <li>Select a task from the Task List and click the Delete Task Button, or select [Tasks -> Delete Selected Task] from the main menu, to remove the task from the current routine</li>
</ul>



<h4>CHARTS & GRAPHS</h4>


   <p>The central region of the main screen displays an assortment of the standard charts and graphs used in Project Management. These graphical representations of the Routine's data all update automatically whenever a change is made to the Routine Details, Resource List, or Task List.</p>
        <ol>
        <li>Gantt Chart</li>
            A bar chart to illustrate the schedule for the Routine and dependencies between tasks
        <li>PERT Chart</li>
            Displays the tasks involved in completing the Routine and highlights the critical path of tasks which actually define the timeline for the project (currently in green).
        <li>Resource Usage</li>
            A stacked bar chart providing a graphical representation of which tasks each Resource has been assigned to
        <li>Graphical Analysis</li>
            A pie chart showing the percentage of time each tasks takes out of the total time
            A Burn-Up chart displaying the projected cost increase for Resources used on each subsequent task in comparison to a linear increase of the projected budget as the Routine progresses
            A pie chart showing the percentage of the total cost that each task is projected to engulf
            A control chart displaying the ratio of Resource Stamina to Task Complexity: Positive -lots of energy for easy task, Negative -low energy for hard task

</ol>


<h4>KEYBOARD SHORTCUTS</h4>

<blockquote>
  <b>FILE:</b>
    
        ctrl+s              :       save
    
        ctrl+shift+n        :       new routine
    
        ctrl+0              :       open routine
    
        ctrl+d              :       edit routine details
    
    
    
    
  <b>HELP:</b>
    
        F1                  :       open user manual


  <b>Tasks:</b>
    
        ctrl+t              :       new 
    
        ctrl+shift+t        :       edit task


  <b>Resources</b>
    
        ctrl+r              :       new resource
    
        ctrl+shift+r        :       edit resource

</blockqoute>



<h4>Images</h4>


![pic_for_github_main_1_light](https://user-images.githubusercontent.com/50467171/119839789-6ca38f00-bed2-11eb-9800-6ba333e4930f.jpg)
![pic_for_github_main_1_dark](https://user-images.githubusercontent.com/50467171/119839793-6d3c2580-bed2-11eb-949d-319b7ef37933.jpg)
![pic_for_github_main_2_light](https://user-images.githubusercontent.com/50467171/119839799-6e6d5280-bed2-11eb-9e42-5a34f6eb3f4c.jpg)
![pic_for_github_main_3_light](https://user-images.githubusercontent.com/50467171/119839797-6dd4bc00-bed2-11eb-979b-a0e774e24964.jpg)
![pic_for_github_main_4_light](https://user-images.githubusercontent.com/50467171/119839796-6dd4bc00-bed2-11eb-89d2-4c1e114cb896.jpg)
![pic_for_github_details_light](https://user-images.githubusercontent.com/50467171/119839788-6ca38f00-bed2-11eb-92fd-c864b6e72fce.jpg)
![pic_for_github_resource_light](https://user-images.githubusercontent.com/50467171/119839782-6c0af880-bed2-11eb-9a3b-c0dd23bd666c.jpg)
![pic_for_github_task_light](https://user-images.githubusercontent.com/50467171/119839785-6ca38f00-bed2-11eb-92b3-c1a387869ee1.jpg)
![pic_for_github_workhours_light](https://user-images.githubusercontent.com/50467171/119839787-6ca38f00-bed2-11eb-9254-df0b45bc34aa.jpg)





