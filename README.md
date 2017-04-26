![UP Logo](UP_Logo.PNG)

# COS301 Longsword Navigation

Implementation phase for the NavUP system, COS301, University of Pretoria.

This is the repository for the Navigation module of team Longsword. Team Longsword is building the NavUP system for the Android platform. The project for Longsword will be completed in Java. Navigation will manage the databases for: User Preferences for routes, Custom pins dropped by users, a cache of all recent routes. Navigation also provides an interface for calculating a route. If the route isn't in cache, the GIS module is called to return a calculated route. 

To run the code:
1) Make sure that you have xampp installed
2) Install the IntelliJ IDE (we set up a maven project and SQL DB connection through it)
    1) The Link: https://www.jetbrains.com/student/
    2) Use your student gmail to create an account and download it, students get the full edition for free 
3) Run the apache and MySQL server through xampp
4) Open the project using InteliiJ 
5) In the following directory you will find a .jar file that has to be added as a library
    1) src/main/resources/mysql-connector-java-5.1.41-bin.jar
    2) Right click on the .jar file and add it as a library
6) Now to set up the MySQL DB:
    1) Open up phpMyAdmin (type local host in the browser and go to phpMyAdmin)
    2) Create a database called Navigation (do not create any of the tables)
7) To set up a MySQL connection in IntelliJ simply:
    1) On the menu bar click on view -> Tool Window -> Database 
    2) An empty window pane should open up to the right. Click on the green cross then go to Data Source -> MySQL
    3) Click on MySQL, this will open a new window which will have multiple fields to fill in
        1) Host: Localhost
        2) Databse: Navigation
        3) User: root
        4) Password: leave this field empty 
    4) Once the fields have been filled in click on Apply and then click on the Test Connection button
        1) It should say Successful in green next to the button if everything is okay
    5) To create the tables you will find a .sql file in the resources directory (mentioned above)
        1) Right click on the SQLCommands.sql and then click on run 'SQLCommands.sql...' in the drop down list 
    6) At this point the SQL tables should have been created in the Navigation DB
        1) Refresh the phpMyAdmin page, all of the tables should be visible 
    7) To run the program you need to click on the Run menu option and then click on Run... from the drop down list
        1) This will build the project and will make it runnable
    8) If you would like to run the code from this point onwards:
        1) click shift + F10 or 
        2) click on the run menu option and click on Run 'Main'
8) An output terminal should appear at the bottom containing the results of running the program
    1) The Main used to test this code is simple as the functionality necessary for the Integration teams use was only tested 
