package me.nexters.chopstatsapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlClickServiceTest {
    @Autowired
    UrlClickService urlClickService;

    @Test
    public void insertClickTime() {
        urlClickService.insertClickTime("Zb", new Date());
    }

    @Test
    public void insertTotalCount() {
        urlClickService.insertTotalCount("Zb");
    }

    @Test
    public void insertPlatform() {
        urlClickService.insertPlatform("Zb", "mobile");
        urlClickService.insertPlatform("Zb", "browser");
    }

    @Test
    public void insertReferer() {
        urlClickService.insertReferer("Zb", "https://google.com");
        urlClickService.insertReferer("Zb", "https://daum.net");
    }
}