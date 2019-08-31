package com.example.demo.stub;

import com.example.demo.stub.config.AMQConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    private final RabbitTemplate rabbitTemplate;

    public Runner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws InterruptedException {
        String message = "package_type";
        logger.info("Requesting table: " + message);
        List<String> packageTypes = (List<String>) rabbitTemplate.convertSendAndReceive(AMQConfiguration.TOPIC_EXCHANGE_NAME, "#", message);
        logger.info("Receiving table: " + packageTypes.toString());
        Thread.sleep(1000);
    }

}
