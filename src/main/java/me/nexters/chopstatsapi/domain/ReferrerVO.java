package me.nexters.chopstatsapi.domain;

import lombok.Getter;
import org.apache.ibatis.type.Alias;

/**
 * @author junho.park
 */
@Alias("referer_count")
@Getter
public class ReferrerVO {
    private String referer;
    private int count;
}
