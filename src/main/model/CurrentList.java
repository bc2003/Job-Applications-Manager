package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// represents a current list of jobs stored in the job applications manager
public class CurrentList implements Writable {
    private String name;
    private List<JobApplication> jobList;

    // EFFECTS: constructs a job list with a name and is empty
    public CurrentList(String name) {
        this.name = name;
        jobList = new ArrayList<>();
    }

    // EFFECTS: getter method which returns name of your job applications list
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds a job to the current list
    public void addJob(JobApplication jobApp) {
        jobList.add(jobApp);
    }

    // EFFECTS: returns an unmodifiable list of jobs in this list
    public List<JobApplication> getJobs() {
        return Collections.unmodifiableList(jobList);
    }

    // EFFECTS: returns number of jobs stored in this list
    public int numJobs() {
        return jobList.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("jobs", jobsToJson());
        return json;
    }

    // EFFECTS: returns jobs in the list as a JSON array
    private JSONArray jobsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (JobApplication job : jobList) {
            jsonArray.put(job.toJson());
        }
        return jsonArray;
    }
}
