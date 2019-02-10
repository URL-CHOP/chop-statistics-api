package me.nexters.chopstatsapi.domain;

import lombok.Builder;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

/**
 * @author junho.park
 */
@Alias("total_count")
@Getter
public class ClickDateVO {
    private String short_url;
    private long click_time;

    @Builder
    public ClickDateVO(String short_url, long click_time) {
        this.short_url = short_url;
        this.click_time = click_time;
    }
}
