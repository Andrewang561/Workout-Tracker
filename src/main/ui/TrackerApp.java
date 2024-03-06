package ui;

import model.Entries;
import model.Entry;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Workout Tracker Application
public class TrackerApp {
    private Entries entries;
    private Scanner input;
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Runs the tracker application
    public TrackerApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        entries = new Entries("Andrew's Entries");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTracker();
    }

    // adapted from the teller application
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTracker() {
        boolean keepGoing = true;
        String command = null;

        inTracker();

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // adapted from the teller application
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("e")) {
            doEntry();
        } else if (command.equals("v")) {
            doViewEntries();
        } else if (command.equals("d")) {
            doDeleteEntry();
        } else if (command.equals("p")) {
            doProgressCheck();
        } else if (command.equals("s")) {
            saveEntries();
        } else if (command.equals("l")) {
            loadEntries();
        } else {
            System.out.println("Please select one of the options");
        }
    }

    // MODIFIES: this
    // EFFECTS: starts the tracker
    private void inTracker() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays the main menu
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\te -> write and add your entry to the list!");
        System.out.println("\tv -> view all of your entries!");
        System.out.println("\td -> delete an entry!");
        System.out.println("\tp -> check your progress!");
        System.out.println("\ts -> save your list of entries");
        System.out.println("\tl -> load your list of entries");
        System.out.println("\tq -> quit the application :(");
    }

    // MODIFIES: this
    // EFFECTS: allows the user to enter an entry of their workout
    private void doEntry() {
        System.out.println("Please enter the following information: Targeted Muscle Group");
        String muscleGroup = input.nextLine();
        System.out.println("Please enter the following information: Weight Used");
        int weight = input.nextInt();
        input.nextLine();
        System.out.print("Please enter the following information: Number of Repetitions\n");
        int rep = input.nextInt();
        input.nextLine();
        System.out.print("Please enter the following information: Name of Workout\n");
        String name = input.nextLine();
        System.out.print("Please enter the following information: Number of Sets\n");
        int set = input.nextInt();
        input.nextLine();
        Entry entry = new Entry(muscleGroup, weight, rep, name, set);
        entries.addEntry(entry);
        if (!entry.isProgressiveOverloading()) {
            System.out.println("WARNING: You are not Progressive Overloading!");
        }
        System.out.print("Successfully added!");
    }

    // EFFECTS: shows viewer a list of all their entries
    private void doViewEntries() {
        System.out.println("Here is a list of your entries!:" + entries.viewEntries());
    }

    // MODIFIES: this
    // EFFECTS: deletes an entry from the list decided by the user
    private void doDeleteEntry() {
        System.out.print("Please enter the position of the entry you would like to delete!\n");

        int pos = input.nextInt();
        input.nextLine();
        entries.deleteEntry(pos);
        System.out.println("Deleted!");
    }

    // EFFECTS: shows the user the progress they have made from the 2 most recent workouts
    public void doProgressCheck() {
        System.out.print("Please enter the name of the workout you would like to see your progress for!\n");

        String nameOfWorkout = input.nextLine();
        System.out.println("Here's your progress:\n" + entries.findProgress(nameOfWorkout));
    }

    // EFFECTS: saves the entries to file
    private void saveEntries() {
        try {
            jsonWriter.open();
            jsonWriter.write(entries);
            jsonWriter.close();
            System.out.println("Saved " + entries.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads entries from file
    private void loadEntries() {
        try {
            entries = jsonReader.read();
            System.out.println("Loaded " + entries.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
