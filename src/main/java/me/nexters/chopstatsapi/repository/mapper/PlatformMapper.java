package me.nexters.chopstatsapi.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import me.nexters.chopstatsapi.domain.PlatformVO;

/**
 * @author junho.park
 */
@Repository
@Mapper
public interface PlatformMapper {
    @Select("SELECT * FROM platform_count WHERE short_url = #{shortUrl}")
    PlatformVO findPlatformByShortUrl(@Param("shortUrl") String shortUrl);
}
