package me.nexters.chopstatsapi.rabbitmq.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author manki.kim
 */
@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class Producer {

	private final RabbitTemplate rabbitTemplate;

	public void enqueue(String routingKey, Object message) {
		try {
			rabbitTemplate.convertAndSend(routingKey, message);
		} catch (AmqpException e) {
			log.error("error in produce {} ", routingKey, e);
		}
	}
}
