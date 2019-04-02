package me.nexters.chopstatsapi.mapper;


import me.nexters.chopstatsapi.repository.mapper.PlatformMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

/**
 * @author chulwoon.jang
 */

@Disabled
@MybatisTest
@SpringBootTest
@AutoConfigureTestDatabase(connection = H2)
public class PlatformMapperTest {

    @Autowired
    private PlatformMapper platformMapper;

    @Test
    public void test() {
        System.out.println(platformMapper.getPlatformByShortUrl("Zb"));
    }
}
