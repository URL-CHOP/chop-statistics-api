package me.nexters.chopstatsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.domain.RefererVO;
import me.nexters.chopstatsapi.repository.mapper.RefererMapper;

/**
 * @author junho.park
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class RefererService {
    private final RefererMapper refererMapper;

    public List<RefererVO> getRefererByShortUrl(String shortUrl) {
        return refererMapper.getRefererByShortUrl(shortUrl);
    }
}
