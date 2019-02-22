package me.nexters.chopstatsapi.grpc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author junho.park
 */
public class RefererUtil {
    private static final Pattern REFERER_REGEX = Pattern.compile("(//)([A-Z0-9a-zㄱ-ㅎㅏ-ㅣ가-힣:+&@#%?=~_|!:,.;-]+)");
    private static final int REFERER_GET = 2;

    public static String checkReferer (String referer) {
        Matcher matcher = REFERER_REGEX.matcher(referer);
        if (matcher.find())
            return matcher.group(REFERER_GET);

        return "mobile";
    }
}
