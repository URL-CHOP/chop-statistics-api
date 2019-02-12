package me.nexters.chopstatsapi.service;

import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.repository.mapper.UrlClickMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author junho.park
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UrlClickService {
    private final UrlClickMapper urlClickMapper;

    public void  insertTotalCount(String shortUrl) {
        urlClickMapper.insertTotalCount(shortUrl);
    }

    public void insertClickTime(String shortUrl, Date clickTime) {
        urlClickMapper.insertClickTime(shortUrl, clickTime);
    }

    public void insertPlatform(String shortUrl, String platform) {
        if (platform.equals("mobile")) {
            urlClickMapper.insertMobilePlatform(shortUrl);
        } else if (platform.equals("browser")) {
            urlClickMapper.insertBrowserPlatform(shortUrl);
        }
    }

    public void insertReferer(String shortUrl, String referer) {
        urlClickMapper.insertReferer(shortUrl, referer);
    }
}
