package me.nexters.chopstatsapi.rabbitmq.exception;

import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author manki.kim
 */
@Slf4j
public class ExceptionStrategy implements FatalExceptionStrategy {
	@Override
	public boolean isFatal(Throwable t) {

		if (t instanceof RuntimeException) {
			log.error("fatal error");
		}
		return false;
	}
}
