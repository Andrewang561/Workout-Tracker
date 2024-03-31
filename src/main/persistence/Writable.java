package persistence;

import org.json.JSONObject;

// Represents a JSON object
// adapted from JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
