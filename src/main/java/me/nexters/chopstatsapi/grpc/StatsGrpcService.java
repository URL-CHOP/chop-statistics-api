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
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

/**
 * @author junho.park
 */
@GRpcService
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
            responseObserver.onError(Status.INTERNAL.withDescription("url이 null 입니다").asException());
            throw new NullPointerException("url이 null 입니다");
        }

        PlatformVO platformVO = platformRepository.getPlatformByShortUrl(shortenUrl);

        if (platformVO == null) {
            responseObserver.onError(Status.INTERNAL.withDescription("존재하지 않는 url 입니다").asException());
            throw new RuntimeException("존재하지 않는 url 입니다");
        }

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
            responseObserver.onError(Status.INTERNAL.withDescription("url이 null 입니다").asException());
            throw new NullPointerException("url이 null 입니다");
        }

        List<RefererVO> refererVOList = refererRepository.getRefererByShortUrl(shortenUrl);

        if (refererVOList.get(0) == null) {
            responseObserver.onError(Status.INTERNAL.withDescription("존재하지 않는 url 입니다").asException());
            throw new RuntimeException("존재하지 않는 url 입니다");
        }

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
            responseObserver.onError(Status.INTERNAL.withDescription("url이 null 입니다").asException());
            throw new NullPointerException("url이 null 입니다");
        }

        TotalCountVO totalCountVO = totalCountRepository.getTotalCountByShortUrl(shortenUrl);

        if (totalCountVO == null) {
            responseObserver.onError(Status.INTERNAL.withDescription("존재하지 않는 url 입니다").asException());
            throw new RuntimeException("존재하지 않는 url 입니다");
        }

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
            responseObserver.onError(Status.INTERNAL.withDescription("url이 null 입니다").asException());
            throw new NullPointerException("url이 null 입니다");
        }

        List<ClickDateVO> clickDateVOList = clickDateRepository.getClickDatePerWeekByShortUrl(shortenUrl, week);

        if (clickDateVOList.get(0) == null) {
            responseObserver.onError(Status.INTERNAL.withDescription("존재하지 않는 url 입니다").asException());
            throw new RuntimeException("존재하지 않는 url 입니다");
        }

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
