package me.nexters.chopstatsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.domain.ClickDateVO;
import me.nexters.chopstatsapi.domain.PlatformVO;
import me.nexters.chopstatsapi.domain.RefererVO;
import me.nexters.chopstatsapi.domain.TotalCountVO;
import me.nexters.chopstatsapi.repository.mapper.UrlClickMapper;

/**
 * @author junho.park
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UrlClickService {
    private final UrlClickMapper urlClickMapper;

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
