package model;

import model.EventCaller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// unit tests for the EventCaller class in model package
public class EventCallerTest {
    private EventCaller eventCaller;
    JobApplication newJob;

    @BeforeEach
    public void runBefore() {
        newJob = new JobApplication("Cashier", "Walmart", 0);
        eventCaller = new EventCaller(newJob);
    }

    @Test
    public void testConstructor() {
        assertEquals(newJob, eventCaller.getJob());
    }

    @Test
    public void testAddEvent() {
        eventCaller.addEvent();
    }

    @Test
    public void testRemoveEvent() {
        eventCaller.removeEvent();
    }

    @Test
    public void testUpdateEvent() {
        eventCaller.updateEvent();
    }
}
