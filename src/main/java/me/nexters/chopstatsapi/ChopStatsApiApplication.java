package me.nexters.chopstatsapi;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import me.nexters.chopstatsapi.grpc.StatsGrpcService;
import me.nexters.chopstatsapi.grpc.UrlClickGrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@EnableRabbit
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ChopStatsApiApplication implements ApplicationRunner {
    private static Logger logger = LoggerFactory.getLogger(ChopStatsApiApplication.class);
    private final StatsGrpcService statsService;
    private final UrlClickGrpcService urlClickService;

    public static void main(String[] args) {
        SpringApplication.run(ChopStatsApiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        new Thread(() -> {
            Server server = ServerBuilder
                    .forPort(6565)
                    .addService(urlClickService)
                    .addService(statsService)
                    .build();
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            logger.info("gRPC server running!");

            try {
                server.awaitTermination();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

