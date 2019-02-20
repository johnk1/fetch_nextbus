// package jck;

// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.Arrays;
// import java.util.Collection;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import jck.lambda.MockLambdaContext;
// import jck.notify.MessageDestination;

// @ExtendWith(MockitoExtension.class)
// class LambdaRequestTest {
//     @Mock NextBusClient client;
//     @Mock MessageDestination messageDestination;
//     @InjectMocks LambdaRequest request;

//     @Test
//     void CanGetData() {
//         Collection<BusLocation> busList = Arrays.asList(
//             new BusLocation(10, new Position(+39.1, -76.8), "green", "nb", 10, 330, 155));
//         when(client.get()).thenReturn(busList);
//         request.handleRequest(null, new MockLambdaContext());
//         verify(client).get();
//        //  verify(messageDestination).send(busList);
//     }
// }