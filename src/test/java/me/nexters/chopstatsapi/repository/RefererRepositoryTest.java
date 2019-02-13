package me.nexters.chopstatsapi.repository;

import me.nexters.chopstatsapi.domain.RefererVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RefererRepositoryTest {
    @Autowired
    RefererRepository refererRepository;

    @Test
    public void getRefererByShortUrl() {
        List<RefererVO> refererVOList = refererRepository.getRefererByShortUrl("a");
        for (RefererVO refererVO : refererVOList) {
            assertThat(refererVO.getReferer()).isNotEmpty();
            assertThat(refererVO.getCount()).isNotNull();
        }
    }
}
