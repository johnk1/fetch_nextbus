package jck;

import java.util.Collection;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.rookout.rook.API;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jck.notify.MessageDestination;
import jck.notify.SnsMessageDestination;

public class LambdaRequest implements RequestHandler<Void, Void> {
    private static final Logger log = LoggerFactory.getLogger(LambdaRequest.class);
    private NextBusClient client = new UnirestNextBusClient();
    private MessageDestination messageDestination = new SnsMessageDestination();
   
    public Void handleRequest(Void input, Context context) {
        API.Load();
        
        // input could also be ScheduledEvent from aws-lambda-java-events
        try {
            Collection<BusLocation> busses = client.get();
           //  String busString = busses.stream().map(BusLocation::toString).collect(joining(" "));
            if (!busses.isEmpty()) {
                log.info(busses.stream().map(BusLocation::toString).collect(Collectors.joining(" ")));
                messageDestination.send(busses);
            } else {
                log.info("No bus information was retrieved.");
            }
        } catch (RequestFailedException ex) {
            log.error("Failed to get bus updates.", ex);
        }

        return null;
    }
}