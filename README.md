# Routines
An upcoming piece of software, not quite ready yet...

((after a recent backup recovery issue --become a fan of all data on cloud factory reset machines often as of late-- I remembered why I don't need the netbeans project on github, so it's just the design documents and src files now , put that in your IDE of choice --or say 'forget this lack of control' and just open the command prompt or bash terminal-- and rebuild the project as needed --ultimately that's what's needed anyway since the peope you want to deliver software to won't be running it from an IDE))



I just can't seem to find the project management software I really want, so I'm going to make it. The problem is such software is set up for massive undertakings and tasks can rarely be scheduled for less than a day, and those that do have hours seem to glitch out at such short time scales. What I'm looking for is something that will allow me to use gantt charts and \<difficulty\> or \<complexity\> level resources when planning out my lectures for the classroom or other presentations. While I'm at it I might as well go ahead and make it usable for regular project management work as well.


So the idea here is a task app beefed up with some project management tools.

add tasks to a routines to-do list, with an estimated start time or predecessor, and length.
"calendar" can range from minutes to years fills automatically from estimated start time and lengths

assign resources to tasks: people, funds, "labor" -an estimate of how hard it will be (exhausted makes next task harder)-,...


get a gantt chart, pie charts for task and resource percentage, pert chart, work breakdown, ...


PROJECT PROGRESS:

I've got the data organization, main GUI, and dialog boxes set up the way I wanted them, ready to start on graphs, charts, and plots. I went with a summary list and dialog editing instead of a table for general overview, but the data is organized into proper Java objects that can easily be stuck right into beans or observable factories to switch that over to traditional tables instead (I may end up doing that eventually anyway). A couple of the project analysis charts have started coming together nicely...



version 0.0.1 is probably only a couple days away...
