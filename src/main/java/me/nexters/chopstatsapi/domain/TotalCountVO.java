package me.nexters.chopstatsapi.domain;

import lombok.Getter;
import org.apache.ibatis.type.Alias;

/**
 * @author junho.park
 */
@Alias("total_count")
@Getter
public class TotalCountVO {
    private String short_url;
    private int total_count;
}
