package me.nexters.chopstatsapi.produce;

import java.util.Date;

import me.nexters.chopstatsapi.rabbitmq.QueueManager;
import me.nexters.chopstatsapi.rabbitmq.model.ClickDateCount;
import me.nexters.chopstatsapi.rabbitmq.model.PlatformCount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import me.nexters.chopstatsapi.rabbitmq.producer.Producer;

/**
 * @author manki.kim
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTest {

    @Autowired
    private Producer producer;

    @Test
    public void produce() {
        for (int i = 0; i < 5; i++) {
            producer.enqueue(QueueManager.CLICK_DATE.getRoutingKey(),
                    new ClickDateCount("4321", 22L));
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
