package me.nexters.chopstatsapi.grpc;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.grpc.util.PlatformUtil;
import me.nexters.chopstatsapi.grpc.util.RefererUtil;
import me.nexters.chopstatsapi.rabbitmq.QueueManager;
import me.nexters.chopstatsapi.rabbitmq.model.ClickDateCount;
import me.nexters.chopstatsapi.rabbitmq.model.PlatformCount;
import me.nexters.chopstatsapi.rabbitmq.model.RefererCount;
import me.nexters.chopstatsapi.rabbitmq.model.TotalCount;
import me.nexters.chopstatsapi.rabbitmq.producer.Producer;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author junho.park
 */
@GRpcService
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UrlClickGrpcService extends UrlClickServiceGrpc.UrlClickServiceImplBase {
    public static Logger logger = LoggerFactory.getLogger(UrlClickGrpcService.class);

    private final Producer producer;

    @Override
    public void unaryRecordCount(Url request, StreamObserver<Success> responseObserver) {
        logger.info("shortUrl from client : " + request.getShortUrl());
        logger.info("referrer from client : " + request.getReferer());
        logger.info("platform from client : " + request.getPlatform());
        logger.info("click time from client : " + request.getClickTime());
        Timestamp timestamp = request.getClickTime();

        String shortUrl = request.getShortUrl();

        producer.enqueue(QueueManager.CLICK_DATE.getRoutingKey(),
                new ClickDateCount(shortUrl, timestamp.getSeconds()));
        producer.enqueue(QueueManager.PLATFORM_COUNT.getRoutingKey(),
                new PlatformCount(shortUrl, PlatformUtil.checkMobile(request.getPlatform())));
        producer.enqueue(QueueManager.REFERRER_COUNT.getRoutingKey(),
                new RefererCount(shortUrl, RefererUtil.checkReferer(request.getReferer())));
        producer.enqueue(QueueManager.TOTAL_COUNT.getRoutingKey(),
                new TotalCount(shortUrl));

        Success success = Success.newBuilder().setMessage("save success : " + request.getShortUrl()).build();

        responseObserver.onNext(success);
        responseObserver.onCompleted();
    }
}
