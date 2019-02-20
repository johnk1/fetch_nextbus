package jck;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UnirestNextBusClientTest {

    // @Test 
   // exists to test the GET call
    void getTbd() {
        UnirestNextBusClient client = new UnirestNextBusClient();
        client.get();
        assertTrue(true);
    }
}