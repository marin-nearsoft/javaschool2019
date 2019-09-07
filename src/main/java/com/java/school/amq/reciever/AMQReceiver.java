package com.java.school.amq.reciever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    public String receiveMessage(String tableName) {
        logger.info("Received <{}>", tableName);
        AMQSender amqSender = amqFactory.getSender(tableName);
        try {
            return amqSender.get();
        } catch (JsonProcessingException e) {
            return "Pendejos, algo de SU lado esta mal, revisenlo!";
        }
    }

}