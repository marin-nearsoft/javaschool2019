package com.java.school.amq.sender.impl;

import java.util.List;
import java.util.Objects;

import com.java.school.amq.sender.AMQSender;
import com.java.school.domain.TransportType;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AMQTransportTypeSender implements AMQSender<TransportType> {

    private ApplicationRepository applicationRepository;
    private static AMQTransportTypeSender instance = null;

    private AMQTransportTypeSender(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public static AMQTransportTypeSender getInstance(ApplicationRepository applicationRepository) {
        if (Objects.isNull(instance)) {
            instance = new AMQTransportTypeSender(applicationRepository);
        }
        return instance;
    }

    @Override
    public List<TransportType> get() {
        logger.info("Calling getTransportTypes from repo");
        return applicationRepository.getTransportTypes();
    }
}
