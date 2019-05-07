package me.nexters.chopstatsapi.grpc.util;

import com.google.protobuf.Timestamp;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author junho.park
 */
class DateParserTest {
    @Test
    public void protoBufTimeStamp_에서_localDate_로변환() {
        Instant time = Instant.now();
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                .setNanos(time.getNano()).build();

        LocalDate convertedLocalDate = DateParser.convertProtoBufTimeStampToLocalDate(timestamp);
        LocalDate actualLocalDate = LocalDateTime.ofInstant(time, ZoneOffset.UTC).toLocalDate();

        assertThat(actualLocalDate.equals(convertedLocalDate)).isTrue();
    }
}