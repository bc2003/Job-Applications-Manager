package ui;

import model.Event;
import model.EventLog;

// use this class to print the event log out to the console AFTER everything is done
public class ConsolePrinter {
    EventLog eventLog;

    // EFFECTS: assigns the eventLog from the model as the eventLog to be used here
    public ConsolePrinter(EventLog eventLog) {
        this.eventLog = eventLog;
    }

    // EFFECTS: prints out everything from the log into console
    public void printLog() {
        for (Event next: eventLog) {
            System.out.println(next.toString() + "\n");
        }
    }
}
