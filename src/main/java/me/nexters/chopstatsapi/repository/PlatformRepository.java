package me.nexters.chopstatsapi.repository;

import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.domain.PlatformVO;
import me.nexters.chopstatsapi.repository.mapper.PlatformMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author junho.park
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PlatformRepository {
    private final PlatformMapper platformMapper;

    public PlatformVO getPlatformByShortUrl(String shortUrl) {
        return platformMapper.getPlatformByShortUrl(shortUrl);
    }
}
