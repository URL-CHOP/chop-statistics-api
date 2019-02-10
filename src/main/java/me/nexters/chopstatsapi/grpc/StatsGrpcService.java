package me.nexters.chopstatsapi.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.domain.PlatformVO;
import me.nexters.chopstatsapi.domain.RefererVO;
import me.nexters.chopstatsapi.domain.TotalCountVO;
import me.nexters.chopstatsapi.service.PlatformService;
import me.nexters.chopstatsapi.service.RefererService;
import me.nexters.chopstatsapi.service.TotalCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author junho.park
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class StatsGrpcService extends UrlStatsServiceGrpc.UrlStatsServiceImplBase {
    private final PlatformService platformService;
    private final RefererService refererService;
    private final TotalCountService totalCountService;

    @Override
    public void getPlatformCount(UrlStatsRequest request, StreamObserver<Platform> responseObserver) {
        String shortenUrl = request.getShortUrl();

        if (shortenUrl == null) {
            responseObserver.onError(Status.INTERNAL.withDescription("shortenURL null").asException());
        }

        PlatformVO platformVO = platformService.getPlatformByShortUrl(shortenUrl);

        Platform platform = Platform.newBuilder()
                .setShortUrl(shortenUrl)
                .setBrowser(platformVO.getBrowser())
                .setMobile(platformVO.getMobile())
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

        List<RefererVO> refererVOList = refererService.getRefererByShortUrl(shortenUrl);

        for (RefererVO refererVO : refererVOList) {
            Referer referer = Referer.newBuilder()
                    .setShortUrl(shortenUrl)
                    .setReferer(refererVO.getReferer())
                    .setCount(refererVO.getCount())
                    .build();

            responseObserver.onNext(referer);
        }

        responseObserver.onCompleted();
    }

    @Override
    public void getTotalCount(UrlStatsRequest request, StreamObserver<TotalCount> responseObserver) {
        String shortenUrl = request.getShortUrl();

        if (shortenUrl == null) {
            responseObserver.onError(Status.INTERNAL.withDescription("shortenURL null").asException());
        }

        TotalCountVO totalCountVO = totalCountService.getTotalCountByShortUrl(shortenUrl);

        TotalCount totalCount = TotalCount.newBuilder()
                .setShortUrl(shortenUrl)
                .setTotalCount(totalCountVO.getTotal_count())
                .build();

        responseObserver.onNext(totalCount);
        responseObserver.onCompleted();
    }
}
