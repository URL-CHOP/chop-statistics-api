package me.nexters.chopstatsapi.grpc.util;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;
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

        String domainFromUserAgent = fromUserAgent(userAgent);
        if (Objects.nonNull(domainFromUserAgent)) {
            return domainFromUserAgent;
        }

        return "mobile";

    }


    private String fromReferer(String referer) {
        Matcher matcher = REFERER_REGEX.matcher(referer);
        if (matcher.find()) {
            return matcher.group(REFERER_GET).replaceAll("www\\.", "");
        }

        return null;
    }

    private String fromUserAgent (String userAgent) {
        String userAgentUpper = userAgent.toUpperCase();
        for (String platForm : REFERER_CHECK_LIST) {
            if (userAgentUpper.contains(platForm)) {
                return platForm;
            }
        }
        return null;
    }
}
