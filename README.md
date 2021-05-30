# Routines


<h2>Intro</h2>

I just could not seem to find the project management software I really wanted, so I finally got around to just making it myself. The problem is project management software is set up for massive undertakings and tasks can rarely be scheduled for less than a day, and those that do have minutes and hours seem to glitch out at such short time scales. What I'm looking for is something that will allow me to use gantt charts and \<difficulty\> or \<complexity\> level resources when planning out my lectures for the classroom or other presentations. While I'm at it I might as well go ahead and make it usable for regular project management work as well.

<h2>Installation</h2>
The project is made with JavaFX which means that to run it requires a Java Runtime Enviroment as well as the JavaFX-sdk. The launcher scripts take care of that (and soon there will be an installer point towards these scripts appropriately), until the installers are ready simply click github's green "Code" button and download the zip file, after unzipping it navigate into the top-level "Routines" folder and run the appropriate launcher for your system (.bat for Windows and .sh for Linux, .zsh for Mac) .exe, .deb, and .dmg installers to point at these launchers will be coming soon (windows-installer is ready, .deb file is ready), and obviously this is assuming your machine already has java installed. If the javafx dependencies are not present it will download them -so it should only happen the first time the application is launched.

The "Routines" folder also contains the test run project (a .rtne file) used in the images shown below.





<h2>PROJECT PROGRESS</h2>

I've got the data organization, main GUI, and dialog boxes set up the way I wanted them, still a few more charts and graphs and plots I'd like to include, but it's time to start planning the setup and lecture delivery for my summer course and enter the real test phase of this thing. I went with a summary list and dialog editing instead of a table for general overview, but the data is organized into proper Java objects that can easily be stuck right into beans or observable factories to switch that over to traditional tables instead (I may end up doing that eventually anyway). All the background data seems to be handled properly and a couple of the project analysis charts have started coming together nicely...

It's at the point I've begun focusing on keyboard shortcuts and other alternative events so there's more than one way to do things, and got started with multiple themes (well... a dark theme... so there's two now). Changing the theme rewrites the primary css file so a restart of the application is required before the changes will take place.


![pic_for_github_main_1_light](https://user-images.githubusercontent.com/50467171/119839789-6ca38f00-bed2-11eb-9800-6ba333e4930f.jpg)
![pic_for_github_main_1_dark](https://user-images.githubusercontent.com/50467171/119839793-6d3c2580-bed2-11eb-949d-319b7ef37933.jpg)
![pic_for_github_main_2_light](https://user-images.githubusercontent.com/50467171/119839799-6e6d5280-bed2-11eb-9e42-5a34f6eb3f4c.jpg)
![pic_for_github_main_3_light](https://user-images.githubusercontent.com/50467171/119839797-6dd4bc00-bed2-11eb-979b-a0e774e24964.jpg)
![pic_for_github_main_4_light](https://user-images.githubusercontent.com/50467171/119839796-6dd4bc00-bed2-11eb-89d2-4c1e114cb896.jpg)
![pic_for_github_details_light](https://user-images.githubusercontent.com/50467171/119839788-6ca38f00-bed2-11eb-92fd-c864b6e72fce.jpg)
![pic_for_github_resource_light](https://user-images.githubusercontent.com/50467171/119839782-6c0af880-bed2-11eb-9a3b-c0dd23bd666c.jpg)
![pic_for_github_task_light](https://user-images.githubusercontent.com/50467171/119839785-6ca38f00-bed2-11eb-92b3-c1a387869ee1.jpg)
![pic_for_github_workhours_light](https://user-images.githubusercontent.com/50467171/119839787-6ca38f00-bed2-11eb-9254-df0b45bc34aa.jpg)





After a couple days of piddling around with minor details, I decided to go ahead and move the version number up to a one and call it preliminarily finished. I'll get started making the installers and formalize it as a release in a day or two...
