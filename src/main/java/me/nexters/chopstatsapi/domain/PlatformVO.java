package me.nexters.chopstatsapi.domain;

import lombok.Getter;
import org.apache.ibatis.type.Alias;

/**
 * @author junho.park
 */
@Alias("platform_count")
@Getter
public class PlatformVO {
    private String shortUrl;
    private int mobile;
    private int browser;
}
