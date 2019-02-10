package me.nexters.chopstatsapi.mapper;

import me.nexters.chopstatsapi.domain.ClickDateVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author junho.park
 */
@Mapper
public interface UrlClickMapper {
    @Insert("INSERT INTO click_count(short_url, click_time) VALUES(#{short_url}, #{click_time}")
    void insertClickTime(ClickDateVO clickDateVO);
}
