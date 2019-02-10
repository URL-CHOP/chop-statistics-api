package me.nexters.chopstatsapi.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import me.nexters.chopstatsapi.domain.TotalCountVO;

/**
 * @author junho.park
 */
@Repository
@Mapper
public interface TotalCountMapper {
    @Select("SELECT * FROM total_count WHERE short_url = #{shortUrl}")
    TotalCountVO findTotalCountByShortUrl(@Param("shortUrl") String shortUrl);
}
