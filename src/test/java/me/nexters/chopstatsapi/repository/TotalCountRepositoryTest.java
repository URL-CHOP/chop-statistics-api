package me.nexters.chopstatsapi.repository;

import me.nexters.chopstatsapi.domain.TotalCountVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author junho.park
 */
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
