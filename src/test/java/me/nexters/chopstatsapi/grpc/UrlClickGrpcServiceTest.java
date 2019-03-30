package me.nexters.chopstatsapi.grpc;

import com.google.protobuf.Timestamp;
import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.testing.GrpcCleanupRule;
import me.nexters.chopstatsapi.rabbitmq.producer.Producer;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;


/**
 * @author junho.park
 */

@RunWith(MockitoJUnitRunner.class)
public class UrlClickGrpcServiceTest {
    private static final String SERVER_NAME = InProcessServerBuilder.generateName();

    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    @Mock
    private StreamObserver<Success> responseObserver;

    private ManagedChannel inProcessChannel = grpcCleanup.register(InProcessChannelBuilder
            .forName(SERVER_NAME)
            .directExecutor()
            .build());

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Producer producer = mock(Producer.class);
        Server server = InProcessServerBuilder
                .forName(SERVER_NAME)
                .directExecutor()
                .addService(new UrlClickGrpcService(producer)).build();

        server.start();

    }

    @Test
    public void shouldInvokeOnCompleted() {
        Url url = makeSimpleUrl();
        UrlClickServiceGrpc.UrlClickServiceStub stub = UrlClickServiceGrpc.newStub(inProcessChannel);

        stub.unaryRecordCount(url, responseObserver);

        then(responseObserver).should(timeout(1)).onNext(any());
        then(responseObserver).should(timeout(1)).onCompleted();

    }

    private Url makeSimpleUrl() {
        return Url.newBuilder()
                .setClickTime(Timestamp.newBuilder())
                .setShortUrl("O1")
                .setReferer("https://www.nexters.com")
                .setPlatform("Mobile")
                .build();
    }

}