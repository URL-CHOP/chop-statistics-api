package me.nexters.chopstatsapi.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import me.nexters.chopstatsapi.domain.RefererVO;

/**
 * @author junho.park
 */
@Repository
@Mapper
public interface RefererMapper {
    @Select("SELECT * FROM referer_count WHERE short_url = #{shortUrl}")
    List<RefererVO> getRefererByShortUrl(@Param("shortUrl") String shortUrl);
}
