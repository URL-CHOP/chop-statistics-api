package me.nexters.chopstatsapi.repository;

import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.domain.ClickDateVO;
import me.nexters.chopstatsapi.repository.mapper.ClickDateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author junho.park
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ClickDateRepository {
    private final ClickDateMapper clickDateMapper;

    public List<ClickDateVO> getClickDatePerWeekByShortUrl(String shortUrl, int week) {
        return clickDateMapper.getClickDatePerWeekByShortUrl(shortUrl, week);
    }
}
