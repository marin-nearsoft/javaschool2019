package com.java.school.amq.sender.impl;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.school.amq.sender.AMQSender;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AMQTransportTypeSender implements AMQSender {

    private final ObjectMapper objectMapper;
    private final ApplicationRepository applicationRepository;
    private static AMQTransportTypeSender instance = null;

    private AMQTransportTypeSender(ApplicationRepository applicationRepository, ObjectMapper objectMapper) {
        this.applicationRepository = applicationRepository;
        this.objectMapper = objectMapper;
    }

    public static AMQTransportTypeSender getInstance(ApplicationRepository applicationRepository, ObjectMapper objectMapper) {
        if (Objects.isNull(instance)) {
            instance = new AMQTransportTypeSender(applicationRepository, objectMapper);
        }
        return instance;
    }

    @Override
    public String get() throws JsonProcessingException {
        logger.info("Calling getTransportTypes from repo");
        return objectMapper.writeValueAsString(applicationRepository.getTransportTypes());
    }
}
