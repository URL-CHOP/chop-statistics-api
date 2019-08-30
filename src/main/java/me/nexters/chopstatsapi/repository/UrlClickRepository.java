package me.nexters.chopstatsapi.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.nexters.chopstatsapi.repository.mapper.UrlClickMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @author manki.kim
 */
@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UrlClickRepository {
	private static final String TAG = "[UrlClickRepository]";

	private final UrlClickMapper urlClickMapper;

	public void insertTotalCount(String shortUrl) {
		urlClickMapper.insertTotalCount(shortUrl);
	}

	public void insertClickTime(String shortUrl, LocalDateTime clickTime) {
		try {
			urlClickMapper.insertClickTime(shortUrl, clickTime);
		} catch (DuplicateKeyException e) {
			log.error("{} click_date time 중복 >> {}", TAG, e.getMessage());
		} catch (Exception e) {
			log.error("{} click_date time Unknown error >> {}", TAG, e.getMessage());
		}
	}

	public void insertPlatform(String shortUrl, String platform) {
		if (platform.equals("Mobile")) {
			urlClickMapper.insertMobilePlatform(shortUrl);
			return;
		}
		urlClickMapper.insertBrowserPlatform(shortUrl);
	}

	public void insertReferer(String shortUrl, String referer) {
		urlClickMapper.insertReferer(shortUrl, referer);
	}
}
