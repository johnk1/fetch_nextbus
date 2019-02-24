package jck;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BusLocationParser {
    private static final Logger log = LoggerFactory.getLogger(BusLocationParser.class);
    private static Gson gson;
    static {
        gson = new Gson();
    }

    public static String format(Collection<BusLocation> busses) {
        return gson.toJson(busses);
    }

    public static Collection<BusLocation> parse(String json) {
        ArrayList<BusLocation> busses = new ArrayList<>();
        try {
            ResponseJson response = gson.fromJson(json, ResponseJson.class);
            if (response != null && response.vehicle != null && response.lastTime != null) {
                Instant reportTime = Instant.ofEpochMilli(response.lastTime.time);
                for (VehicleJson parsedVehicle : response.vehicle) {
                    busses.add(fromVehicleJson(parsedVehicle, reportTime));
                }
            }
        } catch (JsonSyntaxException ex) {
            log.info("GSON couldn't parse string. Start of string is \'{}\'",
                    json.substring(0, Math.min(50, json.length())), ex);
        }

        return busses;
    }

    static private BusLocation fromVehicleJson(VehicleJson vehicleJson, Instant reportTime) {
        return new BusLocation(vehicleJson.id, new Position(vehicleJson.lat, vehicleJson.lon), vehicleJson.routeTag,
                vehicleJson.dirTag, vehicleJson.speedKmHr, vehicleJson.heading,
                reportTime.minusSeconds(vehicleJson.secsSinceReport));
    }

    class VehicleJson {
        long id;
        double lat, lon;
        String routeTag;
        String dirTag;
        boolean predictable;
        int speedKmHr;
        int heading;
        int secsSinceReport;

        // @Override
        // public boolean equals(Object o) {
        // if (o == this)
        // return true;
        // if (!(o instanceof VehicleJson)) {
        // return false;
        // }
        // VehicleJson vehicleJson = (VehicleJson) o;
        // return Objects.equals(id, vehicleJson.id) &&
        // Objects.equals(lat, vehicleJson.lat) &&
        // Objects.equals(lon, vehicleJson.lon) &&
        // Objects.equals(routeTag, vehicleJson.routeTag) &&
        // Objects.equals(dirTag, vehicleJson.dirTag) &&
        // Objects.equals(predictable, vehicleJson.predictable) &&
        // Objects.equals(speedKmHr, vehicleJson.speedKmHr) &&
        // Objects.equals(heading, vehicleJson.heading) &&
        // Objects.equals(secsSinceReport, vehicleJson.secsSinceReport);
        // }

        // @Override
        // public int hashCode() {
        // return Objects.hash(id, lat, lon, routeTag, dirTag, predictable, speedKmHr,
        // heading, secsSinceReport);
        // }

    }

    class LastTimeJson {
        long time;
    }

    class ResponseJson {
        VehicleJson[] vehicle;
        LastTimeJson lastTime;
    }
}