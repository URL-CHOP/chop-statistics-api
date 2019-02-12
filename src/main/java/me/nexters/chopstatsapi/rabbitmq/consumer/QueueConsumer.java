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
	public void consume(Object message) {
		log.info("consume : {}", message);
	}
}
