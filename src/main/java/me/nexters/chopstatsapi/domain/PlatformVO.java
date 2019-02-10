package me.nexters.chopstatsapi.domain;

import lombok.Builder;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

/**
 * @author junho.park
 */
@Alias("platform_count")
@Getter
public class PlatformVO {
    private String short_url;
    private int mobile;
    private int browser;

    @Builder
    public PlatformVO(String short_url, int mobile, int browser) {
        this.short_url = short_url;
        this.mobile = mobile;
        this.browser = browser;
    }
}
