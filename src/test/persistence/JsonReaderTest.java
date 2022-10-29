package persistence;

import model.CurrentList;
import model.JobApplication;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CurrentList cl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyJobList() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyJobList.json");
        try {
            CurrentList cl = reader.read();
            assertEquals("My List", cl.getName());
            assertEquals(0, cl.numJobs());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralJobList() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralJobList.json");
        try {
            CurrentList cl = reader.read();
            assertEquals("My List", cl.getName());
            List<JobApplication> thingies = cl.getJobs();
            assertEquals(2, thingies.size());
            checkJob("Cashier", "Walmart", 0, cl.getJobs().get(0));
            checkJob("Slave", "McDonald's", 0, cl.getJobs().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
