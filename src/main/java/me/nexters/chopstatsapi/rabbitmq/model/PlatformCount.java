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
public class PlatformCount {
	private String shortUrl;
	private String platform;
}
