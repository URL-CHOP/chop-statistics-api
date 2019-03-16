package me.nexters.chopstatsapi.grpc.util;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author junho.park
 */
@UtilityClass
public class DomainParser {
    // 영어 or 한글로 된 문자
    private static final Pattern REFERER_REGEX = Pattern.compile("http[s]?://(www\\.)?([\\d\\wㄱ-ㅎㅏ-ㅣ가-힣\\-]+[^\\/.])");
    private static final List<String> REFERER_CHECK_LIST = Lists.newArrayList("KAKAO", "LINE");
    private static final int FIRST_DOMAIN = 2;

    public static String getDomain(final String referer, final String userAgent) {
       return fromReferer(referer)
               .orElseGet(()-> fromUserAgent(userAgent));
    }

    private static Optional<String> fromReferer(final String referer) {
        Matcher matcher = REFERER_REGEX.matcher(referer);

        if (matcher.find()) {
            return Optional.ofNullable(matcher.group(FIRST_DOMAIN));
        }

        return Optional.empty();
    }

    private static String fromUserAgent(final String userAgent) {
        return REFERER_CHECK_LIST.stream()
                .filter(userAgent.toUpperCase()::contains)
                .findFirst()
                .orElseGet(() -> PlatformUtil.checkMobile(userAgent));
    }
}
