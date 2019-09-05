package com.java.school.amq.reciever;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.school.amq.factory.AMQFactory;
import com.java.school.amq.sender.AMQSender;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AMQReceiver {

    private final AMQFactory amqFactory;

    @Autowired
    public AMQReceiver(AMQFactory amqFactory) {
        this.amqFactory = amqFactory;
    }

    public List receiveMessage(String tableName) {
        logger.info("Received <{}>", tableName);
        AMQSender amqSender = amqFactory.getSender(tableName);
        return amqSender.get();
    }

}