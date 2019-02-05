package me.nexters.chopstatsapi;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import me.nexters.chopstatsapi.grpc.StatsService;
import me.nexters.chopstatsapi.grpc.UrlClickService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChopStatsApiApplication implements ApplicationRunner {

    public static Logger logger = LoggerFactory.getLogger(ChopStatsApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ChopStatsApiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Server server = ServerBuilder
                .forPort(6565)
                .addService(new UrlClickService())
                .addService(new StatsService())
                .build();

        server.start();
        logger.info("gRPC server running!");
        server.awaitTermination();
    }
}

