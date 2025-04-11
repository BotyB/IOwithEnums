package ro.IOwithEnums;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class BiathlonAppTest {

    @Test
    void testAppLaunch() {
        // This test checks if the application can be launched without exceptions
        String[] args = {};
        BiathlonApp.main(args);
        assertTrue(true); //If no exceptions are thrown, the test passes
    }

    @Test
    void testCheckIfFileIsCreated() { //Test if the serialized file is created
        BiathlonApp.main(new String[]{});
        File file = new File("top3.ser");
        assertTrue(file.exists());
    }
}