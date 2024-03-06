package persistence;

import model.Entry;
import model.Entries;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEntry(String nameWorkout, int rep, int weight, int set, String muscleGroup, Entry entry) {
        assertEquals(nameWorkout, entry.getNameWorkout());
        assertEquals(weight, entry.getWeight());
        assertEquals(set, entry.getSet());
        assertEquals(rep, entry.getRepetition());
        assertEquals(muscleGroup, entry.getMuscleGroup());
    }
}
