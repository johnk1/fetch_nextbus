package jck;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Position {
    private ToStringBuilder toStringBuilder = null;
    private double lat, lon;

    public Position(double latitude, double longitude) {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("latitude must be -90..+90");
        }
        if (longitude < -180 || longitude > +180) {
            throw new IllegalArgumentException("longitude must be -180..+180");
        }

        this.lat = latitude;
        this.lon = longitude;
    }

    public double latitude() {
        return lat;
    }

    public double longitude() {
        return lon;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Position)) return false;

        Position pos2 = (Position) other;
        return Objects.equals(this.lat, pos2.lat) && Objects.equals(this.lon, pos2.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }

    @Override
    public String toString() {
        if (toStringBuilder == null) {
            toStringBuilder = new ToStringBuilder(this);
        }
        
        return toStringBuilder.append(lat).append(lon).build();
    }
}