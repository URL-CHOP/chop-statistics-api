package me.nexters.chopstatsapi.rabbitmq;

/**
 * @author manki.kim
 */
public enum QueueManager {
	CLICK_DATE("click-date-count", "click_date_count"),
	PLATFORM_COUNT("platform-count", "platform_count"),
	REFERRER_COUNT("referrer-count", "referrer_count"),
	TOTAL_COUNT("total-count", "total_count");

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
