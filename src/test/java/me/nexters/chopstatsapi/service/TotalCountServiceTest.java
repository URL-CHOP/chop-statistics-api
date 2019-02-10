package me.nexters.chopstatsapi.service;

import me.nexters.chopstatsapi.domain.TotalCountVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TotalCountServiceTest {
    @Autowired
    TotalCountService totalCountService;

    @Test
    public void getTotalCountByShortUrl() {
        TotalCountVO totalCountVO = totalCountService.getTotalCountByShortUrl("Zb");
        System.out.println(totalCountVO.getTotal_count());
    }
}