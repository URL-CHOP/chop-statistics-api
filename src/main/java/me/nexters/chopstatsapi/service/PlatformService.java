package me.nexters.chopstatsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.domain.PlatformVO;
import me.nexters.chopstatsapi.repository.mapper.PlatformMapper;

/**
 * @author junho.park
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PlatformService {
    private final PlatformMapper platformMapper;

    public PlatformVO getPlatformByShortUrl(String shortUrl) {
        return platformMapper.findPlatformByShortUrl(shortUrl);
    }
}
