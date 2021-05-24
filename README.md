# Routines

<h2>Intro</h2>

I just could not seem to find the project management software I really wanted, so I finally got around to just making it myself. The problem is project management software is set up for massive undertakings and tasks can rarely be scheduled for less than a day, and those that do have minutes and hours seem to glitch out at such short time scales. What I'm looking for is something that will allow me to use gantt charts and \<difficulty\> or \<complexity\> level resources when planning out my lectures for the classroom or other presentations. While I'm at it I might as well go ahead and make it usable for regular project management work as well.

<h2>Installation</h2>
The project is made with JavaFX which means that to run it requires a Java Runtime Enviroment as well as the JavaFX-sdk. The launcher scripts take care of that (and soon there will be an installer point towards these scripts appropriately), until the installers are ready simply download the "dist" folder and run the appropriate launcher for your system (.bat for Windows and .sh for Linux, .sh or .zsh for Mac depending on if you've upgraded yet or not --known issue on mac: I forgot to apply stylesheet to change font in 'close without saving dialog' and java can't access Apple's proprietary system fonts, which javafx tries to do by default, left button is save, middle button is don't save, right button is cancel --easy fix at next build), assuming your machine already has java installed. If the javafx dependencies are not present it will download them -so it should only happen the first time the application is launched.

The "dist" folder also contains the test run project used in the images shown below.



<h2>PROJECT PROGRESS</h2>

I've got the data organization, main GUI, and dialog boxes set up the way I wanted them, still a few more charts and graphs and plots I'd like to include, but it's time to start planning the setup and lecture delivery for my summer course and enter the real test phase of this thing. I went with a summary list and dialog editing instead of a table for general overview, but the data is organized into proper Java objects that can easily be stuck right into beans or observable factories to switch that over to traditional tables instead (I may end up doing that eventually anyway). All the background data seems to be handled properly and a couple of the project analysis charts have started coming together nicely...



![pic_for_github_main_1](https://user-images.githubusercontent.com/50467171/119281559-27e2d400-bc04-11eb-86d2-b24d4798db4f.jpg)
![pic_for_github_main_2](https://user-images.githubusercontent.com/50467171/119281557-274a3d80-bc04-11eb-875b-ea3e4c539bb2.jpg)
![pic_for_github_main_3](https://user-images.githubusercontent.com/50467171/119281568-29140100-bc04-11eb-909d-1691f7523493.jpg)
![pic_for_github_details](https://user-images.githubusercontent.com/50467171/119281563-287b6a80-bc04-11eb-9739-f92be5e206d6.jpg)
![pic_for_github_workhours](https://user-images.githubusercontent.com/50467171/119281565-287b6a80-bc04-11eb-85d5-c9c593d6fbfb.jpg)
![pic_for_github_resources](https://user-images.githubusercontent.com/50467171/119281561-287b6a80-bc04-11eb-92b0-9f784b1d5749.jpg)
![pic_for_github_tasks](https://user-images.githubusercontent.com/50467171/119281562-287b6a80-bc04-11eb-88ad-b71b16246d8a.jpg)




version 0.1.1 is ready, at least for beta testing that is...
