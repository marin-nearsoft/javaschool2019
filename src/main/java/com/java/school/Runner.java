package com.java.school;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.java.school.config.AMQConfiguration;

@Component
public class Runner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    private final RabbitTemplate rabbitTemplate;

    public Runner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws InterruptedException {
        String message = "city";
        logger.info("Requesting table: " + message);
        String packageTypes = (String) rabbitTemplate.convertSendAndReceive(AMQConfiguration.TOPIC_EXCHANGE_NAME, "#", message);
        logger.info("Receiving table: " + packageTypes);
        Thread.sleep(1000);
    }

}
