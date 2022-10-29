package persistence;

import model.JobApplication;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkJob(String position, String company, int status, JobApplication jobApplication) {
        assertEquals(position, jobApplication.getTitle());
        assertEquals(company, jobApplication.getCompany());
        assertEquals(status, jobApplication.getStatus());
    }
}
