package jck;

import java.io.IOException;
import java.util.Collection;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UnirestNextBusClient implements NextBusClient {
    // http://webservices.nextbus.com/service/publicJSONFeed?command=vehicleLocations&a=dc-circulator&r=green&t=0`

    private static final Logger log = LoggerFactory.getLogger(UnirestNextBusClient.class);

    UnirestNextBusClient() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                log.debug("Shutting down Unirest client");
                Unirest.shutdown();
            } catch (IOException ex) {
                log.error("Exception when shutting down Unirest client", ex);
            }
        }));
    }

    public Collection<BusLocation> get() {
        try {
            HttpResponse<JsonNode> ret = Unirest
                .get("http://webservices.nextbus.com/service/publicJSONFeed")
                .header("Accept", "application/json")
                .queryString("command", "vehicleLocations")
                .queryString("a", "dc-circulator")
                // .queryString("r", "green")
                .queryString("t", "0")
                .asJson();
            return BusLocationParser.parse(ret.getBody().getObject().toString());
        } catch (UnirestException ex) {
            throw new RequestFailedException("Exception getting bus locations from web service", ex);
        }
    }
}