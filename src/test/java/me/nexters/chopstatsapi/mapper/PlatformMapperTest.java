package me.nexters.chopstatsapi.mapper;


import me.nexters.chopstatsapi.repository.mapper.PlatformMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author junho.park
 */

@SpringBootTest
public class PlatformMapperTest {

    @Autowired
    private PlatformMapper platformMapper;

    @Test
    public void test() {
        System.out.println(platformMapper.getPlatformByShortUrl("Zb"));
    }
}
