package persistence;

import model.Entries;
import model.Entry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Entries e = new Entries("My Entries");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyEntries() {
        try {
            Entries e = new Entries("My Entries");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyEntries.json");
            writer.open();
            writer.write(e);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyEntries.json");
            e = reader.read();
            assertEquals("My Entries", e.getName());
            assertEquals(0, e.numEntries());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralEntries() {
        try {
            Entries e = new Entries("My Entries");
            e.addEntry(new Entry("Chest", 50, 8, "Bench Press", 3 ));
            e.addEntry(new Entry("Back", 55, 10, "Rows", 5 ));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralEntries.json");
            writer.open();
            writer.write(e);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralEntries.json");
            e = reader.read();
            assertEquals("My Entries", e.getName());
            ArrayList<Entry> entries = e.getEntries();
            assertEquals(2, entries.size());
            checkEntry("Bench Press", 8, 50, 3, "Chest", entries.get(0));
            checkEntry("Rows", 10, 55, 5, "Back", entries.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
