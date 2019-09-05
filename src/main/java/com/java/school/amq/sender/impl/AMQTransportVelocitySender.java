package com.java.school.amq.sender.impl;

import java.util.List;
import java.util.Objects;

import com.java.school.amq.sender.AMQSender;
import com.java.school.domain.TransportVelocity;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AMQTransportVelocitySender implements AMQSender<TransportVelocity> {

    private ApplicationRepository applicationRepository;
    private static AMQTransportVelocitySender instance = null;

    private AMQTransportVelocitySender(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public static AMQTransportVelocitySender getInstance(ApplicationRepository applicationRepository) {
        if (Objects.isNull(instance)) {
            instance = new AMQTransportVelocitySender(applicationRepository);
        }
        return instance;
    }

    @Override
    public List<TransportVelocity> get() {
        logger.info("Calling getTransportVelocity from repo");
        return applicationRepository.getTransportVelocity();
    }
}
