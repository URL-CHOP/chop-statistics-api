package me.nexters.chopstatsapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlatformServiceTest {
    @Autowired
    PlatformService platformService;

    @Test
    public void getPlatformByShortUrl() {
        System.out.println(platformService.getPlatformByShortUrl("Zb"));
    }
}