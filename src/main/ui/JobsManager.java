package ui;

import model.CurrentList;
import model.JobApplication;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

// application which keeps track of all your job applications for you
public class JobsManager {
    private ArrayList<JobApplication> manager;
    private static final String JSON_STORE = "./data/myJobs.json";
    private Scanner input;
    private CurrentList cl;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: begins running the job manager application
    public JobsManager() throws FileNotFoundException {
        input = new Scanner(System.in);
        cl = new CurrentList("Your List");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runManager();
    }

    // MODIFIES: this
    // EFFECTS: processes the user's commands
    private void runManager() {
        boolean keepRunning = true;
        String command = null;

        init();

        while (keepRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                takeCommand(command);
            }
        }

        System.out.println("Bye bye! Use me again later.");
    }

    // MODIFIES: this
    // EFFECTS: takes whatever command the user types in
    private void takeCommand(String command) {
        if (command.equals("a")) {
            addJob();
        } else if (command.equals("r")) {
            removeJob();
        } else if (command.equals("u")) {
            updateJobStatus();
        } else if (command.equals("v")) {
            viewJobs();
        } else if (command.equals("s")) {
            saveJobs();
        } else if (command.equals("l")) {
            loadJobs();
        } else {
            System.out.println("What do you mean? Try again, please. \n");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes a job manager for the user
    private void init() {
        manager = new ArrayList<JobApplication>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add job");
        System.out.println("\tr -> remove job");
        System.out.println("\tu -> update a job status");
        System.out.println("\tv -> view stored jobs");
        System.out.println("\ts -> save job applications to file");
        System.out.println("\tl -> load job applications from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a job to the manager
    private void addJob() {
        System.out.println("Enter position of job:");
        String job = input.next();

        System.out.println("Enter company you are applying to:");
        String company = input.next();

        statusMenu();
        System.out.println("Enter the current job status:");
        int status = input.nextInt();
        if (!JobApplication.validStatus(status)) {
            status = 0;
            System.out.println("Input was not a valid status; was set to 0");
        }

        JobApplication newJob = new JobApplication(job, company, status);
        manager.add(newJob);
        System.out.println("Job added to the applications manager!");
        cl.addJob(newJob);
    }

    // MODIFIES: this
    // EFFECTS: removes a job that can be found in manager
    private void removeJob() {
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("What is the position of the job you wanted to remove?");
            String title = input.next();
            System.out.println("What is the company of the job you wanted to remove?");
            String company = input.next();

            for (JobApplication jobs : manager) {
                if (company.equals(jobs.getCompany()) && title.equals(jobs.getTitle())) {
                    manager.remove(jobs);
                    System.out.println("Job successfully removed.");
                    keepRunning = false;
                    break;
                }
            }
            if (keepRunning) {
                System.out.println("Job not found. Try again.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the job status of a preexisting job in the manager
    private void updateJobStatus() {
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("What is the position of the job you wanted to update?");
            String title = input.next();
            System.out.println("What is the company of the job you wanted to update?");
            String company = input.next();

            for (JobApplication jobs : manager) {
                if (jobs.sameJob(jobs.getTitle(), jobs.getTitle())) {
                    statusMenu();
                    System.out.println("What would you like to set the status of the job as?");
                    int newStatus = input.nextInt();
                    jobs.changeStatus(newStatus);
                    System.out.println("Status successfully changed.");
                    keepRunning = false;
                    break;
                }
            }
            if (keepRunning) {
                System.out.println("Job not found.");
            }
            keepRunning = false;
        }
    }

    // EFFECTS: prints all the current jobs in the manager
    private void viewJobs() {
        if (manager.isEmpty()) {
            System.out.println("No Jobs Found");
        } else {
            for (JobApplication jobs : manager) {
                jobs.displayJob();
            }
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveJobs() {
        try {
            jsonWriter.open();
            jsonWriter.write(cl);
            jsonWriter.close();
            System.out.println("Saved " + cl.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads job applications list from file, adds them to active job manager
    private void loadJobs() {
        try {
            cl = jsonReader.read();

            for (JobApplication jobs: cl.getJobs()) {
                manager.add(jobs);
            }

            System.out.println("Loaded " + cl.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: displays options for job status to user
    private void statusMenu() {
        System.out.println("\n\t0 -> interested");
        System.out.println("\t1 -> applied");
        System.out.println("\t2 -> interviewed");
        System.out.println("\t3 -> received offer");
        System.out.println("\t4 -> turned down offer");
        System.out.println("\t5 -> accepted offer");
        System.out.println("\t6 -> rejected");
    }
}
