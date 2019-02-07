package me.nexters.chopstatsapi.mapper;

import me.nexters.chopstatsapi.domain.RefererVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author junho.park
 */
@Mapper
public interface RefererMapper {
    @Select("SELECT * FROM referer_count WHERE short_url = #{shortUrl}")
    List<RefererVO> findRefererByShortUrl(@Param("shortUrl") String shortUrl);
}
