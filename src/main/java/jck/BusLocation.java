package jck;

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
    private int secsSinceReport;

    private ToStringBuilder toStringBuilder = null;

    public BusLocation(long id, Position position, String route, String direction, int speedKmHr, int heading,
        int secsSinceReport) {
        this.id = id;
        this.position = position;
        this.route = route;
        this.direction = direction;
        this.speedKmHr = speedKmHr;
        this.heading = heading;
        this.secsSinceReport = secsSinceReport;
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
            Objects.equals(this.secsSinceReport, bus2.secsSinceReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, route, direction, speedKmHr, heading, secsSinceReport);
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
            .append(secsSinceReport).build();
    }

    public Position position() {
        return position;
    }
}