package me.nexters.chopstatsapi.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.LinkedList;
import java.util.List;

/**
 * @author junho.park
 */
public class StatsService extends UrlStatsServiceGrpc.UrlStatsServiceImplBase {

    @Override
    public void getPlatformCount(UrlStatsRequest request, StreamObserver<Platform> responseObserver) {
        String shortenUrl = request.getShortUrl();

        if (shortenUrl == null) {
            responseObserver.onError(Status.INTERNAL.withDescription("shortenURL null").asException());
        }

        // TODO now hardcoded (mybatis 로 디비 접근 구현)
        Platform platform = Platform.newBuilder()
                .setShortUrl(shortenUrl)
                .setBrowser(3)
                .setMobile(2)
                .build();


        responseObserver.onNext(platform);
        responseObserver.onCompleted();
    }

    @Override
    public void getRefererCount(UrlStatsRequest request, StreamObserver<Referer> responseObserver) {
        String shortenUrl = request.getShortUrl();

        if (shortenUrl == null) {
            responseObserver.onError(Status.INTERNAL.withDescription("shortenURL null").asException());
        }

        // TODO now hardcoded (mybatis 로 디비 접근 구현)
        List<String> refererList = new LinkedList<>();
        List<Integer> countList = new LinkedList<>();

        refererList.add("https://naver.com");
        refererList.add("https://facebook.com");
        countList.add(2);
        countList.add(3);

        Referer referer = Referer.newBuilder()
                .setShortUrl(shortenUrl)
                .addAllReferer(refererList)
                .addAllCount(countList)
                .build();

        responseObserver.onNext(referer);
        responseObserver.onCompleted();
    }

    @Override
    public void getTotalCount(UrlStatsRequest request, StreamObserver<TotalCount> responseObserver) {
        String shortenUrl = request.getShortUrl();

        if (shortenUrl == null) {
            responseObserver.onError(Status.INTERNAL.withDescription("shortenURL null").asException());
        }

        // TODO now hardcoded (mybatis 로 디비 접근 구현)
        TotalCount totalCount = TotalCount.newBuilder()
                .setShortUrl(shortenUrl)
                .setTotalCount(3)
                .build();

        responseObserver.onNext(totalCount);
        responseObserver.onCompleted();
    }
}
