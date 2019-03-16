package me.nexters.chopstatsapi.grpc.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author junho.park
 */
@UtilityClass
public class PlatformUtil {
    private static final int PLATFORM_GET = 1;
    private static final Pattern PLATFORM_REGEX = Pattern.compile("(Mobile)");
    private static final String BROWSER = "Browser";

    public static String checkMobile(String platform) {
        Matcher matcher = PLATFORM_REGEX.matcher(platform);
        if (matcher.find()) {
            return matcher.group(PLATFORM_GET);
        }
        return BROWSER;
    }
}
