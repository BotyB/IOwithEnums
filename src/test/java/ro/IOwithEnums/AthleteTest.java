package ro.IOwithEnums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AthleteTest {

    @Test //Testing a new Athlete object + the expected time
    void testPenaltyCalculation() {
        String[] shooting = {"xxoxo", "xxxxx", "oxxox"}; // 3 misses => 30s penalty
        Athlete athlete = new Athlete(42, "Tester2000", "RO", "30:00", shooting);
        assertEquals("30:40 (30:00 + 40s)", athlete.getFormattedTime());
    }

    @Test //Test compareTo method with 2 new Athlete objects
    void testCompareTo() {
        Athlete a1 = new Athlete(1, "CelRapid", "RO", "30:00", new String[]{"xxxxx", "xxxxx", "xxxxx"});
        Athlete a2 = new Athlete(2, "CelIncet", "RO", "30:10", new String[]{"xxxxx", "xxxxx", "xxxxx"});
        assertTrue(a1.compareTo(a2) < 0);
    }

    @Test //Test for equals method
    void testGetters() {
        String[] shooting = {"oxoxx", "oxxxx", "xoxxx"};
        Athlete athlete = new Athlete(99, "Name", "UK", "29:59", shooting);
        assertEquals(99, athlete.getNumber());
        assertEquals("Name", athlete.getName());
        assertEquals("UK", athlete.getCountry());
        assertArrayEquals(shooting, athlete.getShootings());
    }
}