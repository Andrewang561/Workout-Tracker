package ui;

import model.Entry;
import model.Entries;
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
    public static final int HEIGHT = 800;
    public static final int WIDTH = 800;

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
        setEntryInputs();
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
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        addEntryButton.addActionListener(this));
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
    }
}
