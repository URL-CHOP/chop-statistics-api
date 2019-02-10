package me.nexters.chopstatsapi.grpc;

import static org.assertj.core.api.Assertions.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.testing.GrpcCleanupRule;


/**
 * @author junho.park
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UrlClickServiceTest {
    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    private ManagedChannel inProcessChannel;

    private Server server;

    @Before
    public void setUp() throws Exception {
        String serverName = UUID.randomUUID().toString();
        server = InProcessServerBuilder
                .forName(serverName)
                .directExecutor()
                .addService(new UrlClickGrpcService()).build();

        server.start();

        // 클라이언트 채널을 만들고 위에서 등록한 GrpcCleanupRule을 적용해 추후 셧다운을 시킨다.
        inProcessChannel = grpcCleanup.register(
                InProcessChannelBuilder.forName(serverName).directExecutor().build());
    }


    @Test
    public void unaryRecordCount() {
        Url url1 = Url.newBuilder()
                .setClickTime(100000)
                .setShortUrl("a")
                .setReferer("https://www.facebook.com")
                .setPlatform("mobile")
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
    }
}