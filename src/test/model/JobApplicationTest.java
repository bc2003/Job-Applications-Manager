package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class JobApplicationTest {
    private JobApplication testJob;
    private JobApplication testJobFalseSetup;

    @BeforeEach
    public void setup() {
        testJob = new JobApplication("Cashier", "Walmart", 0);
    }

    @Test
    public void testJobApplication() {
        assertEquals("Cashier", testJob.getTitle());
        assertEquals("Walmart", testJob.getCompany());
        assertEquals(0, testJob.getStatus());
        testJobFalseSetup = new JobApplication("Slave", "McDonald's", 420);
        assertEquals("Slave", testJobFalseSetup.getTitle());
        assertEquals("McDonald's", testJobFalseSetup.getCompany());
        assertEquals(0, testJobFalseSetup.getStatus());
    }

    @Test
    public void testSetTitle() {
        String str = "Cashier";
        assertTrue(str.equals(testJob.getTitle()));
        testJob.setTitle("Manager");
        assertTrue(testJob.getTitle().equals("Manager"));
        testJob.setTitle("Cashier");
        assertTrue(str.equals(testJob.getTitle()));
    }

    @Test
    public void testSetCompany() {
        String str = "Walmart";
        assertTrue(str.equals(testJob.getCompany()));
        testJob.setCompany("Target");
        assertTrue(testJob.getCompany().equals("Target"));
        testJob.setCompany("Walmart");
        assertTrue(str.equals(testJob.getCompany()));
    }

    @Test
    public void testInvalidStatus() {
        testJob.setStatus(7);
        assertEquals(0, testJob.getStatus());
        testJob.setStatus(-1);
        assertEquals(0, testJob.getStatus());
        testJob.setStatus(10);
        assertEquals(0, testJob.getStatus());
        testJob.setStatus(-5);
        assertEquals(0, testJob.getStatus());
        testJob.setStatus(100);
        assertEquals(0, testJob.getStatus());
        testJob.setStatus(-100);
        assertEquals(0, testJob.getStatus());
    }

    @Test
    public void testValidStatus() {
        testJob.setStatus(6);
        assertEquals(6, testJob.getStatus());
        testJob.setStatus(3);
        assertEquals(3, testJob.getStatus());
        testJob.setStatus(0);
        assertEquals(0, testJob.getStatus());
        testJob.setStatus(1);
        assertEquals(1, testJob.getStatus());
    }

    @Test
    public void testChangeToInvalidStatus() {
        testJob.changeStatus(10);
        assertEquals(0, testJob.getStatus());
        testJob.changeStatus(-10);
        assertEquals(0, testJob.getStatus());
        testJob.changeStatus(7);
        assertEquals(0, testJob.getStatus());
        testJob.changeStatus(-1);
        assertEquals(0, testJob.getStatus());
        testJob.changeStatus(100);
        assertEquals(0, testJob.getStatus());
        testJob.changeStatus(-100);
        assertEquals(0, testJob.getStatus());
    }

    @Test
    public void testChangeToValidStatus() {
        testJob.changeStatus(0);
        assertEquals(0, testJob.getStatus());
        testJob.changeStatus(6);
        assertEquals(6, testJob.getStatus());
        testJob.changeStatus(3);
        assertEquals(3, testJob.getStatus());
        testJob.changeStatus(1);
        assertEquals(1, testJob.getStatus());
    }

    @Test
    public void testValidity() {
        assertTrue(JobApplication.validStatus(6));
        assertFalse(JobApplication.validStatus(-1));
        assertTrue(JobApplication.validStatus(3));
        assertFalse(JobApplication.validStatus(7));
        assertTrue(JobApplication.validStatus(0));
        assertFalse(JobApplication.validStatus(420));
    }

    @Test
    public void testSameJob() {
        assertTrue(testJob.sameJob("Cashier", "Walmart"));
        assertFalse(testJob.sameJob("Cashier", "Target"));
        testJob.setCompany("Target");
        assertTrue(testJob.sameJob("Cashier", "Target"));
        assertFalse(testJob.sameJob("Cashier", "Walmart"));
        assertFalse(testJob.sameJob("Manager", "Walmart"));
    }

    @Test
    public void testDisplayJob() {
        testJob.displayJob();
        testJob.setStatus(1);
        testJob.displayJob();
        testJob.setStatus(2);
        testJob.displayJob();
        testJob.setStatus(3);
        testJob.displayJob();
        testJob.setStatus(4);
        testJob.displayJob();
        testJob.setStatus(5);
        testJob.displayJob();
        testJob.setStatus(6);
        assertEquals(6, testJob.getStatus());
        testJob.displayJob();
        testJob.setStatus(7);
        testJob.displayJob();
        assertEquals(6, testJob.getStatus());
    }

    @Test
    public void testToString() {
        testJob.toString();
        testJob.setStatus(1);
        testJob.toString();
        testJob.setStatus(2);
        testJob.toString();
        testJob.setStatus(3);
        testJob.toString();
        testJob.setStatus(4);
        testJob.toString();
        testJob.setStatus(5);
        testJob.toString();
        testJob.setStatus(6);
        assertEquals(6, testJob.getStatus());
        testJob.toString();
        testJob.setStatus(7);
        testJob.toString();
        assertEquals(6, testJob.getStatus());
    }
}