package me.nexters.chopstatsapi.rabbitmq;

/**
 * @author manki.kim
 */
public enum QueueManager {
	CLICK_DATE("click-date", "click_date");

	private String name;
	private String routingKey;

	QueueManager(String name, String routingKey) {
		this.name = name;
		this.routingKey = routingKey;
	}

	public String getQueueName() {
		return this.name;
	}

	public String getRoutingKey() {
		return this.routingKey;
	}
}
