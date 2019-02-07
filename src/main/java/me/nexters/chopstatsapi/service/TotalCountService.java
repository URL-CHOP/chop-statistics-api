package me.nexters.chopstatsapi.service;

import me.nexters.chopstatsapi.domain.TotalCountVO;
import me.nexters.chopstatsapi.mapper.TotalCountMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author junho.park
 */
@Service
@MapperScan({"me.nexters.chopstatsapi.mapper"})
public class TotalCountService {
    @Autowired
    private TotalCountMapper totalCountMapper;

    public TotalCountVO findTotalCountByShortUrl(String shortUrl) {
        return totalCountMapper.findTotalCountByShortUrl(shortUrl);
    }
}
