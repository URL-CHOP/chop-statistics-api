package me.nexters.chopstatsapi.grpc;

import com.google.protobuf.Timestamp;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.domain.ClickDateVO;
import me.nexters.chopstatsapi.domain.PlatformVO;
import me.nexters.chopstatsapi.domain.ReferrerVO;
import me.nexters.chopstatsapi.domain.TotalCountVO;
import me.nexters.chopstatsapi.grpc.error.UrlErrorHandler;
import me.nexters.chopstatsapi.repository.ClickDateRepository;
import me.nexters.chopstatsapi.repository.PlatformRepository;
import me.nexters.chopstatsapi.repository.ReferrerRepository;
import me.nexters.chopstatsapi.repository.TotalCountRepository;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Date;
import java.util.List;

/**
 * @author junho.park
 */
@GRpcService
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class StatsGrpcService extends UrlStatsServiceGrpc.UrlStatsServiceImplBase {
	private final PlatformRepository platformRepository;
	private final ReferrerRepository referrerRepository;
	private final TotalCountRepository totalCountRepository;
	private final ClickDateRepository clickDateRepository;

	@Override
	public void getPlatformCount(UrlStatsRequest request, StreamObserver<Platform> responseObserver) {
		PlatformVO platformVO = platformRepository.getPlatformByShortUrl(request.getShortUrl());
		if (platformVO == null) {
			throwNotFoundException(responseObserver);
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
		List<ReferrerVO> referrers = referrerRepository.getRefererByShortUrl(request.getShortUrl());
		if (CollectionUtils.isEmpty(referrers)) {
			throwNotFoundException(responseObserver);
		}

		referrers.stream()
			.map(referrerVO -> Referer.newBuilder()
				.setReferer(referrerVO.getReferer())
				.setCount(referrerVO.getCount())
				.build())
			.forEach(responseObserver::onNext);

		responseObserver.onCompleted();
	}

	@Override
	public void getTotalCount(UrlStatsRequest request, StreamObserver<TotalCount> responseObserver) {
		TotalCountVO totalCountVO = totalCountRepository.getTotalCountByShortUrl(request.getShortUrl());
		if (totalCountVO == null) {
			throwNotFoundException(responseObserver);
		}

		TotalCount totalCount = TotalCount.newBuilder()
			.setTotalCount(totalCountVO.getTotalCount())
			.build();

		responseObserver.onNext(totalCount);
		responseObserver.onCompleted();
	}

	@Override
	public void getClickCount(UrlClickStatsRequest request, StreamObserver<ClickCount> responseObserver) {
		List<ClickDateVO> clickDates = clickDateRepository.getClickDatePerWeekByShortUrl(request.getShortUrl(),
			request.getWeek());
		if (CollectionUtils.isEmpty(clickDates)) {
			throwNotFoundException(responseObserver);
		}

		clickDates.stream()
			.map(clickDateVO -> ClickCount.newBuilder()
				.setDate(createTimeStampFromDate(clickDateVO.getClickDate()))
				.setCount(clickDateVO.getCount())
				.build())
			.forEach(responseObserver::onNext);

		responseObserver.onCompleted();
	}

	private Timestamp createTimeStampFromDate(Date date) {
		return Timestamp.newBuilder().setSeconds(date.getTime()).build();
	}

	private void throwNotFoundException(StreamObserver response) {
		response.onError(Status.INTERNAL.withDescription(UrlErrorHandler.NOT_FOUND.getMessage()).asException());
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, UrlErrorHandler.NOT_FOUND.getMessage());
	}
}
