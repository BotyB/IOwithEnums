package ro.IOwithEnums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class CSVParserTest {

    @Test
    void testInvalidFormat() throws IOException {
        String data = "10,Invalid Guy,RO,30:00,xxoxo\n"; //Missing last 2 shooting fields
        InputStream stream = new ByteArrayInputStream(data.getBytes());
        List<Athlete> athletes = CSVParser.parseCSV(stream);
        Assertions.assertEquals(0, athletes.size()); //Assert 0 athletes
    }
}