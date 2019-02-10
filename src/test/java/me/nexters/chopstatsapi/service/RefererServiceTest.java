package me.nexters.chopstatsapi.service;

import me.nexters.chopstatsapi.domain.RefererVO;
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
public class RefererServiceTest {
    @Autowired
    RefererService refererService;

    @Test
    public void getRefererByShortUrl() {
        List<RefererVO> refererVOList = refererService.getRefererByShortUrl("Zb");
        for (RefererVO refererVO : refererVOList) {
            System.out.println(refererVO.getReferer());
            System.out.println(refererVO.getCount());
        }
    }
}