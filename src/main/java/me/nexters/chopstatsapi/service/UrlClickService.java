package me.nexters.chopstatsapi.service;

import me.nexters.chopstatsapi.domain.ClickDateVO;
import me.nexters.chopstatsapi.domain.PlatformVO;
import me.nexters.chopstatsapi.domain.RefererVO;
import me.nexters.chopstatsapi.domain.TotalCountVO;
import me.nexters.chopstatsapi.mapper.UrlClickMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author junho.park
 */
@Service
@MapperScan({"me.nexters.chopstatsapi.mapper"})
public class UrlClickService {
    @Autowired
    private UrlClickMapper urlClickMapper;

    public void insertClickTime(ClickDateVO clickDateVo) {
        urlClickMapper.insertClickTime(clickDateVo);
    }

    public void insertPlatform(PlatformVO platformVO) {
    }
    public void insertReferer(RefererVO refererVO) {
    }
    public void  insertTotalCount(TotalCountVO totalCountVO) {
    }
}
