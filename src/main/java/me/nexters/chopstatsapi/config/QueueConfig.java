package me.nexters.chopstatsapi.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.nexters.chopstatsapi.rabbitmq.QueueManager;

/**
 * @author manki.kim
 */
@Configuration
public class QueueConfig {

	public static final String EXCHANGE_NAME = "stats-exchange";

	/**
	 * RabbitMQ에서 사용할 Exchange를 선언하고 반환한다.
	 * Exchange 종류는 Topic 사용
	 *
	 * @return 초기화된 TopicExchange 객체
	 */
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	/**
	 * RabbitMQ에서 사용할 Queue를 선언하고 반환한다.
	 *
	 * @return 초기화된 Queue 객체
	 */
	@Bean
	public Queue clickDateQueue() {
		return new Queue(QueueManager.CLICK_DATE.getQueueName(), true);
	}

	/**
	 * Exchange와 Queue를 연결하는 Binding 객체를 선언하고 반환한다.
	 */
	@Bean
	public Binding clickDateBinding(Queue clickDateQueue, TopicExchange exchange) {
		return BindingBuilder.bind(clickDateQueue).to(exchange).with(
			QueueManager.CLICK_DATE.getRoutingKey());
	}

	@Bean
	public Queue platformCountQueue() {
		return new Queue(QueueManager.PLATFORM_COUNT.getQueueName(), true);
	}

	@Bean
	public Binding platformCountBinding(Queue platformCountQueue, TopicExchange exchange) {
		return BindingBuilder.bind(platformCountQueue).to(exchange).with(
			QueueManager.PLATFORM_COUNT.getRoutingKey());
	}

	@Bean
	public Queue referrerCountQueue() {
		return new Queue(QueueManager.REFERRER_COUNT.getQueueName(), true);
	}

	@Bean
	public Binding referrerCountBinding(Queue referrerCountQueue, TopicExchange exchange) {
		return BindingBuilder.bind(referrerCountQueue).to(exchange).with(
			QueueManager.REFERRER_COUNT.getRoutingKey());
	}

	@Bean
	public Queue totalCountQueue() {
		return new Queue(QueueManager.TOTAL_COUNT.getQueueName(), true);
	}

	@Bean
	public Binding totalCountBinding(Queue totalCountQueue, TopicExchange exchange) {
		return BindingBuilder.bind(totalCountQueue).to(exchange).with(
			QueueManager.TOTAL_COUNT.getRoutingKey());
	}
}
