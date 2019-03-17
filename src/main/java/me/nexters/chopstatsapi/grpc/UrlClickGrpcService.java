package me.nexters.chopstatsapi.grpc;

import com.google.protobuf.Timestamp;
import io.grpc.Context;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.nexters.chopstatsapi.grpc.util.PlatformUtil;
import me.nexters.chopstatsapi.grpc.util.DomainParser;
import me.nexters.chopstatsapi.rabbitmq.QueueManager;
import me.nexters.chopstatsapi.rabbitmq.model.ClickDateCount;
import me.nexters.chopstatsapi.rabbitmq.model.PlatformCount;
import me.nexters.chopstatsapi.rabbitmq.model.RefererCount;
import me.nexters.chopstatsapi.rabbitmq.model.TotalCount;
import me.nexters.chopstatsapi.rabbitmq.producer.Producer;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author junho.park
 */
@GRpcService
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class UrlClickGrpcService extends UrlClickServiceGrpc.UrlClickServiceImplBase {

    private final Producer producer;

    @Override
    public void unaryRecordCount(Url request, StreamObserver<Success> responseObserver) {
        if (Context.current().isCancelled()) {
            responseObserver.onError(Status.CANCELLED.withDescription("Cancelled by client").asRuntimeException());
            return;
        }

        log.info("shortUrl from client : {} ", request.getShortUrl());
        log.info("referrer from client : {}", request.getReferer());
        log.info("platform from client : {}", request.getPlatform());
        log.info("click time from client : {}", request.getClickTime());

        Timestamp timestamp = request.getClickTime();
        String shortUrl = request.getShortUrl();

        try {
            producer.enqueue(QueueManager.CLICK_DATE.getRoutingKey(),
                    new ClickDateCount(shortUrl, timestamp.getSeconds(), timestamp.getNanos()));
            producer.enqueue(QueueManager.PLATFORM_COUNT.getRoutingKey(),
                    new PlatformCount(shortUrl, PlatformUtil.checkMobile(request.getPlatform())));
            producer.enqueue(QueueManager.REFERRER_COUNT.getRoutingKey(),
                    new RefererCount(shortUrl, DomainParser.getDomain(request.getReferer(), request.getPlatform())));
            producer.enqueue(QueueManager.TOTAL_COUNT.getRoutingKey(),
                    new TotalCount(shortUrl));
        } catch (Exception e) {
            responseObserver.onError(new RuntimeException("queue produce error url : " + request.getShortUrl()));
            log.error("queue produce error url : {}, message : {}", request.getShortUrl(), e.getMessage());
        }

        Success success = Success.newBuilder().setMessage("save success : " + request.getShortUrl()).build();

        responseObserver.onNext(success);
        responseObserver.onCompleted();
    }
}
