package me.nexters.chopstatsapi;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import me.nexters.chopstatsapi.grpc.StatsGrpcService;
import me.nexters.chopstatsapi.grpc.UrlClickGrpcService;
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
    private StatsGrpcService statsService;
    @Autowired
    private UrlClickGrpcService urlClickService;

    public static void main(String[] args) {
        SpringApplication.run(ChopStatsApiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO 테스트 시 gRPC 서버 생성하는 부분에서 문제가 생김 (일단 주석처리)
//        Server server = ServerBuilder
//                .forPort(6565)
//                .addService(urlClickService)
//                .addService(statsService)
//                .build();
//
//        server.start();
//        logger.info("gRPC server running!");
//        server.awaitTermination();
    }
}

