package me.nexters.chopstatsapi.domain;

import lombok.Getter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * @author junho.park
 */
@Alias("platform_count")
@Getter
@ToString
public class PlatformVO {
    private int mobile;
    private int browser;
}
