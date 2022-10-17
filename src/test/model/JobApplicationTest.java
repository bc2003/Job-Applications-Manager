package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class JobApplicationTest {
    private JobApplication testJob;

    @BeforeEach
    public void setup() {
        testJob = new JobApplication();
        testJob.setTitle("Cashier");
        testJob.setCompany("Walmart");
        testJob.setStatus(0);
    }

    @Test
    public void testJobApplication() {
        assertEquals("Cashier", testJob.getTitle());
        assertEquals("Walmart", testJob.getCompany());
        assertEquals(0, testJob.getStatus());
    }

    @Test
    public void testInvalidStatus() {
        testJob.setStatus(10);
        assertEquals(0, testJob.getStatus());
        testJob.setStatus(-5);
        assertEquals(0, testJob.getStatus());
    }

    @Test
    public void testChangeToInvalidStatus() {
        testJob.changeStatus(10);
        assertEquals(0, testJob.getStatus());
        testJob.changeStatus(-10);
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
}