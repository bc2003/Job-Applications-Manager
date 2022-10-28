package model;

import org.json.JSONObject;
import persistence.Writable;

// represents a job application which contains fields for the position,
// company, and your current application status
public class JobApplication implements Writable {
    private String title;              // name of the actual job position/title
    private String company;            // name of the company to apply for
    private int status;                // job application status, represented by ints from 0 to 6

    /* REQUIRES: positionTitle and companyName have non-zero length, applicationStatus must
     * from 0 to 6 inclusive
     * MODIFIES: this
     * EFFECTS: the position of the job is set to positionTitle, the company of the job is set
     *          to companyName, and the status of the application is set to an integer 0 to 6
     *          inclusive where each integer represents an application status, any integer
     *          beyond the boundaries automatically sets status to 0 ("interested")
     */
    public JobApplication(String positionTitle, String companyName, int applicationStatus) {
        this.title = positionTitle;
        this.company = companyName;
        if (validStatus(applicationStatus)) {
            this.status = applicationStatus;
        } else {
            this.status = 0;
        }
    }

    // EFFECTS: returns the job position
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns the company of the job
    public String getCompany() {
        return company;
    }

    // EFFECTS: returns the current job status
    public int getStatus() {
        return status;
    }

    // MODIFIES: this
    // EFFECTS: sets the job position to a given position
    public void setTitle(String position) {
        this.title = position;
    }

    // MODIFIES: this
    // EFFECTS: sets the company to a given company
    public void setCompany(String company) {
        this.company = company;
    }

    // MODIFIES: this
    // EFFECTS: sets the status to a given status
    public void setStatus(int stat) {
        if (validStatus(stat)) {
            this.status = stat;
        }
    }

    // REQUIRES: newStatus be between 0 and 6, inclusive
    // MODIFIES: this
    // EFFECTS: used to change the status of a job application, if newStatus is invalid,
    //          then the status remains the same
    public void changeStatus(int newStatus) {
        if (validStatus(newStatus)) {
            this.status = newStatus;
        }
    }

    // EFFECTS: verifies if the status given is between 0 and 6, inclusive
    public static boolean validStatus(int status) {
        return status <= 6 && status >= 0;
    }

    // EFFECTS: verifies if a job with a position and company is equal to the stored job
    public boolean sameJob(String position, String company) {
        return position.equals(this.title) && company.equals(this.company);
    }

    // EFFECTS: displays the job with its attributes separated by slashes
    public void displayJob() {
        if (this.status == 0) {
            System.out.println(this.title + "/" + this.company + "/interested");
        } else if (this.status == 1) {
            System.out.println(this.title + "/" + this.company + "/applied");
        } else if (this.status == 2) {
            System.out.println(this.title + "/" + this.company + "/interviewed");
        } else if (this.status == 3) {
            System.out.println(this.title + "/" + this.company + "/received offer");
        } else if (this.status == 4) {
            System.out.println(this.title + "/" + this.company + "/turned down offer");
        } else if (this.status == 5) {
            System.out.println(this.title + "/" + this.company + "/accepted offer");
        } else if (this.status == 6) {
            System.out.println(this.title + "/" + this.company + "/rejected :(");
        }
    }

    @Override
    // EFFECTS: returns current JobApplication object as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("position", title);
        json.put("company", company);
        json.put("status", status);
        return json;
    }
}
