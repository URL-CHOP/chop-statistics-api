package me.nexters.chopstatsapi.config;

import me.nexters.chopstatsapi.rabbitmq.exception.ExceptionStrategy;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.ErrorHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author manki.kim
 */
@Configuration
public class RabbitMqConfig {
	private static final String RABBITMQ_HOST = "211.249.63.227";
	private static final int RABBITMQ_PORT = 5672;

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, RetryTemplate retryTemplate) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setExchange(QueueConfig.EXCHANGE_NAME);
		template.setMessageConverter(messageConverter());
		template.setRetryTemplate(retryTemplate);
		return template;
	}

	@Bean
	public ExponentialBackOffPolicy exponentialBackOffPolicy() {
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(100);
		backOffPolicy.setMultiplier(2.0);
		backOffPolicy.setMaxInterval(1000);
		return backOffPolicy;
	}

	@Bean
	public RetryPolicy retryPolicy() {
		Map<Class<? extends Throwable>, Boolean> includeExceptions = new HashMap<>();
		includeExceptions.put(RuntimeException.class, true);
		return new SimpleRetryPolicy(2, includeExceptions);
	}

	@Bean
	public RetryTemplate retryTemplate(ExponentialBackOffPolicy exponentialBackOffPolicy, RetryPolicy retryPolicy) {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);
		retryTemplate.setRetryPolicy(retryPolicy);
		return retryTemplate;
	}

	/**
	 * RabbitMQ Server와의 Connection을 생성하는 Factory 객체를 반환한다.
	 *
	 * @return 초기화된 RabbitMQ ConnectionFactory 객체
	 */
	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory = new CachingConnectionFactory(RABBITMQ_HOST, RABBITMQ_PORT);
		((CachingConnectionFactory)connectionFactory).setUsername("nexters");
		((CachingConnectionFactory)connectionFactory).setPassword("nexters");
		return connectionFactory;
	}

	/**
	 * RabbitMQ에서 메시지를 수신할 Listener의 Factory를 선언하고 반환한다.
	 *
	 * @param connectionFactory ConnectionFactory 객체
	 * @param messageConverter Jackson Converter 객체. byte[] <-> 메시지 간 변환을 담당
	 * @return 초기화된 Listener Factory 객체
	 */
	@Bean(name = {"listenerContainerFactory"})
	public SimpleRabbitListenerContainerFactory containerFactory(ConnectionFactory connectionFactory,
		MessageConverter messageConverter) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(messageConverter);
		factory.setErrorHandler(errorHandler());
		return factory;
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public ErrorHandler errorHandler() {
		return new ConditionalRejectingErrorHandler(new ExceptionStrategy());
	}
}
