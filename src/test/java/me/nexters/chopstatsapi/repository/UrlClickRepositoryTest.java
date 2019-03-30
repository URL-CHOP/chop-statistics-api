package me.nexters.chopstatsapi.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @author junho.park
 */
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
