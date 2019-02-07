package me.nexters.chopstatsapi.mapper;

import me.nexters.chopstatsapi.domain.PlatformVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author junho.park
 */
@Mapper
public interface PlatformMapper {
    @Select("SELECT * FROM platform_count WHERE short_url = #{shortUrl}")
    PlatformVO findPlatformByShortUrl(@Param("shortUrl") String shortUrl);
}
