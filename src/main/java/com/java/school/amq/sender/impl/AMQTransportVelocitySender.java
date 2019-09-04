package com.java.school.amq.sender.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.school.amq.sender.AMQSender;
import com.java.school.domain.TransportVelocity;
import com.java.school.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AMQTransportVelocitySender implements AMQSender<TransportVelocity> {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public AMQTransportVelocitySender(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<TransportVelocity> get() {
        logger.info("Calling getTransportVelocity from repo");
        return applicationRepository.getTransportVelocity();
    }
}
