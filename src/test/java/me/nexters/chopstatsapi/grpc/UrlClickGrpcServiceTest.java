package me.nexters.chopstatsapi.grpc;

import com.google.protobuf.Timestamp;
import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.testing.GrpcCleanupRule;
import me.nexters.chopstatsapi.rabbitmq.producer.Producer;
import me.nexters.chopstatsapi.repository.UrlClickRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlClickGrpcServiceTest {
    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    private ManagedChannel inProcessChannel;

    private Server server;

    @Autowired
    private UrlClickRepository urlClickRepository;

    @Autowired
    private Producer producer;

    @Before
    public void setUp() throws Exception {
        String serverName = UUID.randomUUID().toString();
        server = InProcessServerBuilder
                .forName(serverName)
                .directExecutor()
                .addService(new UrlClickGrpcService(producer)).build();

        server.start();

        // 클라이언트 채널을 만들고 위에서 등록한 GrpcCleanupRule을 적용해 추후 셧다운을 시킨다.
        inProcessChannel = grpcCleanup.register(
                InProcessChannelBuilder.forName(serverName).directExecutor().build());
    }


    @Test
    public void unaryRecordCount() {
        long millis = System.currentTimeMillis();
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(millis / 1000)
                .setNanos((int) ((millis % 1000) * 1000000)).build();
        Url url1 = Url.newBuilder()
                .setClickTime(timestamp)
                .setShortUrl("O1")
                .setReferer("https://www.nexters.com")
                .setPlatform("Mobile")
                .build();

        UrlClickServiceGrpc.UrlClickServiceStub stub = UrlClickServiceGrpc.newStub(inProcessChannel);

        stub.unaryRecordCount(url1, new StreamObserver<Success>() {
            @Override
            public void onNext(Success success) {
                assertThat(success.getMessage()).isEqualTo("save success : " + "a");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("서버 응답 종료");
            }
        });

        sleep();

    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
    }
}