package me.nexters.chopstatsapi.repository;

import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.repository.mapper.UrlClickMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @author manki.kim
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UrlClickRepository {
	private final UrlClickMapper urlClickMapper;

	public void  insertTotalCount(String shortUrl) {
		urlClickMapper.insertTotalCount(shortUrl);
	}

	public void insertClickTime(String shortUrl, LocalDateTime clickTime) {
		urlClickMapper.insertClickTime(shortUrl, clickTime);
	}

	public void insertPlatform(String shortUrl, String platform) {
		if (platform.equals("Mobile")) {
			urlClickMapper.insertMobilePlatform(shortUrl);
		} else if (platform.equals("Browser")) {
			urlClickMapper.insertBrowserPlatform(shortUrl);
		}
	}

	public void insertReferer(String shortUrl, String referer) {
		urlClickMapper.insertReferer(shortUrl, referer);
	}
}
