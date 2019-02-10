package me.nexters.chopstatsapi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import me.nexters.chopstatsapi.repository.mapper.PlatformMapper;

/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformMapperTest {

    @Autowired
    private PlatformMapper platformMapper;

    @Test
    public void test() {
        System.out.println(platformMapper.getPlatformByShortUrl("Zb"));
    }
}
