package ui;

import model.JobApplication;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new JobsManager();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found :(");
        }
    }
}
