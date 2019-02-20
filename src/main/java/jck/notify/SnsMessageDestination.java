package jck.notify;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jck.BusLocation;
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
            PublishResponse response = SnsClient.create().publish((builder) -> {
                builder.topicArn(System.getProperty(SNS_TOPIC_ARN)).message("aaa").build();
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