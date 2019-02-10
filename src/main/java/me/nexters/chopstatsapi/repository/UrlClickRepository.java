package me.nexters.chopstatsapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.repository.mapper.UrlClickMapper;

/**
 * @author manki.kim
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UrlClickRepository {
	private final UrlClickMapper urlClickMapper;
}
