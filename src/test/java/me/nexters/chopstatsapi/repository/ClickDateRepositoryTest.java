package me.nexters.chopstatsapi.repository;

import me.nexters.chopstatsapi.domain.ClickDateVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author junho.park
 */

@SpringBootTest
@Transactional
public class ClickDateRepositoryTest {
    @Autowired
    ClickDateRepository clickDateRepository;

    @Test
    public void getClickDatePerWeekByShortUrl() {
        List<ClickDateVO> list = clickDateRepository.getClickDatePerWeekByShortUrl("a", 1);

        for(ClickDateVO vo : list) {
            System.out.println(vo.getCount());
            System.out.println(vo.getClickDate());
        }
    }

    @Test
    public void getClickDatePerMonthByShortUrl() {
        List<ClickDateVO> list = clickDateRepository.getClickDatePerMonthByShortUrl("a", 1);

        for(ClickDateVO vo : list) {
            System.out.println(vo.getCount());
            System.out.println(vo.getClickDate());
        }
    }
}