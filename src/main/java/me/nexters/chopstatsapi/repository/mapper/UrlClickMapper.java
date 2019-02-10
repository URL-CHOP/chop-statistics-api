package me.nexters.chopstatsapi.repository.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import me.nexters.chopstatsapi.domain.ClickDateVO;

/**
 * @author junho.park
 */
@Repository
@Mapper
public interface UrlClickMapper {
    @Insert("INSERT INTO click_count(short_url, click_time) VALUES(#{short_url}, #{click_time}")
    void insertClickTime(ClickDateVO clickDateVO);
}
