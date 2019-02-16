package me.nexters.chopstatsapi.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlClickRepositoryTest {
    @Autowired
    UrlClickRepository urlClickRepository;

    @Test
    public void insertClickTime() {
        urlClickRepository.insertClickTime("a", LocalDateTime.now());
    }

    @Test
    public void insertTotalCount() {
        urlClickRepository.insertTotalCount("a");
    }

    @Test
    public void insertPlatform() {
        urlClickRepository.insertPlatform("a", "mobile");
        urlClickRepository.insertPlatform("a", "browser");
    }

    @Test
    public void insertReferer() {
        urlClickRepository.insertReferer("a", "https://google.com");
        urlClickRepository.insertReferer("a", "https://daum.net");
    }
}
