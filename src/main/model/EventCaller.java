package model;

// class which performs all the event calling, instead of doing it from the GUI
public class EventCaller {
    JobApplication newJob;

    // EFFECTS: sets up a job which has had some event called on it in the GUI
    public EventCaller(JobApplication job) {
        this.newJob = job;
    }

    // EFFECTS: getter for the only job in this class
    public JobApplication getJob() {
        return this.newJob;
    }

    // EFFECTS: logs a successful add event of a job
    public void addEvent() {
        EventLog.getInstance().logEvent(new Event("Successfully added " + newJob.toString() + " to the job manager."));
    }

    // EFFECTS: logs a successful remove event of a job
    public void removeEvent() {
        EventLog.getInstance().logEvent(new Event("Successfully removed " + newJob.toString()
                + " from the job manager."));
    }

    // EFFECTS: logs a successful update event of a job
    public void updateEvent() {
        EventLog.getInstance().logEvent(new Event("Successfully updated a job to " + newJob.toString() + " ."));
    }
}
