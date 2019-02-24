package jck;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContainingInAnyOrder;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;

import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BusLocationParserTest {
    private static BusLocation bus1, bus2, bus3;
    private final String basicJsonBusses = "{ \"vehicle\": [ { \"id\": \"1134\", \"lon\": \"-77.032026\", \"routeTag\": \"green\", \"predictable\": \"true\", \"speedKmHr\": \"30\", \"dirTag\": \"sb\", \"heading\": \"178\", \"lat\": \"38.913128\", \"secsSinceReport\": \"3\" }, { \"id\": \"1132\", \"lon\": \"177.030981\", \"routeTag\": \"yellow\", \"predictable\": \"false\", \"speedKmHr\": \"0\", \"dirTag\": \"nb\", \"heading\": \"270\", \"lat\": \"-2.90137\", \"secsSinceReport\": \"11\" }, { \"id\": \"2107\", \"lon\": \"0.1233\", \"routeTag\": \"purple\", \"predictable\": \"true\", \"speedKmHr\": \"0\", \"dirTag\": \"nb\", \"heading\": \"2\", \"lat\": \"0.5594\", \"secsSinceReport\": \"0\" } ], \"lastTime\": { \"time\": \"1548386886124\" }, \"copyright\": \"All data copyright DC Circulator 2019.\" }";

    @Test
    void ComplexJsonIsMultipleBusses() {
        Collection<BusLocation> busses = BusLocationParser.parse(basicJsonBusses);
        assertThat(busses, containsInAnyOrder(bus1, bus2, bus3));
    }

    @Test
    void EmptyStringIsEmptyCollection() {
        Collection<BusLocation> busses = BusLocationParser.parse("");
        assertThat(busses, empty());
    }

    @Test
    void EmptyJsonIsEmptyCollection() {
        Collection<BusLocation> busses = BusLocationParser.parse("{}");
        assertThat(busses, empty());
    }

    @Test
    void InvalidJsonIsEmptyCollection() {
        Collection<BusLocation> busses = BusLocationParser.parse("some junk");
        assertThat(busses, empty());
    }

    @Test
    void BussesCanBeMarshalled() {
        Collection<BusLocation> busses = Arrays.asList(bus1, bus2, bus3);

        String json = BusLocationParser.format(busses);
        
        BusLocation[] response = new Gson().fromJson(json, BusLocation[].class);
        assertEquals(3, response.length);
        assertThat(response, arrayContainingInAnyOrder(bus1, bus2, bus3));
    }

    @Test
    void ManyBussesCanBeMarshalled() {
        Collection<BusLocation> busses = createLargeBusCollection();

        String json = BusLocationParser.format(busses);

        BusLocation[] response = new Gson().fromJson(json, BusLocation[].class);
        assertEquals(35, response.length);
    }

    @BeforeAll
    static void init() {
        Instant baselineReportTime = Instant.ofEpochMilli(1548386886124L);
        bus1 = new BusLocation(1134, new Position(38.913128, -77.032026), "green", "sb", 30, 178,
                baselineReportTime.minusSeconds(3));
        bus2 = new BusLocation(1132, new Position(-2.90137, 177.030981), "yellow", "nb", 0, 270,
                baselineReportTime.minusSeconds(11));
        bus3 = new BusLocation(2107, new Position(0.5594, 0.1233), "purple", "nb", 0, 2, baselineReportTime);
    }

    private Collection<BusLocation> createLargeBusCollection() {
        return Arrays.asList(
            new BusLocation(2009, new Position(38.924438, -77.040023), "green", "nb", 13, 243, Instant.ofEpochMilli(1551037339107L)),
    new BusLocation(2110, new Position(38.84503, -76.988126), "potomac", "nb", 2, 290, Instant.ofEpochMilli(1551037357107L)),
    new BusLocation(2006, new Position(38.897853, -77.0050909), "yellow", "wb", 6, 184, Instant.ofEpochMilli(1551037358107L)),
    new BusLocation(2108, new Position(38.855146, -76.986521), "potomac", "sb", 37, 158, Instant.ofEpochMilli(1551037351107L)),
    new BusLocation(2118, new Position(38.90255, -77.028348), "yellow", "eb", 0, 91, Instant.ofEpochMilli(1551037357107L)),
    new BusLocation(2122, new Position(38.892045, -77.0437479), "mall", "eastbound", 0, 91, Instant.ofEpochMilli(1551037349107L)),
    new BusLocation(2008, new Position(38.901496, -77.031291), "green", "nb", 0, 270, Instant.ofEpochMilli(1551037351107L)),
    new BusLocation(2012, new Position(38.916018, -77.0318999), "green", "nb", 22, 12, Instant.ofEpochMilli(1551037334107L)),
    new BusLocation(2111, new Position(38.902499, -77.0377649), "yellow", "eb", 35, 87, Instant.ofEpochMilli(1551037357107L)),
    new BusLocation(2109, new Position(38.885021, -77.020786), "blue", "eb", 0, 89, Instant.ofEpochMilli(1551037352107L)),
    new BusLocation(3011, new Position(38.890518, -77.022357), "mall", "westbound", 13, 269, Instant.ofEpochMilli(1551037351107L)),
    new BusLocation(2015, new Position(38.9021479, -77.023079), "yellow", "wb", 24, 271, Instant.ofEpochMilli(1551037342107L)),
    new BusLocation(2114, new Position(38.901974, -77.031887), "green", "nb", 19, 0, Instant.ofEpochMilli(1551037336107L)),
    new BusLocation(2014, new Position(38.913948, -77.066725), "yellow", "wb", 13, 338, Instant.ofEpochMilli(1551037357107L)),
    new BusLocation(2010, new Position(38.902654, -77.0508569), "yellow", "eb", 19, 137, Instant.ofEpochMilli(1551037333107L)),
    new BusLocation(2005, new Position(38.913455, -77.066515), "yellow", "wb", 26, 338, Instant.ofEpochMilli(1551037356107L)),
    new BusLocation(2002, new Position(38.902586, -77.0368809), "yellow", "wb", 24, 271, Instant.ofEpochMilli(1551037336107L)),
    new BusLocation(2017, new Position(38.899095, -77.004191), "yellow", "wb", 0, 15, Instant.ofEpochMilli(1551037348107L)),
    new BusLocation(2106, new Position(38.8858249, -77.045003), "mall", "eastbound", 0, 4, Instant.ofEpochMilli(1551037355107L)),
    new BusLocation(2113, new Position(38.923247, -77.047326), "green", "sb", 28, 94, Instant.ofEpochMilli(1551037353107L)),
    new BusLocation(1139, new Position(38.903714, -77.049394), "rosslyn", "eb", 15, 91, Instant.ofEpochMilli(1551037355107L)),
    new BusLocation(2011, new Position(38.84499, -76.987961), "potomac", "nb", 0, 283, Instant.ofEpochMilli(1551037331107L)),
    new BusLocation(2107, new Position(38.895983, -77.00519), "potomac", "sb", 0, 297, Instant.ofEpochMilli(1551037343107L)),
    new BusLocation(2007, new Position(38.911302, -77.031966), "green", "sb", 4, 144, Instant.ofEpochMilli(1551037339107L)),
    new BusLocation(2126, new Position(38.876403, -76.994137), "potomac", "sb", 22, 91, Instant.ofEpochMilli(1551037356107L)),
    new BusLocation(3006, new Position(38.879281, -77.021867), "blue", "wb", 26, 28, Instant.ofEpochMilli(1551037349107L)),
    new BusLocation(2119, new Position(38.880076, -77.0392449), "mall", "westbound", 20, 273, Instant.ofEpochMilli(1551037348107L)),
    new BusLocation(2116, new Position(38.876506, -77.00134), "blue", "eb", 30, 77, Instant.ofEpochMilli(1551037340107L)),
    new BusLocation(3012, new Position(38.92772, -77.036873), "green", "sb", 9, 347, Instant.ofEpochMilli(1551037340107L)),
    new BusLocation(1135, new Position(38.902802, -77.0626489), "yellow", "wb", 6, 266, Instant.ofEpochMilli(1551037356107L)),
    new BusLocation(2001, new Position(38.846292, -76.98809), "potomac", "nb", 0, 87, Instant.ofEpochMilli(1551037333107L)),
    new BusLocation(3014, new Position(38.893017, -76.998112), "potomac", "nb", 7, 304, Instant.ofEpochMilli(1551037357107L)),
    new BusLocation(2016, new Position(38.8830079, -77.022001), "blue", "eb", 0, 181, Instant.ofEpochMilli(1551037355107L)),
    new BusLocation(2117, new Position(38.8887069, -77.023082), "mall", "eastbound", 22, 89, Instant.ofEpochMilli(1551037351107L)),
    new BusLocation(2124, new Position(38.858427, -76.9927809), "potomac", "sb", 30, 88, Instant.ofEpochMilli(1551037348107L)));
    }
}