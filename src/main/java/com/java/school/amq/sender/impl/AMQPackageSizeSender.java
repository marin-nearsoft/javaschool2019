package com.java.school.amq.sender.impl;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.school.amq.sender.AMQSender;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AMQPackageSizeSender implements AMQSender {

    private final ApplicationRepository applicationRepository;
    private final ObjectMapper objectMapper;
    private static AMQPackageSizeSender instance = null;

    private AMQPackageSizeSender(ApplicationRepository applicationRepository, ObjectMapper objectMapper) {
        this.applicationRepository = applicationRepository;
        this.objectMapper = objectMapper;
    }

    public static AMQPackageSizeSender getInstance(ApplicationRepository applicationRepository, ObjectMapper objectMapper) {
        if (Objects.isNull(instance)) {
            instance = new AMQPackageSizeSender(applicationRepository, objectMapper);
        }
        return instance;
    }

    @Override
    public String get() throws JsonProcessingException {
        logger.info("Calling getPackageSizes from repo");
        return objectMapper.writeValueAsString(applicationRepository.getPackageSizes());
    }
}
