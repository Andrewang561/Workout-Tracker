package persistence;

import model.Entries;
import model.Entry;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads Entries from JSON data stored in file
// adapted from JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads entries from file and returns it;
    // throws IOException if an error occurs from reading the data from file
    public Entries read() throws IOException {
        String savedInfo = readFile(source);
        JSONObject jsonObject = new JSONObject(savedInfo);
        return parseEntries(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it;
    // throws IOException if error occurs from reading file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses entries from JSON object and returns it
    private Entries parseEntries(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Entries e = new Entries(name);
        addEntries(e, jsonObject);
        return e;
    }

    // MODIFIES: e
    // EFFECTS: parses an entry from JSON object and adds them to entries
    private void addEntries(Entries e, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(e, nextEntry);
        }
    }

    // MODIFIES: e
    // EFFECTS: parses entry from JSON object and adds it to entries
    private void addEntry(Entries e, JSONObject jsonObject) {
        String muscleGroup = jsonObject.getString("muscleGroup");
        int weight = jsonObject.getInt("weight");
        int repetition = jsonObject.getInt("repetition");
        String nameWorkout = jsonObject.getString("nameWorkout");
        int set = jsonObject.getInt("set");
        Entry entry = new Entry(muscleGroup, weight, repetition, nameWorkout, set);
        e.addEntry(entry);
    }
}
