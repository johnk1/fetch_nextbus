package jck;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;

public class BusLocationParser {
    private static Gson gson;
    static {
        gson = new Gson();
    }

    static Collection<BusLocation> parse(String json) {
        ArrayList<BusLocation> busses = new ArrayList<>();

        ResponseJson response = gson.fromJson(json, ResponseJson.class);
        for (VehicleJson parsedVehicle : response.vehicle) {
            busses.add(fromVehicleJson(parsedVehicle));
        }

        return busses;

    }

    static private BusLocation fromVehicleJson(VehicleJson vehicleJson) {
        return new BusLocation(vehicleJson.id, new Position(vehicleJson.lat, vehicleJson.lon), vehicleJson.routeTag,
                vehicleJson.dirTag, vehicleJson.speedKmHr, vehicleJson.heading, vehicleJson.secsSinceReport);
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