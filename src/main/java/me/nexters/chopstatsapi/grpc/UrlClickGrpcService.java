package me.nexters.chopstatsapi.grpc;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.repository.UrlClickRepository;
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

    private final UrlClickRepository urlClickRepository;

    @Override
    public void unaryRecordCount(Url request, StreamObserver<Success> responseObserver) {
        logger.info("shortUrl from client : " + request.getShortUrl());
        logger.info("referrer from client : " + request.getReferer());
        logger.info("platform from client : " + request.getPlatform());
        logger.info("click time from client : " + request.getClickTime());
        Timestamp timestamp = request.getClickTime();

        LocalDateTime dt = LocalDateTime.ofEpochSecond(timestamp.getSeconds(), 0, ZoneOffset.MAX);
        String shortUrl = request.getShortUrl();

        // TODO Queue insert & referer, totalcount, platform
        urlClickRepository.insertClickTime(shortUrl, dt);

        // TODO platform 정규식 - 정규식 util 합치면 됨
        urlClickRepository.insertPlatform(shortUrl, "browser");
        // producer.enqueue(routingket, new

        // TODO referer 정규식
        urlClickRepository.insertReferer(shortUrl, request.getReferer());

        urlClickRepository.insertTotalCount(shortUrl);

        Success success = Success.newBuilder().setMessage("save success : " + request.getShortUrl()).build();

        responseObserver.onNext(success);
        responseObserver.onCompleted();
    }
}
