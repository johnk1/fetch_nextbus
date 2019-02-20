package jck;

import java.util.Collection;

public interface NextBusClient {
    

    Collection<BusLocation> get() throws RequestFailedException;
}