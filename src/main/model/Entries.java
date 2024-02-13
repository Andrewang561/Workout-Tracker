package model;

import java.util.ArrayList;

public class Entries {
    private ArrayList<Entry> entries;

    public Entries() {
        this.entries = new ArrayList<Entry>();
    }

    // MODIFIES: this
    // EFFECTS: adds an entry to the list of entries
    public void addEntry(Entry e) {
        entries.add(e);
    }

    // REQUIRES: at least 2 entries in the list with the same workout name
    // EFFECTS: gives the user the changes in their repetition, weight and set from 2 most recent workouts
    public String findProgress(String nameWorkout) {
        Entry first = null;
        Entry second = null;
        for (int i = entries.size() - 1; i >= 0; i--) {
            if (entries.get(i).getNameWorkout() == nameWorkout) {
                if (second == null) {
                    second = entries.get(i);
                } else {
                    first = entries.get(i);
                    break;
                }
            }
        }
        return "Repetition: " + Integer.toString(second.getRepetition() - first.getRepetition())
                + " Weight: " + Integer.toString(second.getWeight() - first.getWeight());
    }

    // Requires: 0 <= position - 1 < length of entries
    // EFFECTS: deletes an entry from the list
    public void deleteEntry(int position) {
        int normalPosition = position++;
        entries.remove(normalPosition);
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public String viewEntries() {
        for (Entry e : entries) {
            return "\nEntry:" + "\n" + e.getMuscleGroup() + "\n" + e.getNameWorkout()
                    + "\n" + Integer.toString(e.getSet()) + "\n" + Integer.toString(e.getRepetition())
                    + "\n" + Integer.toString(e.getWeight());
        }
        return null;
    }
}
