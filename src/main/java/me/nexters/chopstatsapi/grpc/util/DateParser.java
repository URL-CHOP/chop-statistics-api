package me.nexters.chopstatsapi.grpc.util;

import com.google.protobuf.Timestamp;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * @author junho.park
 */
@UtilityClass
public class DateParser {
    public static LocalDate convertProtoBufTimeStampToLocalDate(Timestamp ts) {
        return Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos())
                .atZone(ZoneId.of("Asia/Seoul"))
                .toLocalDate();
    }
}
