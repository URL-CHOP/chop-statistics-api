package me.nexters.chopstatsapi.domain;

import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

/**
 * @author junho.park
 */
@Alias("click_date")
@Getter
public class ClickDateVO {
    private Date clickDate;
    private int count;
}
