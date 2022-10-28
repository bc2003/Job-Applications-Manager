package persistence;

import org.json.JSONObject;

// referenced from the sample application on edX
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
