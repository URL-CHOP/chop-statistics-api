package me.nexters.chopstatsapi.repository.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author junho.park
 */
@Repository
@Mapper
public interface UrlClickMapper {
    @Insert("INSERT INTO click_date(short_url, click_time) " +
            "VALUES (#{param1}, #{param2})")
    void insertClickTime(String shortUrl, LocalDateTime date);

    @Insert("INSERT INTO platform_count(short_url, mobile, browser) " +
            "VALUES (#{param1}, 1, 0)" +
            "ON DUPLICATE KEY UPDATE mobile = mobile + 1")
    void insertMobilePlatform(String shortUrl);

    @Insert("INSERT INTO platform_count(short_url, mobile, browser) " +
            "VALUES (#{param1}, 0, 1)" +
            "ON DUPLICATE KEY UPDATE browser = browser + 1")
    void insertBrowserPlatform(String shortUrl);

    @Insert("INSERT INTO referer_count(short_url, referer, count)" +
            "VALUES (#{param1}, #{param2}, 1)" +
            "ON DUPLICATE KEY UPDATE count = count + 1")
    void insertReferer(String shortUrl, String referer);

    @Insert("INSERT INTO total_count(short_url, total_count)" +
            "VALUES (#{param1}, 1)" +
            "ON DUPLICATE KEY UPDATE total_count = total_count + 1")
    void insertTotalCount(String shortUrl);
}
