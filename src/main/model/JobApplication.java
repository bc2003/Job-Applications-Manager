package model;

// represents a job application which contains fields for the position,
// company, and your current application status
public class JobApplication {
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
    public void jobApplication(String positionTitle, String companyName, int applicationStatus) {
        this.title = positionTitle;
        this.company = companyName;
        if (applicationStatus <= 6 && applicationStatus >= 0) {
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

    // EFFECTS: prints out the job itself
    public boolean displayJob() {
        if (this.status == 0) {
            System.out.println(this.title + "/" + this.company + "/" + "interested");
            return true;
        } else if (this.status == 1) {
            System.out.println(this.title + "/" + this.company + "/" + "applied");
            return true;
        } else if (this.status == 2) {
            System.out.println(this.title + "/" + this.company + "/" + "interviewed");
            return true;
        } else if (this.status == 3) {
            System.out.println(this.title + "/" + this.company + "/" + "received offer");
            return true;
        } else if (this.status == 4) {
            System.out.println(this.title + "/" + this.company + "/" + "turned down offer");
            return true;
        } else if (this.status == 5) {
            System.out.println(this.title + "/" + this.company + "/" + "accepted offer");
            return true;
        } else if (this.status == 6) {
            System.out.println(this.title + "/" + this.company + "/" + "rejected");
            return true;
        }
        return false;
    }
}
