package jck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PositionTest {

    @Test
    void getters_returnExpected() {
        Position p = new Position(10.0, -10.0);
        assertEquals(10.0, p.latitude());
        assertEquals(-10.0, p.longitude());
    }

    @Test
    void constructor_outOfRangeThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Position(90.01, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Position(-90.01, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Position(10, 180.001);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Position(10, -180.001);
        });
    }
}