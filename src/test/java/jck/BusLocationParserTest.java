package jck;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.*;

class BusLocationParserTest {
    private static BusLocation bus1, bus2, bus3;
    private final String basicJsonBusses = "{ \"vehicle\": [ { \"id\": \"1134\", \"lon\": \"-77.032026\", \"routeTag\": \"green\", \"predictable\": \"true\", \"speedKmHr\": \"30\", \"dirTag\": \"sb\", \"heading\": \"178\", \"lat\": \"38.913128\", \"secsSinceReport\": \"3\" }, { \"id\": \"1132\", \"lon\": \"177.030981\", \"routeTag\": \"yellow\", \"predictable\": \"false\", \"speedKmHr\": \"0\", \"dirTag\": \"nb\", \"heading\": \"270\", \"lat\": \"-2.90137\", \"secsSinceReport\": \"11\" }, { \"id\": \"2107\", \"lon\": \"0.1233\", \"routeTag\": \"purple\", \"predictable\": \"true\", \"speedKmHr\": \"0\", \"dirTag\": \"nb\", \"heading\": \"2\", \"lat\": \"0.5594\", \"secsSinceReport\": \"0\" } ], \"lastTime\": { \"time\": \"1548386886124\" }, \"copyright\": \"All data copyright DC Circulator 2019.\" }";

    @Test
    void ParsMultipleBusses() {        
        Collection<BusLocation> busses = BusLocationParser.parse(basicJsonBusses);
        assertThat(busses, containsInAnyOrder(bus1, bus2, bus3));
    }

    @BeforeAll
    static void init() {
        bus1 = new BusLocation(1134, new Position(38.913128, -77.032026), "green", "sb", 30, 178, 3);
        bus2 = new BusLocation(1132, new Position(-2.90137, 177.030981), "yellow", "nb", 0, 270, 11);
        bus3 = new BusLocation(2107, new Position(0.5594, 0.1233), "purple", "nb", 0, 2, 0);
    }    
}