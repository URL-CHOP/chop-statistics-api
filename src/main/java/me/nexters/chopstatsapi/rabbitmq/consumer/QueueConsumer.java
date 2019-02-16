package me.nexters.chopstatsapi.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author manki.kim
 */
@Slf4j
@Component
public class QueueConsumer  {

	@RabbitListener(containerFactory = "listenerContainerFactory", queues = "#{clickDateQueue.name}")
	public void consumeClickDate(Object message) {
		log.info("consume : {}", message);
	}

	@RabbitListener(containerFactory = "listenerContainerFactory", queues = "#{platformCountQueue.name}")
	public void consumePlatformCount(Object message) {
		log.info("consume platform : {}", message);
	}

	@RabbitListener(containerFactory = "listenerContainerFactory", queues = "#{referrerCountQueue.name}")
	public void consumeReferrerCount(Object message) {
		log.info("consume referrer : {}", message);
	}

	@RabbitListener(containerFactory = "listenerContainerFactory", queues = "#{totalCountQueue.name}")
	public void consumeTotalCount(Object message) {
		log.info("consume total : {}", message);
	}
}
