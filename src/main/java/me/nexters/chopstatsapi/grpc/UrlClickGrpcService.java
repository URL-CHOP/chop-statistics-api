package me.nexters.chopstatsapi.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.repository.UrlClickRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author junho.park
 */
@Service
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

        String shortUrl = request.getShortUrl();

        // 비동기 확인 할 때는 이쪽에 Thread sleep 주면 됨
        // TODO Queue insert & referer, totalcount, platform
        urlClickRepository.insertClickTime(shortUrl, new Date());

        // TODO platform 정규식
        //urlClickService.insertPlatform(shortUrl, request.getPlatform());

        // TODO referer 정규식
        //urlClickService.insertReferer(shortUrl, request.getReferer());

        urlClickRepository.insertTotalCount(shortUrl);

        Success success = Success.newBuilder().setMessage("save success : " + request.getShortUrl()).build();

        responseObserver.onNext(success);
        responseObserver.onCompleted();
    }
}
