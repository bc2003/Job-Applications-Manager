package persistence;

import model.CurrentList;
import model.JobApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// entire class referenced from
// represents a reader that reads room from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CurrentList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCurrentList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses room from JSON object and returns it
    private CurrentList parseCurrentList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        CurrentList newCl = new CurrentList(name);
        addJobs(newCl, jsonObject);
        return newCl;
    }

    // MODIFIES: cl
    // EFFECTS: parses job applications from JSON object and adds them to the current list
    private void addJobs(CurrentList cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("jobs");
        for (Object json : jsonArray) {
            JSONObject nextJob = (JSONObject) json;
            addJob(cl, nextJob);
        }
    }

    // MODIFIES: cl
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addJob(CurrentList cl, JSONObject jsonObject) {
        String position = jsonObject.getString("position");
        String company = jsonObject.getString("company");
        int status = jsonObject.getInt("status");
        JobApplication jobApp = new JobApplication(position, company, status);
        cl.addJob(jobApp);
    }
}
