package ui;

import model.EventLog;
import model.JobApplication;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new DoneWindow();
            new GraphicalUserInterface();
        } catch (Exception e) {
            System.out.println("Unable to run application: file not found :(");
        }
    }
}
