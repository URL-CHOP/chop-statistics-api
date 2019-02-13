package me.nexters.chopstatsapi.grpc;

import com.google.protobuf.Timestamp;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.domain.ClickDateVO;
import me.nexters.chopstatsapi.domain.PlatformVO;
import me.nexters.chopstatsapi.domain.RefererVO;
import me.nexters.chopstatsapi.domain.TotalCountVO;
import me.nexters.chopstatsapi.repository.ClickDateRepository;
import me.nexters.chopstatsapi.repository.PlatformRepository;
import me.nexters.chopstatsapi.repository.RefererRepository;
import me.nexters.chopstatsapi.repository.TotalCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author junho.park
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class StatsGrpcService extends UrlStatsServiceGrpc.UrlStatsServiceImplBase {
    private final PlatformRepository platformRepository;
    private final RefererRepository refererRepository;
    private final TotalCountRepository totalCountRepository;
    private final ClickDateRepository clickDateRepository;

    @Override
    public void getPlatformCount(UrlStatsRequest request, StreamObserver<Platform> responseObserver) {
        String shortenUrl = request.getShortUrl();

        if (shortenUrl == null) {
            responseObserver.onError(Status.INTERNAL.withDescription("shortenURL null").asException());
        }

        PlatformVO platformVO = platformRepository.getPlatformByShortUrl(shortenUrl);

        Platform platform = Platform.newBuilder()
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

        List<RefererVO> refererVOList = refererRepository.getRefererByShortUrl(shortenUrl);

        for (RefererVO refererVO : refererVOList) {
            Referer referer = Referer.newBuilder()
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

        TotalCountVO totalCountVO = totalCountRepository.getTotalCountByShortUrl(shortenUrl);

        TotalCount totalCount = TotalCount.newBuilder()
                .setTotalCount(totalCountVO.getTotalCount())
                .build();

        responseObserver.onNext(totalCount);
        responseObserver.onCompleted();
    }

    @Override
    public void getClickCount(UrlClickStatsRequest request, StreamObserver<ClickCount> responseObserver) {
        String shortenUrl = request.getShortUrl();
        int week = request.getWeek();

        if (shortenUrl == null) {
            responseObserver.onError(Status.INTERNAL.withDescription("shortenURL null").asException());
        }

        List<ClickDateVO> clickDateVOList = clickDateRepository.getClickDatePerWeekByShortUrl(shortenUrl, week);

        for (ClickDateVO clickDateVO : clickDateVOList) {
            ClickCount clickCount = ClickCount.newBuilder()
                    .setDate(createTimeStampFromDate(clickDateVO.getClickDate()))
                    .setCount(clickDateVO.getCount())
                    .build();

            responseObserver.onNext(clickCount);
        }
        responseObserver.onCompleted();
    }

    private Timestamp createTimeStampFromDate(Date date) {
        return Timestamp.newBuilder().setSeconds(date.getTime()).build();
    }
}
