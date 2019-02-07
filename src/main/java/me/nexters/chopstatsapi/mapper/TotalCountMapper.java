package me.nexters.chopstatsapi.mapper;

import me.nexters.chopstatsapi.domain.TotalCountVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author junho.park
 */
@Mapper
public interface TotalCountMapper {
    @Select("SELECT * FROM total_count WHERE short_url = #{shortUrl}")
    TotalCountVO findTotalCountByShortUrl(@Param("shortUrl") String shortUrl);
}
