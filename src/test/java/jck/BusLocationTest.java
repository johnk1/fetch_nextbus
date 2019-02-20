package jck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusLocationTest {

    @Test
    void Equals() {
        BusLocation b1 = new BusLocation(1000, new Position(10, -10), "yellow", "nb", 15, 233, 11);
        assertTrue(b1.equals(new BusLocation(1000, new Position(10, -10), "yellow", "nb", 15, 233, 11)));
        assertFalse(b1.equals(null));
        assertTrue(b1.equals((b1)));
        assertFalse(b1.equals(new BusLocation(1000, new Position(10, -10), "yellow", "nb", 15, 233, 0)));
    }
}