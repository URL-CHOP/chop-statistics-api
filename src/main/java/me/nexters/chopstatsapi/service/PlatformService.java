package me.nexters.chopstatsapi.service;

import me.nexters.chopstatsapi.domain.PlatformVO;
import me.nexters.chopstatsapi.mapper.PlatformMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author junho.park
 */
@Service
@MapperScan({"me.nexters.chopstatsapi.mapper"})
public class PlatformService {
    @Autowired
    private PlatformMapper platformMapper;

    public PlatformVO getPlatformByShortUrl(String shortUrl) {
        return platformMapper.findPlatformByShortUrl(shortUrl);
    }
}
