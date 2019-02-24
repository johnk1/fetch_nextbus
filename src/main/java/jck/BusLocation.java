package jck;

import java.time.Instant;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity representation of a bus location at a single point in time
 */
public class BusLocation {
    private long id;
    private Position position;
    private String route;
    private String direction;
    private int speedKmHr;
    private int heading;
    private Instant reportTime;

    private ToStringBuilder toStringBuilder = null;

    public BusLocation(long id, Position position, String route, String direction, int speedKmHr, int heading,
        Instant reportTime) {
        this.id = id;
        this.position = position;
        this.route = route;
        this.direction = direction;
        this.speedKmHr = speedKmHr;
        this.heading = heading;
        this.reportTime = reportTime;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof BusLocation)) return false;

        BusLocation bus2 = (BusLocation) other;
        return 
            Objects.equals(this.id, bus2.id) && 
            Objects.equals(this.position, bus2.position) && 
            Objects.equals(this.route, bus2.route) && 
            Objects.equals(this.direction, bus2.direction) && 
            Objects.equals(this.speedKmHr, bus2.speedKmHr) && 
            Objects.equals(this.heading, bus2.heading) && 
            Objects.equals(this.reportTime, bus2.reportTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, route, direction, speedKmHr, heading, reportTime);
    }

    @Override
    public String toString() {
        if (toStringBuilder == null) {
            toStringBuilder = new ToStringBuilder(this);
        }
        return toStringBuilder
            .append(id) 
            .append(position)
            .append(route)
            .append(direction)
            .append(speedKmHr)
            .append(heading)
            .append(reportTime).build();
    }

    public String toCtorString() {
        // new BusLocation(1134, new Position(38.913128, -77.032026), "green", "sb", 30, 178,
        //         baselineReportTime.minusSeconds(3));
        String val = "new BusLocation(" + id + ", new Position(" + position.latitude() + ", " + 
            position.longitude() + "), \"" + route + "\", \"" + direction + "\", " + speedKmHr +
            ", " + heading + ", Instant.ofEpochMilli(" + reportTime.toEpochMilli() + "L)), ";
            return val;
    }

    public Position position() {
        return position;
    }
}