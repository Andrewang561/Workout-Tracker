package ui;

import java.io.FileNotFoundException;

// Represents the main UI
public class Main {
    // EFFECTS: Initializes the application
    public static void main(String[] args) {
        try {
            new TrackerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
