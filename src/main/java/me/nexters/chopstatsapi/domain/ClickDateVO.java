package me.nexters.chopstatsapi.domain;

import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * @author junho.park
 */
@Alias("total_count")
@Getter
public class ClickDateVO {
    private String shortUrl;
    private LocalDateTime clickTime;
}
