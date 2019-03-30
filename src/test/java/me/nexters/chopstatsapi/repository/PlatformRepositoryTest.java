package me.nexters.chopstatsapi.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author junho.park
 */

@SpringBootTest
public class PlatformRepositoryTest {
    @Autowired
    PlatformRepository platformRepository;

    @Test
    public void getPlatformByShortUrl() {
        assertThat(platformRepository.getPlatformByShortUrl("a").getMobile()).isNotNull();
        assertThat(platformRepository.getPlatformByShortUrl("a").getBrowser()).isNotNull();
    }
}