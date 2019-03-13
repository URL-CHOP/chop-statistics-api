package me.nexters.chopstatsapi.grpc.util;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class DomainParserTest {

    @Test
    public void shouldGetUserAgent(){
        // given
        String referrer = "";
        String userAgent = "Mozilla/5.0 (Linux; Android 8.0.0; SM-G960F Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.84 Mobile Safari/537.36";

        // when
        String actualAgent  = DomainParser.getDomain(referrer, userAgent);

        //then
        Assert.assertThat(actualAgent,is("Mobile"));
    }

}