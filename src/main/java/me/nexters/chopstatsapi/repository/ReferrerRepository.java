package me.nexters.chopstatsapi.repository;

import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.domain.ReferrerVO;
import me.nexters.chopstatsapi.repository.mapper.RefererMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author junho.park
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ReferrerRepository {
    private final RefererMapper refererMapper;

    public List<ReferrerVO> getRefererByShortUrl(String shortUrl) {
        return refererMapper.getRefererByShortUrl(shortUrl);
    }
}
