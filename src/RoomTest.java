
import org.junit.Test;

import static org.junit.Assert.*;


public class RoomTest {

    @Test
    public void generateChecksumMoreThan5Characters() {
        String teststring = "notarealroom";
        String test = Room.generateChecksum(teststring);
        assertEquals("oarel", test);
    }

    @Test
    public void generateChecksum5Characters() {
        String teststring = "aaaaabbbzyx";
        String test = Room.generateChecksum(teststring);
        assertEquals("abxyz", test);
    }
}