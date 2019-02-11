package me.nexters.chopstatsapi.repository.mapper;

import me.nexters.chopstatsapi.domain.ClickDateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author junho.park
 */
@Repository
@Mapper
public interface ClickDateMapper {
    @Select("SELECT DATE_FORMAT(click_time, '%Y-%m-%d') AS click_date, count(*) AS count " +
            "FROM click_date " +
            "WHERE short_url = #{shortUrl} " +
            "GROUP BY DATE(click_time) " +
            "AND click_date > (NOW() - INTERVAL #{week} WEEK)")
    List<ClickDateVO> getClickDatePerWeekByShortUrl(@Param("shortUrl") String shortUrl, @Param("week") int week);
}
