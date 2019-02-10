package me.nexters.chopstatsapi.grpc;

import io.grpc.stub.StreamObserver;
import me.nexters.chopstatsapi.domain.ClickDateVO;
import me.nexters.chopstatsapi.service.UrlClickService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author junho.park
 */
@Service
public class UrlClickGrpcService extends UrlClickServiceGrpc.UrlClickServiceImplBase {
    public static Logger logger = LoggerFactory.getLogger(UrlClickGrpcService.class);

    @Autowired
    private UrlClickService urlClickService;

    @Override
    public void unaryRecordCount(Url request, StreamObserver<Success> responseObserver) {
        logger.info("shortUrl from client : " + request.getShortUrl());
        logger.info("referrer from client : " + request.getReferer());
        logger.info("platform from client : " + request.getPlatform());
        logger.info("click time from client : " + request.getClickTime());

        // 비동기 확인 할 때는 이쪽에 Thread sleep 주면 됨
        // TODO Queue insert & referer, totalcount, platform
        urlClickService.insertClickTime(makeClickTimeVO(request.getShortUrl(), request.getClickTime()));

        Success success = Success.newBuilder().setMessage("save success : " + request.getShortUrl()).build();

        responseObserver.onNext(success);
        responseObserver.onCompleted();
    }

    private ClickDateVO makeClickTimeVO(String shortUrl, long clickTime) {
        return ClickDateVO.builder().short_url(shortUrl).click_time(clickTime).build();
    }
}
