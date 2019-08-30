package me.nexters.chopstatsapi.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.nexters.chopstatsapi.rabbitmq.model.ClickDateCount;
import me.nexters.chopstatsapi.rabbitmq.model.PlatformCount;
import me.nexters.chopstatsapi.rabbitmq.model.RefererCount;
import me.nexters.chopstatsapi.rabbitmq.model.TotalCount;
import me.nexters.chopstatsapi.repository.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author manki.kim
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class QueueConsumer  {
	private final UrlClickRepository urlClickRepository;

	@RabbitListener(containerFactory = "listenerContainerFactory", queues = "#{clickDateQueue.name}")
	public void consumeClickDate(ClickDateCount clickDateCount) {
		log.info("consume clickdate : {}", clickDateCount);
		LocalDateTime dt = LocalDateTime.ofEpochSecond(clickDateCount.getDateSeconds(), clickDateCount.getDateNanos(), ZoneOffset.MAX);
		urlClickRepository.insertClickTime(clickDateCount.getShortUrl(), dt);
	}

	@RabbitListener(containerFactory = "listenerContainerFactory", queues = "#{platformCountQueue.name}")
	public void consumePlatformCount(PlatformCount platformCount) {
		log.info("consume platform : {}", platformCount);
		urlClickRepository.insertPlatform(platformCount.getShortUrl(), platformCount.getPlatform());
	}

	@RabbitListener(containerFactory = "listenerContainerFactory", queues = "#{referrerCountQueue.name}")
	public void consumeReferrerCount(RefererCount refererCount) {
		log.info("consume referrer : {}", refererCount);
		urlClickRepository.insertReferer(refererCount.getShortUrl(), refererCount.getReferer());
	}

	@RabbitListener(containerFactory = "listenerContainerFactory", queues = "#{totalCountQueue.name}")
	public void consumeTotalCount(TotalCount totalCount) {
		log.info("consume total : {}", totalCount);
		urlClickRepository.insertTotalCount(totalCount.getShortUrl());
	}
}
