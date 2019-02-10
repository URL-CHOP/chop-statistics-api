package me.nexters.chopstatsapi.domain;

import lombok.Builder;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author junho.park
 */
@Alias("total_count")
@Getter
public class ClickDateVO {
    private String short_url;
    private Date click_time;

    @Builder
    public ClickDateVO(String short_url, Date click_time) {
        this.short_url = short_url;
        this.click_time = click_time;
    }
}
