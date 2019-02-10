package me.nexters.chopstatsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.domain.TotalCountVO;
import me.nexters.chopstatsapi.repository.mapper.TotalCountMapper;

/**
 * @author junho.park
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class TotalCountService {
    private final TotalCountMapper totalCountMapper;

    public TotalCountVO getTotalCountByShortUrl(String shortUrl) {
        return totalCountMapper.getTotalCountByShortUrl(shortUrl);
    }
}
