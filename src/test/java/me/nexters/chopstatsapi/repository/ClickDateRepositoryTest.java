package me.nexters.chopstatsapi.repository;

import me.nexters.chopstatsapi.domain.ClickDateVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClickDateRepositoryTest {
    @Autowired
    ClickDateRepository clickDateRepository;

    @Test
    public void getClickDatePerWeekByShortUrl() {
        List<ClickDateVO> list = clickDateRepository.getClickDatePerWeekByShortUrl("Zb", 1);

        for(ClickDateVO vo : list) {
            System.out.println(vo.getCount());
            System.out.println(vo.getClickDate());
        }
    }
}