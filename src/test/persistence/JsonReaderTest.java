package persistence;

import model.Entries;
import model.Entry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Entries e = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyEntries() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyEntries.json");
        try {
            Entries e = reader.read();
            assertEquals("My Entries", e.getName());
            assertEquals(0, e.numEntries());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralEntries() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralEntries.json");
        try {
            Entries e = reader.read();
            assertEquals("My Entries", e.getName());
            ArrayList<Entry> entries = e.getEntries();
            assertEquals(2, entries.size());
            checkEntry("Bench Press", 8, 50, 3, "Chest", entries.get(0));
            checkEntry("Rows", 10, 55, 5, "Back", entries.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
