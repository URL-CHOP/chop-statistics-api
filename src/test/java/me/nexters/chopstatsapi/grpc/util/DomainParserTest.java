package me.nexters.chopstatsapi.grpc.util;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DomainParserTest {

    @ParameterizedTest(name = "expect Mobile Agent")
    @CsvFileSource(resources = "/userAgentList.csv")
    public void shouldGetUserAgent(String agent, String expect){
        // when
        String actualAgent  = DomainParser.getDomain("", agent);

        //then
        assertThat(actualAgent,is(expect));
    }

    @ParameterizedTest(name = "expect {1} referrer")
    @CsvFileSource(resources = "/referrerList.csv")
    public void shouldGetReferrer(String referrer, String expect){
        // when
        String actualAgent  = DomainParser.getDomain(referrer, "");

        //then
        assertThat(actualAgent,is(expect));
    }

}