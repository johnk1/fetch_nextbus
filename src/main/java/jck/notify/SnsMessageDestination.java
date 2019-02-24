package jck.notify;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jck.BusLocation;
import jck.BusLocationParser;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.NotFoundException;
import software.amazon.awssdk.services.sns.model.PublishResponse;

/*
 * An implementation of MessageDestination that uses AWS SNS as the provider
 */
public class SnsMessageDestination implements MessageDestination {
    private static Logger log = LoggerFactory.getLogger(SnsMessageDestination.class);
    private static final String SNS_TOPIC_ARN = "SNS_Topic_ARN";
    
    public void send(Collection<BusLocation> busses) {
        try {
            String bussesJson = BusLocationParser.format(busses);
            log.debug("Will send {} byte message to SNS topic {}.", bussesJson.getBytes().length, System.getenv(SNS_TOPIC_ARN));

            PublishResponse response = SnsClient.create().publish(builder -> {
                builder.topicArn(System.getenv(SNS_TOPIC_ARN)).message(bussesJson).build();
            });
//             if (response.messageId())
        } catch (NotFoundException notFoundEx) {
            // maybe the throwable is not right here. pass object array and then the throwanle??
            log.error("Could not find SNS Topic. Check configuration of the {} environment variable." +
                " It is currently {}.", SNS_TOPIC_ARN, System.getProperty(SNS_TOPIC_ARN),
                notFoundEx);
        } catch (Exception ex) {
            log.error("Exception caught when publishing SNS message", ex);
        }
    }
}