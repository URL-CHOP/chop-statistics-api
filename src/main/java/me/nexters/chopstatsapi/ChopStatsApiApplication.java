package me.nexters.chopstatsapi;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ChopStatsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChopStatsApiApplication.class, args);
    }
}

