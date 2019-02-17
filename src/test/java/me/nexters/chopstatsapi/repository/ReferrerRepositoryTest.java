package me.nexters.chopstatsapi.repository;

import me.nexters.chopstatsapi.domain.ReferrerVO;
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
public class ReferrerRepositoryTest {
    @Autowired
    ReferrerRepository referrerRepository;

    @Test
    public void getRefererByShortUrl() {
        List<ReferrerVO> referrerVOList = referrerRepository.getRefererByShortUrl("a");
        for (ReferrerVO referrerVO : referrerVOList) {
            assertThat(referrerVO.getReferer()).isNotEmpty();
            assertThat(referrerVO.getCount()).isNotNull();
        }
    }
}
