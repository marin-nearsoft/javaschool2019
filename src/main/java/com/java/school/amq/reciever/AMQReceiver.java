package com.java.school.amq.reciever;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
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
    public String receiveMessage(String messageRequest) {
        logger.info("Received <{}>", messageRequest);
        try {
            JsonNode jsonNode = objectMapper.readValue(messageRequest, ObjectNode.class);
            AMQSender amqSender = amqFactory.getSender(jsonNode);
            return amqSender.get();
        } catch (IOException e) {
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