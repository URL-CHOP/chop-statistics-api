package me.nexters.chopstatsapi.rabbitmq.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author manki.kim
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ClickDateCount {
	private String shortUrl;
	private long dateSeconds;
}
