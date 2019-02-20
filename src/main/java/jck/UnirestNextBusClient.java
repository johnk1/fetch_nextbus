package jck;

import java.util.Collection;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestNextBusClient implements NextBusClient {
    // http://webservices.nextbus.com/service/publicJSONFeed?command=vehicleLocations&a=dc-circulator&r=green&t=0`

    public Collection<BusLocation> get() { // throws RequestFailedException 
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

    // private String fromInputString(InputStream stream) {
    //     String text = null;
    //     try (Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8.name())) {
    //         text = scanner.useDelimiter("\\A").next();
    //     }
    //     return text;
    // }
}