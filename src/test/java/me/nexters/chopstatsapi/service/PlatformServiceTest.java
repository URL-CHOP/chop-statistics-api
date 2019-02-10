package me.nexters.chopstatsapi.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author junho.park
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class PlatformServiceTest {
    @Autowired
    PlatformService platformService;

    @Test
    public void getPlatformByShortUrl() {

    }
}