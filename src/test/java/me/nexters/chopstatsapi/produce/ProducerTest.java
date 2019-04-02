package me.nexters.chopstatsapi.produce;

import me.nexters.chopstatsapi.rabbitmq.QueueManager;
import me.nexters.chopstatsapi.rabbitmq.model.ClickDateCount;
import me.nexters.chopstatsapi.rabbitmq.model.PlatformCount;
import me.nexters.chopstatsapi.rabbitmq.producer.Producer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author manki.kim
 */

@Disabled
@SpringBootTest
public class ProducerTest {

    @Autowired
    private Producer producer;

    @Test
    public void produce() {
        for (int i = 0; i < 5; i++) {
            producer.enqueue(QueueManager.CLICK_DATE.getRoutingKey(),
                    new ClickDateCount("4321", 22L, 123123));
            sleep();
        }
    }

    @Test
    public void producePlatform() {
        for (int i = 0; i < 10; i++) {
            producer.enqueue(QueueManager.PLATFORM_COUNT.getRoutingKey(),
                    new PlatformCount("a123", "Mobile"));
            sleep();
        }
    }

    @Test
    public void produceTotalCount() {
        for (int i = 0; i < 10; i++) {
            //producer.enqueue("total_count", "hi " + new Date());
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
    }
}
