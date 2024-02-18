package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EntriesTest {
    private Entries listOfEntry;
    private Entry e;
    private Entries listOfEntry1;
    private ArrayList listOfEntry2;
    private ArrayList listOfEntry3;

    @BeforeEach
    public void runBefore() {
        listOfEntry = new Entries();
        listOfEntry1 = new Entries();
        listOfEntry2 = new ArrayList<Entry>();
        listOfEntry3 = new ArrayList<Entry>();
        Entry e1 = new Entry("Chest", 50, 5, "Bench Press", 3);
        Entry e2 = new Entry("Chest", 55, 3, "Bench Press", 4);
        Entry e3 = new Entry("Back", 80, 3, "Lat Pull-Down", 3);
        listOfEntry.addEntry(e1);
        listOfEntry.addEntry(e2);
        listOfEntry.addEntry(e3);
        listOfEntry1.addEntry(e2);
        listOfEntry1.addEntry(e3);
        listOfEntry2.add(e1);
        listOfEntry2.add(e2);
        listOfEntry2.add(e3);
    }

    @Test
    public void testFindProgress() {
        assertEquals("Repetition: -2 Weight: 5", listOfEntry.findProgress("Bench Press"));
    }

    @Test
    public void testGetEntries() {
        assertTrue(listOfEntry2.equals(listOfEntry.getEntries()));
        assertFalse(listOfEntry3.equals(listOfEntry.getEntries()));
    }

    @Test
    public void testDeleteEntry() {
        listOfEntry.deleteEntry(1);
        assertTrue(listOfEntry1.getEntries().equals(listOfEntry.getEntries()));
        assertFalse(listOfEntry2.equals(listOfEntry.getEntries()));
    }

    @Test
    public void testViewEntries() {
        assertEquals("\n-Entry 1: \nMuscle Group: Chest, Name of Workout: Bench Press, Sets: 4, Repetition: 3, Weight: 55"
                + "\n-Entry 2: \nMuscle Group: Back, Name of Workout: Lat Pull-Down, Sets: 3, Repetition: 3, Weight: 80",
                listOfEntry1.viewEntries());

    }
}