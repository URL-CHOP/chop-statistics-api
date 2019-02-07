package me.nexters.chopstatsapi.service;

import me.nexters.chopstatsapi.domain.RefererVO;
import me.nexters.chopstatsapi.mapper.RefererMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author junho.park
 */
@Service
@MapperScan({"me.nexters.chopstatsapi.mapper"})
public class RefererService {
    @Autowired
    private RefererMapper refererMapper;

    public List<RefererVO> findRefererByShortUrl(String shortUrl) {
        return refererMapper.findRefererByShortUrl(shortUrl);
    }
}
