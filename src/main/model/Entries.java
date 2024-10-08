package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;

// Represents a list of entries
public class Entries implements Writable {
    private String name;
    private ArrayList<Entry> entries;

    // EFFECTS: creates a new empty list of entries with a name
    public Entries(String name) {
        this.name = name;
        this.entries = new ArrayList<Entry>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds an entry to the list of entries and logs the event
    public void addEntry(Entry e) {
        entries.add(e);
        EventLog.getInstance().logEvent(new Event("Added an entry to workout list!"));
    }

    // MODIFIES: this
    // EFFECTS: returns total number of entries
    public int numEntries() {
        return entries.size();
    }


    // REQUIRES: at least 2 entries in the list with the same workout name
    // EFFECTS: gives the user the changes in their repetition, weight and set from 2 most recent workouts
    public String findProgress(String nameWorkout) {
        Entry first = null;
        Entry second = null;
        for (int i = entries.size() - 1; i >= 0; i--) {
            if (entries.get(i).getNameWorkout().equals(nameWorkout)) {
                if (second == null) {
                    second = entries.get(i);
                } else {
                    first = entries.get(i);
                    break;
                }
            }
        }
        if (first == null && second == null) {
            return eventWithNoEntries();
        }
        if (first == null) {
            return eventWithOneEntry(second.getRepetition(), second.getWeight());
        } else {
            EventLog.getInstance().logEvent(new Event("Found progress made from 2 entries!"));
            return "Repetition Change: " + Integer.toString(second.getRepetition() - first.getRepetition())
                    + ", Weight Change: " + Integer.toString(second.getWeight() - first.getWeight());
        }
    }

    // EFFECTS: creates a new event with 0 entries and returns progress
    public String eventWithNoEntries() {
        EventLog.getInstance().logEvent(new Event("Found progress made from 0 entries!"));
        return "No Progress Found!";
    }

    // EFFECTS: creates a new event with 1 entry and returns progress
    public String eventWithOneEntry(int rep, int weight) {
        EventLog.getInstance().logEvent(new Event("Found progress made from 1 entries!"));
        return "Repetition Change: " + Integer.toString(rep) + ", Weight Change: " + Integer.toString(weight);
    }



    // Requires: 0 <= position - 1 < length of entries
    // EFFECTS: deletes an entry from the list
    public void deleteEntry(int position) {
        int normalPosition = position - 1;
        entries.remove(normalPosition);
        EventLog.getInstance().logEvent(new Event("Deleted an entry!"));
    }

    // EFFECTS: deletes an entry from the list
    public void deleteEntryEvent(Entry e) {
        entries.remove(e);
        EventLog.getInstance().logEvent(new Event("Deleted an entry!"));
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    // EFFECTS: Creates a string with all users entries
    public String viewEntries() {
        int counter = 0;
        String listOfEntries = "";
        for (Entry e : entries) {
            counter++;
            listOfEntries += "\n-Entry " + Integer.toString(counter) + ": " + "\nMuscle Group: "
                    + e.getMuscleGroup() + ", Name of Workout: " + e.getNameWorkout()
                    + ", Sets: " + Integer.toString(e.getSet()) + ", Repetition: " + Integer.toString(e.getRepetition())
                    + ", Weight: " + Integer.toString(e.getWeight());
        }
        return listOfEntries;
    }

    // EFFECTS: puts entries into Json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("entries", entriesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray entriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Entry e : entries) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
