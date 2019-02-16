package me.nexters.chopstatsapi.grpc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author junho.park
 */
public class PlatformUtil {
    private static final int PLATFORM_GET = 1;
    private static final Pattern PLATFORM_REGEX = Pattern.compile("(Mobile)");

    public static String checkMobile(String platform) {
        Matcher matcher = PLATFORM_REGEX.matcher(platform);
        if (matcher.find())
            return matcher.group(PLATFORM_GET);

        return "Browser";
    }
}
