# MyFitnessTracker

### What is the Application?

The application I want to create is a fitness tracker that users will be able to log and track their workout
routines. They are able to select what muscle group they have worked on (*ex.* Shoulders and Biceps), and input
what type of workout they did, for how many repetitions, weight used, etc. The fitness application
will be able to keep track of it all so users know what progress is made/being lost. 


### Who will use it?

The application is designed for **people who are into fitness and health and want to improve
their approach toward the gym**. By keeping track of workouts and weights, users have a better understanding
on their growth and whether or not they have been actively improving. Signs of stagnation could indicate
lack of rest or insufficient consumption of protein. This is just one of many benefits
my application that people who are passionate about fitness will love.

### Why this Application?

I am an active gym goer and one of the main issues I experience is that I often
forget how many repetitions and what weights I used previously. This resulted in my growth
becoming stagnant as I am not pushing myself to use heavier weights. This application would be a great
asset in my gym experience and I am sure others would love it as well. 

# User Stories

- As a user, I want to be able to add my current workout information into a list 
- As a user, I want to be able to view a list that tracks all of my workouts 
- As a user, I want to be able to see the changes in my most recent repetition and weights
- As a user, I want to be able to delete an entry
- As a user, I want to be able to save all of my workout entries
- As a user, I want to be able to load my saved entries from a file

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by pressing
the delete button to the left of the entry
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by pressing the find
progress button on the bottom of the menu which finds the progress made from the two most
recent workouts with the same name
- You can locate my visual component by using the find progress function, adding an entry, inputting incorrect 
information.
- You can save the state of my application by clicking the save button
- You can reload the state of my application by clicking the load button

---

# Phase 4: Task 2
Sun Mar 31 11:28:05 PDT 2024\
Added an entry to workout list!

Sun Mar 31 11:28:24 PDT 2024\
Added an entry to workout list!

Sun Mar 31 11:28:42 PDT 2024\
Found progress made from 2 entries!

Sun Mar 31 11:28:52 PDT 2024\
Deleted an entry!

Sun Mar 31 11:28:53 PDT 2024\
Deleted an entry!

# Phase 4: Task 3
The biggest change I would make on my project would be on the Writeable interface. My 
current implementation forces my Entry and Entries class to implement a toJson() method as both 
these classes implements Writeable. This makes the program unnecessarily more complicated by introducing
an interface class into the project.

To refactor the project, I would delete the Writeable interface and instead of implementing
the conversion of my Entry and Entries into JsonObjects inside of each respective class, I would move it all
into the JsonReader and Writer class. This reduces the number of classes within my project
and increases the readability and simplicity without impacting the functionality or efficiency. 

