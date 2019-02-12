package me.nexters.chopstatsapi.repository;

import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.domain.TotalCountVO;
import me.nexters.chopstatsapi.repository.mapper.TotalCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author junho.park
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class TotalCountRepository {
    private final TotalCountMapper totalCountMapper;

    public TotalCountVO getTotalCountByShortUrl(String shortUrl) {
        return totalCountMapper.getTotalCountByShortUrl(shortUrl);
    }
}
