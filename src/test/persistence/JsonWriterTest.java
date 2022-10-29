package persistence;

import model.JobApplication;
import model.CurrentList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            CurrentList cl = new CurrentList("Test List");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyList() {
        try {
            CurrentList currentList = new CurrentList("My List");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyJobList.json");
            writer.open();
            writer.write(currentList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyJobList.json");
            currentList = reader.read();
            assertEquals("My List", currentList.getName());
            assertEquals(0, currentList.numJobs());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralList() {
        try {
            CurrentList cl = new CurrentList("My List");
            cl.addJob(new JobApplication("Cashier", "Walmart", 0));
            cl.addJob(new JobApplication("Slave", "McDonald's", 0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralJobList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralJobList.json");
            cl = reader.read();
            assertEquals("My List", cl.getName());
            List<JobApplication> thingies = cl.getJobs();
            assertEquals(2, thingies.size());
            checkJob("Cashier", "Walmart", 0, cl.getJobs().get(0));
            checkJob("Slave", "McDonald's", 0, cl.getJobs().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
