package me.nexters.chopstatsapi.repository;

import me.nexters.chopstatsapi.domain.TotalCountVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TotalCountRepositoryTest {
    @Autowired
    TotalCountRepository totalCountRepository;

    @Test
    public void getTotalCountByShortUrl() {
        TotalCountVO totalCountVO = totalCountRepository.getTotalCountByShortUrl("a");
        assertThat(totalCountVO.getTotalCount()).isNotNull();
    }
}
