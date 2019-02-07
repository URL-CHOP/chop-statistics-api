package me.nexters.chopstatsapi;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import me.nexters.chopstatsapi.grpc.StatsService;
import me.nexters.chopstatsapi.grpc.UrlClickService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChopStatsApiApplication implements ApplicationRunner {

    public static Logger logger = LoggerFactory.getLogger(ChopStatsApiApplication.class);
    @Autowired
    private StatsService statsService;
    @Autowired
    private UrlClickService urlClickService;

    public static void main(String[] args) {
        SpringApplication.run(ChopStatsApiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Server server = ServerBuilder
                .forPort(6565)
                .addService(urlClickService)
                .addService(statsService)
                .build();

        server.start();
        logger.info("gRPC server running!");
        server.awaitTermination();
    }
}

