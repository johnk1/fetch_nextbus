package jck.notify;

import java.util.Collection;

import jck.BusLocation;

public interface MessageDestination {
    void send(Collection<BusLocation> busses);
}