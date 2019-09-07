package com.java.school.amq.sender.impl;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.school.amq.sender.AMQSender;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AMQTransportVelocitySender implements AMQSender {

    private final ObjectMapper objectMapper;
    private final ApplicationRepository applicationRepository;
    private static AMQTransportVelocitySender instance = null;

    private AMQTransportVelocitySender(ApplicationRepository applicationRepository, ObjectMapper objectMapper) {
        this.applicationRepository = applicationRepository;
        this.objectMapper = objectMapper;
    }

    public static AMQTransportVelocitySender getInstance(ApplicationRepository applicationRepository, ObjectMapper objectMapper) {
        if (Objects.isNull(instance)) {
            instance = new AMQTransportVelocitySender(applicationRepository, objectMapper);
        }
        return instance;
    }

    @Override
    public String get() throws JsonProcessingException {
        logger.info("Calling getTransportVelocity from repo");
        return objectMapper.writeValueAsString(applicationRepository.getTransportVelocity());
    }
}
