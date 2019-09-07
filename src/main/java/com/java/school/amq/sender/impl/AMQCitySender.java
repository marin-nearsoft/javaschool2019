package com.java.school.amq.sender.impl;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.school.amq.sender.AMQSender;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AMQCitySender implements AMQSender {

    private final ApplicationRepository applicationRepository;
    private final ObjectMapper objectMapper;
    private static AMQCitySender instance = null;

    private AMQCitySender(ApplicationRepository applicationRepository, ObjectMapper objectMapper) {
        this.applicationRepository = applicationRepository;
        this.objectMapper = objectMapper;
    }

    public static AMQCitySender getInstance(ApplicationRepository applicationRepository, ObjectMapper objectMapper) {
        if (Objects.isNull(instance)) {
            instance = new AMQCitySender(applicationRepository, objectMapper);
        }
        return instance;
    }

    public String get() throws JsonProcessingException {
        logger.info("Calling getCities from repo");
        return objectMapper.writeValueAsString(applicationRepository.getCities());
    }
}
