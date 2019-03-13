package me.nexters.chopstatsapi.grpc.util;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author junho.park
 */
@UtilityClass
public class DomainParser {
    private static final Pattern REFERER_REGEX = Pattern.compile("(//)([A-Z0-9a-zㄱ-ㅎㅏ-ㅣ가-힣:+&@#%?=~_|!:,.;-]+)");
    private static final int REFERER_GET = 2;
    private static final List<String> REFERER_CHECK_LIST = Lists.newArrayList("KAKAO", "LINE");

    public static String getDomain(String referer, String userAgent) {
        String domainFromReferer = fromReferer(referer);
        if (Objects.nonNull(domainFromReferer)) {
            return domainFromReferer;
        }

        return fromUserAgent(userAgent).orElse(PlatformUtil.checkMobile(userAgent));
    }


    private static String fromReferer(String referer) {
        Matcher matcher = REFERER_REGEX.matcher(referer);
        if (matcher.find()) {
            return matcher.group(REFERER_GET).replaceAll("www\\.", "");
        }

        return null;
    }

    private static Optional<String> fromUserAgent(String userAgent) {
        return REFERER_CHECK_LIST.stream()
                .filter(userAgent.toUpperCase()::contains)
                .findFirst();
    }
}
