package com.java.school.amq.reciever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.java.school.amq.factory.AMQFactory;
import com.java.school.amq.sender.AMQSender;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AMQReceiver {

    private AMQFactory amqFactory;
    private ObjectMapper objectMapper;

    @SuppressWarnings("unused")
    public String receiveMessage(String tableName) {
        logger.info("Received <{}>", tableName);
        AMQSender amqSender = amqFactory.getSender(tableName);
        try {
            return amqSender.get();
        } catch (JsonProcessingException e) {
            return createException();
        }
    }

    private String createException() {
        ObjectNode error = JsonNodeFactory.instance.objectNode();
        error.put("type", "exception");
        error.put("message", "Pendejos, algo de su lado esta mal, revisenlo!");
        return error.toString();
    }

    @Autowired
    public void setAmqFactory(AMQFactory amqFactory) {
        this.amqFactory = amqFactory;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}