package ui;

import model.Entry;
import model.Entries;
import model.Event;
import model.EventLog;
import persistence.Writable;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// GraphicalInterface for Workout Tracker Application
public class GraphicalInterface extends JFrame implements ActionListener {
    public static final int HEIGHT = 600;
    public static final int WIDTH = 1000;

    private static final String LOCATION = "./data/diary.json";
    private Entry entry;
    private Entries entries;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel mainMenuButtonPanel;
    private JPanel entriesButtonPanel;
    private JPanel entriesPanel;
    private JScrollPane entriesPanelScrollable;

    // EFFECTS: creates a graphical interface when the application runs
    public GraphicalInterface() {
        super("Workout Tracker");
        setFields();
        setGraphics();
    }

    // MODIFIES: this
    // EFFECTS: starts the application and the save/load functions
    private void setFields() {
        entries = new Entries("User's Workout Tracker");
        jsonWriter = new JsonWriter(LOCATION);
        jsonReader = new JsonReader(LOCATION);
    }

    // MODIFIES: this
    // EFFECTS: creates the JFrame window that the tracker operates on and necessary functions
    private void setGraphics() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setPrintEventLogOnClose();
        setEntriesPanel();
        setMenuInputs();
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    // EFFECTS: creates the graphical interface for the list of entries
    private void setEntriesPanel() {
        entriesPanel = new JPanel(new GridLayout(2, 0));
        entriesPanelScrollable = new JScrollPane(entriesPanel);
        entriesPanelScrollable.setPreferredSize(new Dimension(700, HEIGHT));
        add(entriesPanelScrollable);
    }

    // MODIFIES: this
    // EFFECTS: sets up the buttons for the menu
    private void setMenuInputs() {
        mainMenuButtonPanel = new JPanel(new GridBagLayout());
        mainMenuButtonPanel.setBackground(Color.WHITE);
        add(mainMenuButtonPanel);

        JButton addEntryButton = new JButton("Add Entry");
        JButton findProgressButton = new JButton("Find Progress");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        addEntryButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        findProgressButton.addActionListener(this);

        mainMenuButtonPanel.add(addEntryButton);
        mainMenuButtonPanel.add(findProgressButton);
        mainMenuButtonPanel.add(saveButton);
        mainMenuButtonPanel.add(loadButton);
    }

    // EFFECTS: responds to user's input
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add Entry":
                handleAddEntry();
                break;
            case "Find Progress":
                handleFindProgress();
                break;
            case "Save":
                saveEntry();
                break;
            case "Load":
                loadEntry();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays the entries in the graphic
    private void displayEntries() {
        entriesPanel.removeAll();
        for (Entry entry : entries.getEntries()) {
            JPanel entryCard = new JPanel(new BorderLayout());
            entryCard.setPreferredSize(new Dimension(WIDTH / 2, 100));

            JButton deleteButton = new JButton("Delete");
            JButton entryButton = new JButton(printEntry(entry));

            deleteButton.addActionListener(E -> {
                entries.getEntries().remove(entry);
                entriesPanel.remove(entryCard);
                refreshGraphics();
                displaySuccessGraphic("Successfully deleted entry!");
            });

            entryCard.add(deleteButton, BorderLayout.WEST);
            entryCard.add(entryButton, BorderLayout.CENTER);
            entriesPanel.add(entryCard);
        }
        refreshGraphics();
    }

    // MODIFIES: this
    // EFFECTS: handles adding an entry to the graphical interface
    private void handleAddEntry() {
        try {
            String muscleGroup = JOptionPane.showInputDialog("Enter muscle group targeted");
            int weight = Integer.parseInt(JOptionPane.showInputDialog("Input weight used"));
            int repetition = Integer.parseInt(JOptionPane.showInputDialog("Input number of reps"));
            String nameWorkout = JOptionPane.showInputDialog("Enter the name of the workout");
            int set = Integer.parseInt(JOptionPane.showInputDialog("Input number of sets"));

            if (muscleGroup.isEmpty() || weight < 0 || repetition < 0 || nameWorkout.isEmpty() || set < 0) {
                throw new Exception();
            }

            entries.addEntry(new Entry(muscleGroup, weight, repetition, nameWorkout, set));
            displayEntries();
            displaySuccessGraphic("Successfully added a new entry!");
        } catch (Exception e) {
            displayErrorMessage("Invalid information!");
        }
    }

    // EFFECTS: Displays progress for user
    private void handleFindProgress() {
        String wantedWorkout = JOptionPane.showInputDialog("Enter the name of the workout "
                + "you want to see your progress for");
        String answer = entries.findProgress(wantedWorkout);
        JOptionPane.showMessageDialog(null, answer, "Progress made for " + wantedWorkout,
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Image/dumbbell.jpg"));
    }

    // EFFECTS: saves the list of entries to the file
    private void saveEntry() {
        try {
            jsonWriter.open();
            jsonWriter.write(entries);
            jsonWriter.close();
            displaySuccessGraphic("Successfully saved your entries!");
        } catch (Exception e) {
            displayErrorMessage("Unable to save!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads entries from file
    private void loadEntry() {
        try {
            entries = jsonReader.read();
            displayEntries();
            displaySuccessGraphic("Successfully loaded entries!");
        } catch (IOException e) {
            displayErrorMessage("Unable to read from file!");
        }
    }

    // EFFECTS: displays confirmation message when input is successful
    private void displaySuccessGraphic(String successfulOperation) {
        JOptionPane.showMessageDialog(null, successfulOperation, "Confirmation",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Image/checkmark.png"));
    }

    // EFFECTS: displays an error message when exception is thrown
    private void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error",
                JOptionPane.WARNING_MESSAGE, new ImageIcon("Image/error.png"));

    }

    // EFFECTS: Refreshes the graphical interface
    private void refreshGraphics() {
        validate();
        repaint();
    }

    // EFFECTS: prints out given entry from user
    private String printEntry(Entry e) {
        String listOfEntries = "";
        listOfEntries = "\n-Entry " + ": " + "\nMuscle Group: "
                + e.getMuscleGroup() + ", Name of Workout: " + e.getNameWorkout()
                + ", Sets: " + Integer.toString(e.getSet()) + ", Repetition: "
                + Integer.toString(e.getRepetition())
                + ", Weight: " + Integer.toString(e.getWeight());
        return listOfEntries;
    }

    private void setPrintEventLogOnClose() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                System.exit(0);
            }
        });
    }
}

