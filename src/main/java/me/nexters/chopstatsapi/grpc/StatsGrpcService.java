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
import java.util.stream.Collectors;

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

        List<RefererVO> refererVOList = refererService.findRefererByShortUrl(shortenUrl);
        List<String> refererList = getRefererListFromRefererCount(refererVOList);
        List<Integer> countList = getCountListFromRefererCount(refererVOList);

        System.out.println(refererList);
        System.out.println(countList);

        Referer referer = Referer.newBuilder()
                .setShortUrl(shortenUrl)
                .addAllReferer(refererList)
                .addAllCount(countList)
                .build();

        responseObserver.onNext(referer);
        responseObserver.onCompleted();
    }

    private List<Integer> getCountListFromRefererCount(List<RefererVO> refererCountList) {
        return refererCountList.stream().map(RefererVO::getCount).collect(Collectors.toList());
    }

    private List<String> getRefererListFromRefererCount(List<RefererVO> refererCountList) {
        return refererCountList.stream().map(RefererVO::getReferer).collect(Collectors.toList());
    }

    @Override
    public void getTotalCount(UrlStatsRequest request, StreamObserver<TotalCount> responseObserver) {
        String shortenUrl = request.getShortUrl();

        if (shortenUrl == null) {
            responseObserver.onError(Status.INTERNAL.withDescription("shortenURL null").asException());
        }

        TotalCountVO totalCountVO = totalCountService.findTotalCountByShortUrl(shortenUrl);

        TotalCount totalCount = TotalCount.newBuilder()
                .setShortUrl(totalCountVO.getShort_url())
                .setTotalCount(totalCountVO.getTotal_count())
                .build();

        responseObserver.onNext(totalCount);
        responseObserver.onCompleted();
    }
}
