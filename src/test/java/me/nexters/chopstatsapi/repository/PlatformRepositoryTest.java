package me.nexters.chopstatsapi.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformRepositoryTest {
    @Autowired
    PlatformRepository platformRepository;

    @Test
    public void getPlatformByShortUrl() {
        System.out.println(platformRepository.getPlatformByShortUrl("Zb").getMobile());
    }
}