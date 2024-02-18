package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {
    private Entry entry;
    private Entry entry1;
    private Entry entry2;
    private Entry entry3;
    private Entry entry4;

    @BeforeEach
    public void runBefore() {
        entry = new Entry("Bicep", 20, 8, "Curl", 3);
        entry1 = new Entry("Shoulder", 50, 6, "Shoulder Press", 2);
        entry2 = new Entry("Chest", 50, 12, "Bench Press", 2);
        entry3 = new Entry("Chest", 50, 8, "Bench Press", 4);
        entry4 = new Entry("Shoulder", 50, 5, "Shoulder Press", 2);
    }

    @Test
    public void testIsProgressiveOverloading() {
        assertTrue(entry1.isProgressiveOverloading());
        assertFalse(entry2.isProgressiveOverloading());
        assertFalse(entry3.isProgressiveOverloading());
        assertFalse(entry4.isProgressiveOverloading());
    }

    @Test
    public void testGetMuscleGroup() {
        assertEquals("Bicep", entry.getMuscleGroup());
        assertEquals("Shoulder", entry1.getMuscleGroup());
    }

    @Test
    public void testGetWeight() {
        assertEquals(20, entry.getWeight());
        assertEquals(50, entry1.getWeight());
    }

    @Test
    public void testGetRepetition() {
        assertEquals(8, entry.getRepetition());
        assertEquals(6, entry1.getRepetition());
    }

    @Test
    public void testGetNameWorkout() {
        assertEquals("Curl", entry.getNameWorkout());
        assertEquals("Shoulder Press", entry1.getNameWorkout());
    }

    @Test
    public void testGetSet() {
        assertEquals(3, entry.getSet());
        assertEquals(2, entry1.getSet());
    }
}
